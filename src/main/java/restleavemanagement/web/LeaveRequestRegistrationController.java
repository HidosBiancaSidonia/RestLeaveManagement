package restleavemanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.service.LeaveRequestService;

@Controller
@RequestMapping("/create_leave_request")
public class LeaveRequestRegistrationController {
    private LeaveRequestService leaveRequestService;

    public LeaveRequestRegistrationController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @ModelAttribute("leave_request")
    public LeaveRequestDto personRegistrationDto() {
        return new LeaveRequestDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "create_leave_request";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("leave_request") LeaveRequestDto leaveRequestDto) {
        leaveRequestService.save(leaveRequestDto);
        return "redirect:/create_leave_request?success";
    }
}
