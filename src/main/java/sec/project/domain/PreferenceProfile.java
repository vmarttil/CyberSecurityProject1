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
    private static String[] GENDERPREFS = new String[]{"Female","Male","Non-binary"};
    private String agePref;
    private static String[] AGEPREFS = new String[]{"Young","Middle-aged","Elderly"};
    private String[] plotKeywords;
    private static String[] PLOTKEYWORDS = new String[]{"action","crime","drama","family","intrigue", "politics", "romance", "violence"};
    private String freePrefs;
    
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
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

    public static String[] getGENDERPREFS() {
        return GENDERPREFS;
    }
    
    public String getAgePref() {
        return this.agePref;
    }

    public void setAgePref(String agePref) {
        this.agePref = agePref;
    }

    public static String[] getAGEPREFS() {
        return AGEPREFS;
    }
    
    public String[] getPlotKeywords() {
        return this.plotKeywords;
    }

    public void setPlotKeywords(String[] plotKeywords) {
        this.plotKeywords = plotKeywords;
    }

    public String listPlotKeywords() {
        String keywords = "";
        for (int i = 0; i < this.plotKeywords.length - 1; i++) {
            keywords = keywords + this.plotKeywords[i] + ", ";
        }
        keywords = keywords + this.plotKeywords[this.plotKeywords.length - 1];
        return keywords;
    }
    
    public static String[] getPLOTKEYWORDS() {
        return PLOTKEYWORDS;
    }
    
    public String getFreePrefs() {
        return this.freePrefs;
    }

    public void setFreePrefs(String freePrefs) {
        this.freePrefs = freePrefs;
    }
    
}
