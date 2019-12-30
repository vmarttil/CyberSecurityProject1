package sec.project.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PreferenceProfile extends AbstractPersistable<Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @MapsId
    private Registration registration;
    private String genderPref;
    private String agePref;
    private String plotKeywords;
    private String freePrefs;

    public PreferenceProfile(Registration registration, String genderPref, String agePref, String plotKeywords, String freePrefs) {
        this.registration = registration;
        this.genderPref = genderPref;
        this.agePref = agePref;
        this.plotKeywords = plotKeywords;
        this.freePrefs = freePrefs;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Registration getRegistration() {
        return this.registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getGenderPref() {
        return this.genderPref;
    }

    public void setGenderPref(String genderPref) {
        this.genderPref = genderPref;
    }

    public String getAgePref() {
        return this.agePref;
    }

    public void setAgePref(String agePref) {
        this.agePref = agePref;
    }

    public String getPlotKeywords() {
        return this.plotKeywords;
    }

    public void setPlotKeywords(String plotKeywords) {
        this.plotKeywords = plotKeywords;
    }

    public String getFreePrefs() {
        return this.freePrefs;
    }

    public void setFreePrefs(String freePrefs) {
        this.freePrefs = freePrefs;
    }
    
}
