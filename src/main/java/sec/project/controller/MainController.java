package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sec.project.repository.AccountRepository;

@Controller
public class MainController {

    @Autowired
    private AccountRepository accountRepository;
    
    @RequestMapping("/")
    public String defaultMapping(Authentication authentication) {
        return "redirect:/userview/";
    }

}
