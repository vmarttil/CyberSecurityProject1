package sec.project.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sec.project.domain.Registration;
import sec.project.domain.Account;
import sec.project.domain.Event;
import sec.project.repository.AccountRepository;
import sec.project.repository.EventRepository;
import sec.project.repository.RegistrationRepository;

@Controller
public class LoginController {
    
    @Resource(name = "authenticationManager")
    private AuthenticationManager authManager; 
    
    @Autowired
    private AccountRepository accountRepository;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, @RequestParam("username") String user, @RequestParam("password") String pass) { 
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, pass);
        Authentication auth = authManager.authenticate(authReq);     
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        if (auth.isAuthenticated()) {
            Long accountId = accountRepository.findByUsername(user).getId();
            return "redirect:/userview/" + accountId;
        } else {
            return "login_failed";
        }
    }
    

    /*
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public String addFile(Authentication authentication, @RequestParam("file") MultipartFile file) throws IOException {
        Account account = accountRepository.findByUsername(authentication.getName());
        
        FileObject fileObject = new FileObject();
        fileObject.setContentType(file.getContentType());
        fileObject.setContent(file.getBytes());
        fileObject.setName(file.getOriginalFilename());
        fileObject.setContentLength(file.getSize());
        fileObject.setAccount(account);
        
        fileRepository.save(fileObject);
        

        return "redirect:/files";
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.DELETE)
    public String delete(Authentication authentication, @PathVariable Long id) {
        Account account = accountRepository.findByUsername(authentication.getName());
        FileObject fo = fileRepository.findOne(id);
        if (fo.getAccount().equals(account)) {
            fileRepository.delete(id);
        }
        return "redirect:/files";
    }
    
    
    
    
    
    
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String loadForm(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Registration(name, address));
        return "done";
    }
*/
}
