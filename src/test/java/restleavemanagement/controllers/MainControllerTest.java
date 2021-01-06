package restleavemanagement.controllers;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {


    @Test
    public void test() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        String firstDate1 = new SimpleDateFormat("MM/dd/yyyy").format(new Date());


        Date firstDate =  sdf.parse(firstDate1);
        Date secondDate = sdf.parse("01/6/2021");


        long milis = Math.abs(secondDate.getTime() - firstDate.getTime());
        long result= TimeUnit.DAYS.convert(milis, TimeUnit.MILLISECONDS);

        assertEquals(14,result);
    }

}