package creational.builder;

public class ComplexObjectBuilder {
    public static ComplexObject build(String first, String second, String third) {
        ComplexObject complexObject = new ComplexObject();
        complexObject.setFirst(calculateFirst(first));
        complexObject.setSecond(second);
        complexObject.setThird(third);
        return complexObject;
    }

    private static String calculateFirst(String first) {
        return first;
    }
}
