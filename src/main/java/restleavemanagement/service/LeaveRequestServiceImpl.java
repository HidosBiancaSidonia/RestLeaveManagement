package restleavemanagement.service;

import org.springframework.stereotype.Service;
import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;
import restleavemanagement.repository.LeaveRequestRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public LeaveRequest save(LeaveRequestDto registrationDto) throws Exception {
        if (startDateIsSixMonthsBefore(registrationDto.getStartDate())) {
            throw new Exception("You can't create a leave request with 6 months days after starting date.");
        }

        if (startDateIsFiveDaysAhead(registrationDto.getStartDate())) {
            throw new Exception("You can't create a leave request 5 days before starting date.");
        }

        LeaveRequest leaveRequest = new LeaveRequest(registrationDto.getStartDate(),registrationDto.getEndDate(),"PENDING",0,registrationDto.getPerson());

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest getLeaveRequest(Person person) {
        return leaveRequestRepository.findByPerson(person);
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }

    private boolean startDateIsSixMonthsBefore(Date startDate) {
        Date now = new Date();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            startDate = sdf.parse(startDate.toString());
            long ms = Math.abs(startDate.getTime() - now.getTime());
            long result = TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);

            return result > 180;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean startDateIsFiveDaysAhead(Date startDate) {
        Date now = new Date();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            startDate = sdf.parse(startDate.toString());
            long ms = Math.abs(startDate.getTime() - now.getTime());
            long result = TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);

            return result < 5;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
