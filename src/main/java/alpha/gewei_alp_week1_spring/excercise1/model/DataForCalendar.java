package alpha.gewei_alp_week1_spring.excercise1.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


public class DataForCalendar {

    private Integer year;

    private Integer month;


    private Integer spaces;

    private List<List<Integer>> daysArrays;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }


    public Integer getSpaces() {
        return spaces;
    }

    public void setSpaces(Integer spaces) {
        this.spaces = spaces;
    }

    public List<List<Integer>> getDaysArrays() {
        return daysArrays;
    }

    public void setDaysArrays(List<List<Integer>> daysArrays) {
        this.daysArrays = daysArrays;
    }
}
