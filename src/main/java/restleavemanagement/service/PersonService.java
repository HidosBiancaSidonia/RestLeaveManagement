package restleavemanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import restleavemanagement.model.Person;

public interface PersonService extends UserDetailsService {

    public Person findPersonByEmail(String username);
}
