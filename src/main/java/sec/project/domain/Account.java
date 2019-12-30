package sec.project.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import sec.project.repository.AccountRepository;
import sec.project.repository.EventRepository;
import sec.project.repository.RegistrationRepository;

@Entity
public class Account extends AbstractPersistable<Long> {

    //@Autowired
    //private AccountRepository accountRepository;
    
    //@Autowired
    //private EventRepository eventRepository;
    
    //@Autowired
    //private RegistrationRepository registrationRepository;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    @OneToMany(mappedBy = "eventAdmin")
    private List<Event> events;
    @OneToMany(mappedBy = "account")
    private List<Registration> registrations;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public List<Event> getEvents() {
        return events;
    }

    public List<Registration> getRegistrations() {
        return this.registrations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
