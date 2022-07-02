package alpha.gewei_alp_week1_spring.excercise3.exception;


public class OutOfStockException extends Exception {

    private static final long serialVersionUID = 1L;

    public OutOfStockException(String message) {
        super(message);
    }
}
