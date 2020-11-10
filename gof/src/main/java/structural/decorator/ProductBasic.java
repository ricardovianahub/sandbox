package structural.decorator;

public class ProductBasic implements Product {
    private final String name;
    private final double price;

    public ProductBasic(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
