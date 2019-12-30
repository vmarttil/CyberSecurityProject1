package sec.project.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.PreferenceProfile;
import sec.project.repository.AccountRepository;

@Controller
public class MainController {

    @Autowired
    private AccountRepository accountRepository;
    
    @RequestMapping(value = "/")
    public String defaultMapping(Authentication authentication) {
        return "redirect:/userview/";
    }
    
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String loginResults(Authentication authentication) {
        return "redirect:/userview/";
    }
    
    @RequestMapping(value = "/login-error.html")  
    public String loginError(Model model) {  
        model.addAttribute("loginError", true);  
        return "login";
    }
    
    @RequestMapping(value = "/logout")
    public String logout(Model model) {
        model.addAttribute("logout", true);
        return "login";
    }
    
}
