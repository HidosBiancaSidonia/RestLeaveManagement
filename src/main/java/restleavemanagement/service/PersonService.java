package restleavemanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import restleavemanagement.dto.PersonRegistrationDto;
import restleavemanagement.model.Person;

public interface PersonService extends UserDetailsService {
    Person save(PersonRegistrationDto registrationDto);

    public Person findPersonByEmail(String username);
}
