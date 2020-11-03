package structural.facade;

public class Module1 {
    private Module11 module11;

    public Module1(Module11 module11) {
        this.module11 = module11;
    }

    public int calculate(int number) {
        return module11.calculate(number + 3);
    }
}
