package structural.facade;

public class ControllerExample {
    private final Module1 module1;
    private final Module2 module2;
    private final CalculationExample calculationExample;

    public ControllerExample(Module1 module1, Module2 module2, CalculationExample calculationExample) {
        this.module1 = module1;
        this.module2 = module2;
        this.calculationExample = calculationExample;
    }

    public int calculate(int number1, int number2) {
        return calculationExample.calculate(
                module1.calculate(number1), module2.calculate(number2)
        );
    }
}
