package alpha.gewei_alp_week1_spring.excercise2.model;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    //骰子有幾面
    int diceDimension;

    //有幾粒骰子
    int numberOfDice;

    //骰幾次
    int timeToRoll;

    //骰子 List
    List<Dice> dices = new LinkedList<>();

    //結果 map

    private Map<Integer, DiceOutcomeDto> answerSet = new TreeMap<>();

    public Map<Integer, DiceOutcomeDto> getAnswerSet() {
        return answerSet;
    }


//    private  Map<Integer,Integer> answerMap
//            = new TreeMap<>();
//
//    public  Map<Integer,Integer> getAnswerMap() {
//        return answerMap;
//    }


    public Game(int diceDimension, int numberOfDice, int timeToRoll) {
        this.diceDimension = diceDimension;
        this.numberOfDice = numberOfDice;
        this.timeToRoll = timeToRoll;

        for (int i = 0; i < numberOfDice; i++) {
            dices.add(new Dice(diceDimension));
        }
    }

    public void play() {
        for (int i = 0; i < timeToRoll; i++) {

            //System.out.println(numberOfDice +"顆骰子曬骰" +i + "次");

            Map<Integer, Integer> map = new HashMap<>();

            Integer sumThisRoll = Integer.valueOf(0);

            for (int j = 0; j < numberOfDice; j++) {
                Integer onediceValue = dices.get(j).roll();
                //System.out.println("第"+j+"顆骰子這次骰"+onediceValue);
                sumThisRoll += onediceValue;
                //System.out.println("第"+i+"次總和為"+sumThisRoll);
            }

            //for test

            //System.out.println("before put if absent" + sumThisRoll);
            //answerMap.putIfAbsent( sumThisRoll , 0 );
            //System.out.println("before put if absent"+sumThisRoll);
            //answerMap.computeIfPresent( sumThisRoll , (key,value) ->  value + 1 );

            DiceOutcomeDto diceOutComeThisRound = new DiceOutcomeDto();
            diceOutComeThisRound.setSum(sumThisRoll);

            if (this.answerSet.containsKey(sumThisRoll)) {
                answerSet.get(sumThisRoll).addTime();

            } else {
                diceOutComeThisRound.addTime();
                answerSet.put(sumThisRoll, diceOutComeThisRound);
            }

        }


    }

    public static void main(String[] args) {
        Game game = new Game(6, 3, 100);
        game.play();

        game.getAnswerSet().forEach((sumKey, dtoObject) -> dtoObject.calculateStars());

        //System.out.println(game.getAnswerMap());
        //game.getAnswerSet().forEach((sumKey, dtoObject)-> System.out.println(dtoObject) );
        List<DiceOutcomeDto> result = game.getAnswerSet().values().stream().collect(Collectors.toList());
        result.forEach(dto -> System.out.println(dto));
    }

}
