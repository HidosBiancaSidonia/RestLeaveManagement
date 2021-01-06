package restleavemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;
import restleavemanagement.model.Role;
import restleavemanagement.repository.PersonRepository;
import restleavemanagement.repository.RoleRepository;
import restleavemanagement.service.PersonService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        List<Role> role = roleRepository.findAll();
        List<Person> persons = personRepository.findAll();

        model.addObject("role", role);
        model.addObject("users", persons);
        model.addObject("user", person);
        model.setViewName("home");
        return model;
    }




}
