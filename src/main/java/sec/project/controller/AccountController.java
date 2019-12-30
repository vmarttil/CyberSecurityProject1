package sec.project.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Event;
import sec.project.domain.PreferenceProfile;
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
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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

    @RequestMapping(value = "/create_account", method = RequestMethod.GET)
    public String newAccount(Model model) {
        model.addAttribute("genderList", PreferenceProfile.getGENDERPREFS());
        return "create_account";
    }
    
    @RequestMapping(value = "/create_account", method = RequestMethod.POST)  
    public String createAccount(Model model, @RequestParam String newUsername, @RequestParam String newPassword, @RequestParam String newFullName, @RequestParam String newEmail, @RequestParam String newGender, @RequestParam String newBirthdate) {  
        if (accountRepository.findByUsername(newUsername) != null) {
            model.addAttribute("duplicateUsername", true);
            return "create_account";
        } else {
            Account account = new Account();
            account.setUsername(newUsername);
            account.setPassword(passwordEncoder.encode(newPassword));
            account.setFullName(newFullName);
            account.setEmail(newEmail);
            account.setGender(newGender);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            account.setBirthdate(LocalDate.parse(newBirthdate, formatter));
            accountRepository.save(account);
            model.addAttribute("accountCreated", true);
            model.addAttribute("accountUsername", newUsername);
            return "login";
        }
    }
    
}
