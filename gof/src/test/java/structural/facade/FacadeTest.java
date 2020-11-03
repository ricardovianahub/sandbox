package structural.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FacadeTest {

    @Test
    void calculateReturnsValueFromFullFormula() {
        ControllerExample controllerExample = new ControllerExample(
                new Module1(new Module11()), new Module2(), new CalculationExample()
        );

        int parameter1 = 10;
        int parameter2 = 20;

        assertEquals(52, controllerExample.calculate(parameter1, parameter2));
    }

    @Test
    void facadeCombinesModule11WithModule2InPercent() {
        FacadeExample facadeExample = new FacadeExample(new Module11(), new Module2());
        assertEquals(0.44, facadeExample.calculatePercent(50, 50));
    }
}
