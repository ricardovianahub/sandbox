package domain;

public class DomainObject {
    private int id;
    private String name;

    public DomainObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSignature() {
        return this.id + " - " + this.name;
    }
}
