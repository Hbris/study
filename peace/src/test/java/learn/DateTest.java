package learn;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

    @Test
    public void  toLocalDate()
    {
        String dateStr = "2021-03-17";

        LocalDate date =  LocalDate.parse(dateStr);

        LocalDate sixMonth = LocalDate.now().plusMonths(6);
        System.out.println(sixMonth);
        System.out.println(date.compareTo(sixMonth));
    }


    @Test
    public static Calendar getToday() throws ParseException
    {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // This seems like quite a hack (date -> string -> date),
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(stringToDate(todayAsString));
        return cal;
    }

    public static Date stringToDate(String strDate) throws ParseException
    {
        Date aDate = null;
        try
        {
            aDate = stringToDate("yyyy-MM-dd", strDate);
        }
        catch (ParseException pe)
        {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return aDate;
    }

    public static Date stringToDate(String aMask, String strDate) throws ParseException
    {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);
        try
        {
            date = df.parse(strDate);
        }
        catch (ParseException pe)
        {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return date;
    }
}
