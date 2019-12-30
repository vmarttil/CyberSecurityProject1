package sec.project.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    /*
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public String viewEvent(Authentication authentication, Model model, @PathVariable Long id) {
        Account account = accountRepository.findByUsername(authentication.getName());
        Event event = eventRepository.findOne(id);
        //if (event.getAdmin().equals(account)) {
            model.addAttribute("id", id);
            return "event";
        //} else {
        //    return null;
        //}
    }

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
