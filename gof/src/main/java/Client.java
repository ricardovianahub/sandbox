import creational.builder.ComplexObject;
import creational.builder.ComplexObjectBuilder;

public class Client {

    public String consume() {
        ComplexObject complexObject = ComplexObjectBuilder.build(
            "one", "two", "three"
        );

        return complexObject.toString();
    }

}
