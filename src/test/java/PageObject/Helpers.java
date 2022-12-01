package PageObject;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.Date;

public class Helpers { //Класс для вспомогательных инструментов не относящийхся к тестам
    //Получить дату через @Param кол-во дней
    public static String getCalendarDate(int amountOfSkipDays) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yy");
        Date dt = new Date();
        DateTime dtOrg = new DateTime(dt);
        DateTime dtPlusOne = dtOrg.plusDays(amountOfSkipDays);
        return dtPlusOne.toString(fmt);
    }
}
