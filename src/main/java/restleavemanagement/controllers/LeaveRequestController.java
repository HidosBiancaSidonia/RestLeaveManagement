package restleavemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restleavemanagement.dto.ForButtonsDto;
import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.ForButtons;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;
import restleavemanagement.repository.ForButtonsRepository;
import restleavemanagement.repository.LeaveRequestRepository;
import restleavemanagement.repository.PersonRepository;
import restleavemanagement.service.ForButtonsService;
import restleavemanagement.service.LeaveRequestService;
import restleavemanagement.service.PersonService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class LeaveRequestController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @Autowired
    ForButtonsRepository forButtonsRepository;

    @Autowired
    LeaveRequestService leaveRequestService;

    @Autowired
    PersonService personService;

    @Autowired
    ForButtonsService forButtonsService;

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
        if(leaveRequestExist != null ){
            modelAndView = new ModelAndView("/errors");
            modelAndView.setViewName("/errors");
            modelAndView.addObject("name", person.getName());
        }
        else{

            long ms = Math.abs(leaveRequestDto.getEndDate().getTime() - leaveRequestDto.getStartDate().getTime());

            if(TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS) > person.getVacationDays()){
                modelAndView = new ModelAndView("/vacationDays");
                modelAndView.setViewName("/vacationDays");
                modelAndView.addObject("name", person.getName());
            }
            else {
                try {
                    leaveRequestService.save(leaveRequestDto);

                    for (Person boss:person.getBoss()) {
                        forButtonsService.save(new ForButtonsDto(person.getId(), boss.getId()));
                    }

                    modelAndView = new ModelAndView("/create_leave_request");
                    modelAndView.addObject(leaveRequestDto);
                    modelAndView.setViewName("/create_leave_request");
                } catch (Exception e) {
                    modelAndView = new ModelAndView("/criteria");
                    modelAndView.setViewName("/criteria");
                    modelAndView.addObject("name", person.getName());
                }
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

        List<ForButtons> forButtonsList = forButtonsRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Person employee : persons) {
            for(Person boss : employee.getBoss()) {
                if(boss.getEmail().equals(person.getEmail())){
                   LeaveRequest leaveRequest = leaveRequestService.getLeaveRequest(employee);

                    if(leaveRequest!=null){
                        if(leaveRequest.getStatus().equals("PENDING")){
                            for (ForButtons forButton:forButtonsList) {
                                if(forButton.getEmployee_id() == employee.getId() &&
                                forButton.getBoss_id() == boss.getId() && !forButton.isPress()){
                                    LeaveRequest lR = new LeaveRequest(formatter.format(leaveRequest.getStartDate()),formatter.format(leaveRequest.getEndDate()),leaveRequest.getPerson());
                                    leaveRequests.add(lR);
                                }
                            }
                        }
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
            model.addObject("item", new LeaveRequest());
        }
        model.addObject("name", person.getName());

        return model;
    }


    @PostMapping("/employeeRequest/accept")
    public String accept(@RequestParam("personId") Long person_id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/employeeRequest");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());

        System.out.println(person_id);

        List<ForButtons> forButtons = forButtonsRepository.findAll();

        for (ForButtons forButton:forButtons) {
            if(forButton.getBoss_id()==person.getId() && forButton.getEmployee_id()==person_id){
                forButton.setPress(true);
                forButtonsRepository.save(forButton);
            }

        }

        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        for (LeaveRequest leaveRequest:leaveRequests) {
            if(leaveRequest.getPerson().getId().equals(person_id)){
                int nr = leaveRequest.getNrStatus()+1;
                leaveRequest.setNrStatus(nr);
                if(leaveRequest.getNrStatus()==leaveRequest.getPerson().getBoss().size()){
                    leaveRequest.setStatus("ACCEPTED");
                }

                leaveRequestRepository.save(leaveRequest);
            }

        }

        return "redirect:/employeeRequest?accept";
    }


    @PostMapping("/employeeRequest/deny")
    public String deny(@RequestParam("personId") Long person_id) throws Exception {
        ModelAndView model = new ModelAndView("/employeeRequest");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByEmail(auth.getName());

        System.out.println(person_id);

        List<ForButtons> forButtons = forButtonsRepository.findAll();

        for (ForButtons forButton:forButtons) {
            if(forButton.getBoss_id()==person.getId() && forButton.getEmployee_id()==person_id){
                forButton.setPress(true);
                forButtonsRepository.save(forButton);
            }

        }

        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        for (LeaveRequest leaveRequest:leaveRequests) {
            if(leaveRequest.getPerson().getId().equals(person_id)){
                leaveRequest.setStatus("DENY");
                leaveRequestRepository.save(leaveRequest);
            }

        }

        model.setViewName("accept");
        return "redirect:/employeeRequest?deny";
    }
}
