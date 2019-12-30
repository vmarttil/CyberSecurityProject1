package sec.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import sec.project.repository.EventRepository;
import sec.project.repository.AccountRepository;
import sec.project.repository.RegistrationRepository;

@Entity
public class Event extends AbstractPersistable<Long> {

    //@Autowired
    //private EventRepository eventRepository;
    
    //@Autowired
    //private AccountRepository accountRepository;
    
    //@Autowired
    //private RegistrationRepository registrationRepository;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventName;
    private String eventLocation;
    private Date eventDate;
    private String eventDescription;
    private boolean registrationOpen; 
    @ManyToOne
    private Account eventAdmin;
    @OneToMany(mappedBy = "event")
    private List<Registration> registrations;
    
    public Event(String name, String location, Date eventDate, Account admin) {
        this.eventName = name;
        this.eventLocation = location;
        this.eventDate = eventDate;
        this.registrationOpen = true;
        this.eventAdmin = admin;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return this.eventName;
    }

    public void setName(String name) {
        this.eventName = name;
    }

    public String getLocation() {
        return this.eventLocation;
    }

    public void setLocation(String location) {
        this.eventLocation = location;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return this.eventDescription;
    }

    public void setDescription(String description) {
        this.eventDescription = description;
    }

    public boolean isRegistrationOpen() {
        return this.registrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        this.registrationOpen = registrationOpen;
    }

    public Account getAdmin() {
        return this.eventAdmin;
    }

    public void setAdmin(Account admin) {
        this.eventAdmin = admin;
    }
    
    public List<Registration> getRegistrations() {
        return this.registrations;
    }
    
    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }
    
    public Registration findRegistrationByAccount(Account account) {
        List<Registration> registrations = this.registrations.stream().filter(r -> r.getAccount().equals(account)).collect(Collectors.toList());
        if (registrations.size() == 0) {
            return null;
        } else {
            return registrations.get(registrations.size() - 1);
        }
    }
    
    // Need to add finder methods for gender, age group and plot keywords, as well as the insecure nativeQuery method for running SQL queries on the free preference column
    
    
    public void deleteRegistration(Account account) {
        this.registrations.remove(this.registrations.indexOf(account));
    }
    
    
}
