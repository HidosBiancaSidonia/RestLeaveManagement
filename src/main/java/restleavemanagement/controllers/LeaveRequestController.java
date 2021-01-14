package restleavemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;
import restleavemanagement.repository.LeaveRequestRepository;
import restleavemanagement.repository.PersonRepository;
import restleavemanagement.service.LeaveRequestService;
import restleavemanagement.service.PersonService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LeaveRequestController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @Autowired
    LeaveRequestService leaveRequestService;

    @Autowired
    PersonService personService;

    @ModelAttribute("leaveRequestDto")
    @RequestMapping(value = {"/create_leave_request"}, method = RequestMethod.GET)
    public ModelAndView createLeaveRequestModelView() {
        ModelAndView modelAndView = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());

        LeaveRequest leaveRequestExist = leaveRequestService.getLeaveRequest(person);
        if(leaveRequestExist !=null ){
            modelAndView = new ModelAndView("/errors");
            modelAndView.setViewName("/errors");
            modelAndView.addObject("name", person.getName());
        }
        else{
            modelAndView = new ModelAndView("/create_leave_request");
            modelAndView.addObject("name", person.getName());
            modelAndView.addObject("leaveRequestDto", new LeaveRequestDto());
            modelAndView.setViewName("/create_leave_request");
        }

        return modelAndView;
    }

    @PostMapping("/create_leave_request")
    public ModelAndView createLeaveRequestAction(@ModelAttribute("leaveRequestDto") LeaveRequestDto leaveRequestDto) throws Exception {
        ModelAndView modelAndView = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        leaveRequestDto.setPerson(person);

        LeaveRequest leaveRequestExist = leaveRequestService.getLeaveRequest(person);
        if(leaveRequestExist !=null ){
            modelAndView = new ModelAndView("/errors");
            modelAndView.setViewName("/errors");
            modelAndView.addObject("name", person.getName());
        }
        else{
            modelAndView = new ModelAndView("/create_leave_request");
            modelAndView.addObject(leaveRequestDto);
            modelAndView.setViewName("/create_leave_request");
            try {
                leaveRequestService.save(leaveRequestDto);
            } catch (Exception e) {

            }
        }

        return modelAndView;
    }

    @GetMapping("/myRequest")
    public ModelAndView listLeaveRequestsView() throws ParseException {
        ModelAndView model = new ModelAndView("/myRequest");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequest(person);

        if(leaveRequest != null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = formatter.format(leaveRequest.getStartDate());
            String endDate = formatter.format(leaveRequest.getEndDate());

            model.setViewName("/myRequest");
            model.addObject("name", person.getName());
            model.addObject("startDate",startDate);
            model.addObject("endDate",endDate);
            model.addObject("leaveRequest", leaveRequest);
        }
        else
        {
            model.setViewName("/noRequest");
            model.addObject("name", person.getName());
        }

        return model;
    }

    @PostMapping("/delete")
    public ModelAndView delete(){
        ModelAndView modelAndView = new ModelAndView("/delete");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();

        for (LeaveRequest leaveRequest: leaveRequests) {
            if(leaveRequest.getPerson().getId().equals(person.getId())){
                leaveRequestService.deleteLeaveRequest(leaveRequest.getId());
            }
        }

        modelAndView.setViewName("/delete");
        return  modelAndView;
    }

    @GetMapping("/errors")
    public ModelAndView errorHandling() {
        ModelAndView model = new ModelAndView("/errors");
        model.setViewName("/errors");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());
        model.addObject("name", person.getName());

        return model;
    }

    @GetMapping("/employeeRequest")
    public ModelAndView seeEmployeeRequest() throws ParseException {
        ModelAndView model = new ModelAndView("/employeeRequest");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());

        List<Person> persons = personRepository.findAll();
        ArrayList<LeaveRequest> leaveRequests = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Person employee : persons) {
            for(Person boss : employee.getBoss()) {
                if(boss.getEmail().equals(person.getEmail())){
                   LeaveRequest leaveRequest = leaveRequestService.getLeaveRequest(employee);

                    if(leaveRequest!=null){
                        LeaveRequest lR = new LeaveRequest(formatter.format(leaveRequest.getStartDate()),formatter.format(leaveRequest.getEndDate()),leaveRequest.getPerson());
                        leaveRequests.add(lR);
                   }
                }
            }
        }


        if(leaveRequests.isEmpty()){
            model.setViewName("/noEmployeeRequest");
        }
        else{

            model.setViewName("/employeeRequest");
            model.addObject("leaveRequests", leaveRequests);
        }
        model.addObject("name", person.getName());

        return model;
    }
}
