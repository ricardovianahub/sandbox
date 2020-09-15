public class MathCalculation {
    public double apply(int argument) {
        if (argument >= 1 && argument <= 100) {
            return argument * 0.9 + 1;
        }
        if (argument >= 101 && argument <= 200) {
            return argument * 0.8 - 4;
        }
        if (argument >= 201 && argument <= 300) {
            return argument * 0.7 + 8;
        }
        throw new IllegalArgumentException();
    }
}
