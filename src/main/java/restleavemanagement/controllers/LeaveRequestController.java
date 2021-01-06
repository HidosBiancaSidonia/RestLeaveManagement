package restleavemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.service.LeaveRequestService;

@Controller
public class LeaveRequestController {

    @Autowired
    LeaveRequestService leaveRequestService;

    @RequestMapping(value = {"/create-leave-request"}, method = RequestMethod.GET)
    public ModelAndView form(@RequestParam(value = "error", required = false) String error,
                             @RequestParam LeaveRequest leaveRequest) {

        ModelAndView model = new ModelAndView();

        if (leaveRequestService.checkIfLeaveRequestIs5DaysBefore(leaveRequest.getStartDate())) {
            error = "Leave request must be after at least 5 days.";
        }

        if (leaveRequestService.checkIfDateIsSixMonthsEarlier(leaveRequest.getStartDate())) {
            error = "Leave request cant be six months earlier";
        }

        if (error != null) {
            model.addObject("error", error);
        }
        return model;
    }


}
