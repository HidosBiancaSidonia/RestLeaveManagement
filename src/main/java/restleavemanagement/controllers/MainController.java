package restleavemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.Person;
import restleavemanagement.model.Role;
import restleavemanagement.repository.PersonRepository;
import restleavemanagement.repository.RoleRepository;
import restleavemanagement.service.LeaveRequestService;
import restleavemanagement.service.PersonService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    ModelAndView model = null;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView home() {
        model =new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        List<Role> role = roleRepository.findAll();
        List<Person> persons = personRepository.findAll();

        model.addObject("role", role);
        model.addObject("users", persons);
        model.addObject("user", person);
        model.setViewName("home");

        model.addObject("leaveRequestDto", new LeaveRequestDto());
        return model;
    }


    @PostMapping("/home")
    public ModelAndView createLeaveRequestAction(@ModelAttribute("leaveRequestDto") LeaveRequestDto leaveRequestDto) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(leaveRequestDto);
        modelAndView.setViewName("home");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        leaveRequestDto.setPerson(person);

        System.out.println(leaveRequestDto);
        System.out.println("Intra in controller");

        try {
            leaveRequestService.createLeaveRequest(leaveRequestDto);
        } catch (Exception e) {
//            TODO: Add error object + message in html
            modelAndView.setViewName("user/errors");
        }

        return modelAndView;
    }
}
