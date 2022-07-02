package alpha.gewei_alp_week1_spring.excercise1.controller;

import alpha.gewei_alp_week1_spring.excercise1.model.DataForCalendar;
import alpha.gewei_alp_week1_spring.excercise1.model.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;


@RestController
public class DateController {

    @Autowired
    private DateService dateService;


    @GetMapping("/excercise1")
    private DataForCalendar retrunDaysAccordingMonthAndDate(@RequestParam("year")  Integer year,@RequestParam("month")  Integer month) {

        DataForCalendar data = dateService.convert(year, month);

        return data;
    }
}
