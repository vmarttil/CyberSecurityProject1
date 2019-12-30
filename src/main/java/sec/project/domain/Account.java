package sec.project.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate birthdate;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    
    public int calculateAge() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(this.birthdate, now);
        int age = (int) period.toTotalMonths() / 12;
        return age;
    }
    
    public String printBirthdate() {    
        String birthday = this.birthdate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return birthday.replaceAll("0([1-9]\\.)","$1");
    }
    
    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
    
    public List<Registration> getRegistrations() {
        return this.registrations;
    }

    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
}
