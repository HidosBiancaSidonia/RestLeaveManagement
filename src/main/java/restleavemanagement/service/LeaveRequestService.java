package restleavemanagement.service;

import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;

public interface LeaveRequestService {
    LeaveRequest save(LeaveRequestDto registrationDto) throws Exception;

    LeaveRequest getLeaveRequest(Person person);

    void deleteLeaveRequest(Long id);
}
