package alpha.gewei_alp_week1_spring.excercise1.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DateService {

    public DataForCalendar convert(int year, int month) {

        System.out.println(year);
        System.out.println(month);
        Calendar calendar = createCalendar(year, month);

        DataForCalendar dataForCalendar = new DataForCalendar();
        dataForCalendar.setSpaces(getHowManySpaces(calendar));
        dataForCalendar.setYear(calendar.get(Calendar.YEAR));
        dataForCalendar.setMonth((calendar.get(Calendar.MONTH)) + 1);
        dataForCalendar.setDaysArrays(createDayList(calendar));

        return dataForCalendar;
    }

    private Calendar createCalendar(int year, int month) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal;
    }

    private int getHowManySpaces(Calendar calendar) {
        //
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayInWhatDay = calendar.get(Calendar.DAY_OF_WEEK);
        int spaces = firstDayInWhatDay - 1;

        return spaces;
    }

    private List<List<Integer>> createDayList(Calendar calendar) {

        List<List<Integer>> lists = new LinkedList<>();
        int thisMonthHasHowManyDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int spaces = getHowManySpaces(calendar);

        int count = spaces;

        List<Integer> list = new LinkedList<>();

        for (int i = 1; i <= thisMonthHasHowManyDays; i++) {
            list.add(i);
            count += 1;
            if (count % 7 == 0 || i == thisMonthHasHowManyDays) {
                lists.add(list);
                list = new ArrayList<>();
            }
        }
        return lists;
    }
}
