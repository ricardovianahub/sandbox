package structural.facade;

public class FacadeExample {
    private Module11 module11;
    private Module2 module2;

    public FacadeExample(Module11 module11, Module2 module2) {
        this.module11 = module11;
        this.module2 = module2;
    }

    public double calculatePercent(int number1, int number2) {
        return (module11.calculate(number1) - module2.calculate(number2)) / 100d;
    }
}
