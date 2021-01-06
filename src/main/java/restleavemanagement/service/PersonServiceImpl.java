package restleavemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restleavemanagement.dto.PersonRegistrationDto;
import restleavemanagement.enumeration.RoleType;
import restleavemanagement.model.Person;
import restleavemanagement.model.Role;
import restleavemanagement.model.TeamType;
import restleavemanagement.repository.PersonRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl  implements  PersonService{

    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository){
        super();
        this.personRepository=personRepository;
    }

    @Override
    public Person save(PersonRegistrationDto registrationDto) {
        Person person = new Person(registrationDto.getName(),
                registrationDto.getPhoneNumber(), "50",
                registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),Arrays.asList(new Role(RoleType.EMPLOYEE.name())),new HashSet<>(Arrays.asList(new TeamType(restleavemanagement.enumeration.TeamType.TEST.name()))));

        System.out.println(passwordEncoder.encode(registrationDto.getPassword()));


        return personRepository.save(person);
    }

    @Override
    public Person findPersonByEmail(String email) {
        Person person = personRepository.findByEmail(email);
        return person;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if(person == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(person.getEmail(),person.getPassword(),mapRolesToAuthorities(person.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>roles){

        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
