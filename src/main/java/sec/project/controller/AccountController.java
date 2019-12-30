package sec.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Account;
import sec.project.domain.Event;
import sec.project.repository.AccountRepository;
import sec.project.repository.EventRepository;
import sec.project.repository.RegistrationRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;
    
    @RequestMapping(value = "/userview", method=RequestMethod.GET)
    public String showAccount(Authentication authentication, Model model) {
        Account account = accountRepository.findByUsername(authentication.getName());
        model.addAttribute("account",account);
        model.addAttribute("events", account.getEvents());
        model.addAttribute("registrations", account.getRegistrations());
        List<Event> openEvents = eventRepository.findAll().stream().filter(e -> e.isRegistrationOpen()).collect(Collectors.toList());
        model.addAttribute("openEvents", openEvents);
        return "userview";
    }

}
