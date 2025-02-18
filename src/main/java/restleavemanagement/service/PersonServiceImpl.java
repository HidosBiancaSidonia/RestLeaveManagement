package restleavemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restleavemanagement.model.Person;
import restleavemanagement.model.Role;
import restleavemanagement.repository.PersonRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl  implements  PersonService{

    private final PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository){
        super();
        this.personRepository=personRepository;
    }

    @Override
    public Person findPersonByEmail(String email) {
        Person person = personRepository.findByEmail(email);
        return person;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), mapRolesToAuthorities(person.getRole()));

        return userDetails;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>roles){

        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
