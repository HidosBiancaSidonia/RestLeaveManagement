package restleavemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restleavemanagement.model.Person;
import restleavemanagement.model.Role;
import restleavemanagement.model.TeamType;
import restleavemanagement.repository.PersonRepository;
import restleavemanagement.repository.RoleRepository;
import restleavemanagement.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    ModelAndView model = null;

    /**
     * Constructor
     */
    public MainController() {
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView home() {
        model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        List<Role> role = roleRepository.findAll();
        List<Person> persons = personRepository.findAll();

        ArrayList<String> teams = new ArrayList<>();
        for (TeamType team:person.getTeamTypes()) {
            teams.add(team.getName()+" ");
        }

        model.addObject("teams",teams);
        model.addObject("role", role);
        model.addObject("users", persons);
        model.addObject("user", person);
        model.setViewName("home");
        return model;
    }
}
