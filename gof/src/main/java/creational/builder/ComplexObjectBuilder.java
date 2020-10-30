package creational.builder;

public class ComplexObjectBuilder {
    public static ComplexObject build(String first, String second, String third) {
        ComplexObject complexObject = new ComplexObject();
        complexObject.setFirst(first);
        complexObject.setSecond(second);
        return complexObject;
    }
}
