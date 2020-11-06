package structural.proxy;

public class DoActionProxy {
    private DoAction doAction;
    private boolean authorized;

    public DoActionProxy(boolean authorized) {
        this.doAction = new DoAction();
        this.authorized = authorized;
    }

    public String doit() throws IllegalAccessException {
        if (!this.authorized) {
            throw new IllegalAccessException();
        }
        return this.doAction.doit();
    }
}
