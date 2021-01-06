package restleavemanagement.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LeaveRequestService {

    public boolean checkIfLeaveRequestIs5DaysBefore(Date startDate) {
        Date now = new Date();
        long milis = Math.abs(startDate.getTime() - now.getTime());
        long result = TimeUnit.DAYS.convert(milis, TimeUnit.MILLISECONDS);

        return (result > 5) ? false : true;
    }

    public boolean checkIfDateIsSixMonthsEarlier(Date startDate) {
        String now = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        String requestStartDate = new SimpleDateFormat("MM/dd/yyyy").format(startDate);
        long monthsBetween = ChronoUnit.MONTHS.between(
                LocalDate.parse(now).withDayOfMonth(1),
                LocalDate.parse(requestStartDate).withDayOfMonth(1));

        if (monthsBetween > 6) {
            return false;
        }

        return true;
    }

}
