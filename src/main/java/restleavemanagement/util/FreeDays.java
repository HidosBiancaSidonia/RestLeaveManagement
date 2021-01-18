package restleavemanagement.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FreeDays {
    private ArrayList<Date> freeDays = new ArrayList<>();


    public FreeDays() {
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JANUARY, 2).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JANUARY, 24).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JANUARY, 27).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.APRIL, 30).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.MAY, 5).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.MAY, 2).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.MAY, 3).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JUNE, 1).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JUNE, 20).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.JUNE, 21).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.AUGUST, 15).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.NOVEMBER, 30).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.DECEMBER, 1).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.DECEMBER, 25).getTime());
        this.freeDays.add(new GregorianCalendar(2021, Calendar.DECEMBER, 26).getTime());


    }

    public ArrayList<Date> getFreeDays() {
        return freeDays;
    }

    @Override
    public String toString() {
        return "FreeDays{" +
                "freeDays=" + freeDays +
                '}';
    }
}
