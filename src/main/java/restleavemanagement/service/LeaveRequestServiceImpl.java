package restleavemanagement.service;

import org.springframework.stereotype.Service;
import restleavemanagement.dto.LeaveRequestDto;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.repository.LeaveRequestRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public LeaveRequest save(LeaveRequestDto registrationDto) {
        LeaveRequest leaveRequest = new LeaveRequest(registrationDto.getStartDate(),registrationDto.getEndDate(),registrationDto.getStatus(),registrationDto.getNrStatus(),registrationDto.getPerson());

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public boolean createLeaveRequest(LeaveRequestDto leaveRequestDto) throws Exception {
        if (requestLengthIsHigherThanSixMonths(leaveRequestDto.getStartDate(), leaveRequestDto.getEndDate())) {
            System.out.println("Intra in requestLengthIsHigherThanSixMonths si returneaza: " + requestLengthIsHigherThanSixMonths(leaveRequestDto.getStartDate(), leaveRequestDto.getEndDate()));
            throw new Exception("You can't create a leave request longer than 6 months.");
        }

        if (startDateIsFiveDaysAhead(leaveRequestDto.getStartDate())) {
            System.out.println("Intra in startDateIsFiveDaysAhead si returneaza: " + startDateIsFiveDaysAhead(leaveRequestDto.getStartDate()));
            throw new Exception("You can't create a leave request 5 days before starting date.");
        }

        LeaveRequest leaveRequest = new LeaveRequest(leaveRequestDto.getStartDate(),leaveRequestDto.getEndDate(),"PENDING",0,leaveRequestDto.getPerson());


        save(leaveRequestDto);

        return true;
    }

    private boolean requestLengthIsHigherThanSixMonths(Date startDate, Date endDate) {
        long ms = Math.abs(endDate.getTime() - startDate.getTime());
        long result = TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);
        return result > 180;
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
