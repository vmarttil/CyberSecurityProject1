package sec.project.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Registration;
import sec.project.domain.Account;
import sec.project.domain.Event;
import sec.project.repository.AccountRepository;
import sec.project.repository.EventRepository;
import sec.project.repository.RegistrationRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RegistrationRepository registrationRepository;

    @RequestMapping(value = "/add_event", method = RequestMethod.POST)
    public String addEvent(Authentication authentication, Model model) {
        return "create_event";
    }
    
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String createEvent(Authentication authentication, @RequestParam String name, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String location, @RequestParam String description) throws ParseException {
        Account account = accountRepository.findByUsername(authentication.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        Event newEvent = new Event();
        newEvent.setName(name);
        newEvent.setEventStartDate(start);
        newEvent.setEventEndDate(end);
        newEvent.setLocation(location);
        newEvent.setRegistrationOpen(true);
        newEvent.setDescription(description);
        newEvent.setAdmin(account);
        account.addEvent(newEvent);
        eventRepository.save(newEvent);
        accountRepository.save(account);
        return "redirect:/userview";
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public String showEvent(Authentication authentication, Model model, @PathVariable Long id) {
        // VULNERABILITY: This would need an authentication check to see if the current user is the event admin
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        return "event";
    }
    
    @RequestMapping(value = "/events/open", method = RequestMethod.POST)
    public String openEvent(@RequestParam Long id, Authentication authentication, Model model) {
        // VULNERABILITY: This would need an authentication check to see if the current user is the event admin
        Event event = eventRepository.findOne(id);
        event.setRegistrationOpen(true);
        eventRepository.save(event);
        return "redirect:/events/" + id;
    }
    
    @RequestMapping(value = "/events/close", method = RequestMethod.POST)
    public String closeEvent(@RequestParam Long id, Authentication authentication, Model model) {
        // VULNERABILITY: This would need an authentication check to see if the current user is the event admin
        Event event = eventRepository.findOne(id);
        event.setRegistrationOpen(false);
        eventRepository.save(event);
        return "redirect:/events/" + id;
    }
    
    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public String remove(Authentication authentication, @PathVariable Long id) {
        // VULNERABILITY: This would need an authentication check to see if the current user is the event admin
        Event event = eventRepository.findOne(id);
        List<Registration> registrations = event.getRegistrations();
        List<Long> regIds = new ArrayList<>();
        for (Registration reg : registrations) {
            regIds.add(reg.getId());
            Account account = reg.getAccount();
            account.getRegistrations().remove(reg);
            accountRepository.save(account);
        }
        event.getRegistrations().clear();
        eventRepository.save(event);
        for (Long regId : regIds) {
            registrationRepository.delete(regId);
        }
        eventRepository.delete(id);
        return "redirect:/userview/";
    }
    
}
