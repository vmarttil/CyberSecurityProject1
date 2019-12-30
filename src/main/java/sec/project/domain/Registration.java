package sec.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Registration extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Event event;
    @ManyToOne
    private Account account;
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
    private PreferenceProfile preferences;
    
    public Registration(Event event, Account account, PreferenceProfile preferences) {
        this.event = event;
        this.account = account;
        this.preferences = preferences;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public PreferenceProfile getPreferences() {
        return this.preferences;
    }
    
    public void setPreferences(PreferenceProfile preferences) {
        this.preferences = preferences;
    }
    

}
