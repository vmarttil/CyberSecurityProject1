package sec.project.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventName;
    private String eventLocation;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String eventDescription;
    private boolean registrationOpen; 
    @ManyToOne
    private Account eventAdmin;
    @OneToMany(mappedBy = "event")
    private List<Registration> registrations;
    

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String printId() {
        return this.id.toString();
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

    public LocalDate getEventStartDate() {
        return this.eventStartDate;
    }

    public void setEventStartDate(LocalDate eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDate getEventEndDate() {
        return this.eventEndDate;
    }
    
    public void setEventEndDate(LocalDate eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
    
    public String printEventDate() {
        String start = eventStartDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String end = eventEndDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        
        if (start.equals(end)) {
            return start.replaceAll("0([1-9]\\.)","$1");
        } else {
            if (start.substring(6).equals(end.substring(6))) {
                start = start.substring(0,6);
                if (start.substring(3).equals(end.substring(3,6))) {
                    start = start.substring(0,3);
                }
            }
            return start.replaceAll("0([1-9]\\.)","$1") + "–" + end.replaceAll("0([1-9]\\.)","$1");
        }
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
