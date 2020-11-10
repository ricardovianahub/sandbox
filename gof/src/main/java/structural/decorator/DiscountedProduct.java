package structural.decorator;

public class DiscountedProduct implements Product {
    private final Product product;
    private final double discount;

    public DiscountedProduct(Product product, double discount) {
        this.product = product;
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return this.product.getPrice() * (100 - this.discount) / 100;
    }

    @Override
    public String getName() {
        return this.product.getName() + " - minus " + discount + "%";
    }
}
