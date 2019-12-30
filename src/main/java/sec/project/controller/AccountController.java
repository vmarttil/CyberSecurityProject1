package sec.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Event;
import sec.project.domain.Registration;
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
    

    
    @RequestMapping("/userview/{accountId}")
    public String showAccount(Authentication authentication, HttpSession session, Model model, @PathVariable Long accountId) {
        Account account = accountRepository.findOne(accountId);
        model.addAttribute("username",account.getUsername());
        model.addAttribute("fullName", account.getFullName());
        model.addAttribute("events", account.getEvents());
        model.addAttribute("registrations", account.getRegistrations());
        List<Event> openEvents = eventRepository.findAll().stream().filter(e -> e.isRegistrationOpen()).collect(Collectors.toList());
        model.addAttribute("openEvents", openEvents);
        return "userview";
    }


}
