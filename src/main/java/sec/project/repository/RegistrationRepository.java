package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
