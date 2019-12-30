package sec.project.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @PostConstruct
    public void init() {
        Account account = new Account();
        account.setUsername("roger");
        account.setPassword(passwordEncoder.encode("carrots"));
        account.setFullName("Roger Sample");
        account.setEmail("roger.sample@example.com");
        account.setGender("Male");
        account.setBirthdate(LocalDate.parse("1977-05-23", formatter));
        accountRepository.save(account);
        account = new Account();
        account.setUsername("valiant");
        account.setPassword(passwordEncoder.encode("vaudeville"));
        account.setFullName("Prince Valiant");
        account.setEmail("prince.valiant@example.com");
        account.setGender("Male");
        account.setBirthdate(LocalDate.parse("1986-02-26", formatter));
        accountRepository.save(account);
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
    
}
