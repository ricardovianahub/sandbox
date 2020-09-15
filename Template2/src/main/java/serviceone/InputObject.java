package serviceone;

public class InputObject {
    private String id;
    private String name;

    public InputObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}
