package restleavemanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import restleavemanagement.dto.PersonRegistrationDto;
import restleavemanagement.service.PersonService;

@Controller
@RequestMapping("/registration")
class PersonRegistrationController {
    private PersonService personService;

    public PersonRegistrationController(PersonService personService) {
        super();
        this.personService = personService;
    }

    @ModelAttribute("person")
    public PersonRegistrationDto personRegistrationDto() {
        return new PersonRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("person") PersonRegistrationDto registrationDto) {
        //personService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
