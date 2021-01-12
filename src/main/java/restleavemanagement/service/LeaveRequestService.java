package restleavemanagement.service;

import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.LeaveRequest;

public interface LeaveRequestService {
    LeaveRequest save(LeaveRequestDto registrationDto);

    boolean createLeaveRequest(LeaveRequestDto leaveRequest) throws Exception;
}
