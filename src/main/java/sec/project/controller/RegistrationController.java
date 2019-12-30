package sec.project.controller;

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
import sec.project.domain.PreferenceProfile;
import sec.project.domain.Registration;
import sec.project.repository.AccountRepository;
import sec.project.repository.EventRepository;
import sec.project.repository.RegistrationRepository;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @RequestMapping(value = "/add_registration", method = RequestMethod.POST)
    public String addRegistration(Authentication authentication, Model model, @RequestParam Long eventId) {
        Event event = eventRepository.findOne(eventId);
        model.addAttribute("event", event);
        Account account = accountRepository.findByUsername(authentication.getName());
        model.addAttribute("account", account);
        model.addAttribute("genderPrefList", PreferenceProfile.getGENDERPREFS());
        model.addAttribute("agePrefList", PreferenceProfile.getAGEPREFS());
        model.addAttribute("plotKeywordList", PreferenceProfile.getPLOTKEYWORDS());
        return "create_registration";
    }

    @RequestMapping(value = "/registrations/{eventId}", method = RequestMethod.POST)
    public String createRegistration(Authentication authentication, @PathVariable Long eventId, @RequestParam String genderPref, @RequestParam String agePref, @RequestParam String[] plotKeywords, @RequestParam String freePrefs) {
        Registration newRegistration = new Registration();
        Account account = accountRepository.findByUsername(authentication.getName());
        Event event = eventRepository.findOne(eventId);
        PreferenceProfile newProfile = new PreferenceProfile();
        newProfile.setRegistration(newRegistration);
        newProfile.setGenderPref(genderPref);
        newProfile.setAgePref(agePref);
        newProfile.setPlotKeywords(plotKeywords);
        newProfile.setFreePrefs(freePrefs);
        newRegistration.setEvent(event);
        newRegistration.setAccount(account);
        newRegistration.setPreferences(newProfile);
        account.addRegistration(newRegistration);
        event.addRegistration(newRegistration);
        registrationRepository.save(newRegistration);
        accountRepository.save(account);
        eventRepository.save(event);
        return "redirect:/userview";
    }
    
    @RequestMapping(value = "/registrations/{id}", method = RequestMethod.GET)
    public String showRegistration(Authentication authentication, Model model, @PathVariable Long id) {
        // VULNERABILITY: This would need an authentication check to see if the current user is the registrant of this registration or the event admin
        Registration registration = registrationRepository.findOne(id);
        model.addAttribute("registration", registration);
        model.addAttribute("registrant", registration.getAccount());
        model.addAttribute("event", registration.getEvent());
        model.addAttribute("preferences", registration.getPreferences());
        return "registration";
    }
    
    @RequestMapping(value = "/registrations/{id}", method = RequestMethod.DELETE)
    public String remove(Authentication authentication, @PathVariable Long id) {
        // VULNERABILITY: This would need an authentication check to see if the current user is the registrant of this registration or the event admin
        registrationRepository.delete(id);
        return "redirect:/userview/";
    }
    
}
