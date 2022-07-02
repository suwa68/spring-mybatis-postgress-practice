package alpha.gewei_alp_week1_spring.excercise2.controller;

import alpha.gewei_alp_week1_spring.excercise2.model.DiceOutcomeDto;
import alpha.gewei_alp_week1_spring.excercise2.model.Game;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DiceController {

    @GetMapping("/excercise2")
    private List<DiceOutcomeDto> execute(@RequestParam("dimensions") Integer dimensions
            , @RequestParam("howManyDices") Integer howManyDices, @RequestParam("howManyTimes")Integer howManyTimes) {

        Game newGame = new Game(dimensions, howManyDices, howManyTimes);
        newGame.play();
        List<DiceOutcomeDto> result = newGame.getAnswerSet().values().stream().collect(Collectors.toList());
        result.forEach(dto -> dto.calculateStars());
        return result;
    }
}
