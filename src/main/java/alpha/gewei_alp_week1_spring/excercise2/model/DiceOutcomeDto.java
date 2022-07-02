package alpha.gewei_alp_week1_spring.excercise2.model;

public class DiceOutcomeDto {

    private Integer sum;

    private Integer times;

    private String stars;

    public void addTime() {

        this.times += 1;
    }

    public DiceOutcomeDto() {
        this.times = 0;
    }

    public void calculateStars() {
        StringBuffer tmpStringBuffer = new StringBuffer("");
        for (int i = 0; i < times; i++) {
            tmpStringBuffer.append("*");
        }
        this.stars = tmpStringBuffer.toString();
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getStars() {
        return stars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiceOutcomeDto that = (DiceOutcomeDto) o;

        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        return sum != null ? sum.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DiceOutcomeDto{" +
                "sum=" + sum +
                ", times=" + times +
                ", stars= " + stars +
                '}';
    }
}
