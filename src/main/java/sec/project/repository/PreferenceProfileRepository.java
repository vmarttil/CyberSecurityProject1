package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.PreferenceProfile;

public interface PreferenceProfileRepository extends JpaRepository<PreferenceProfile, Long> {

}
