public class Phrase {
    private String name;
    private String city;

    public Phrase(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String showPhraseBasedOnPattern() {
        return formatName() + " is from " + this.city;
    }

    private String formatName() {
        return this.name.toUpperCase();
    }
}
