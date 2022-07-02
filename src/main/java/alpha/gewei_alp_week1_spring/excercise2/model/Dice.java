package alpha.gewei_alp_week1_spring.excercise2.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;


public class Dice {

    int max;

    Random random = new Random();

    public Dice(int max) {
        this.max = max;
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }

}
