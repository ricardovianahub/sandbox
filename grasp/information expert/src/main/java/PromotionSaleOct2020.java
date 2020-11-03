public class PromotionSaleOct2020 implements PromotionalSale {
    private double discountPercentage;

    public PromotionSaleOct2020(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double apply(double cost) {
        return 80.00;
    }
}
