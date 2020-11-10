package structural.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DecoratorTest {

    @Test
    void discountedProductReturnsPriceMinus10Percent() {
        Product product = new ProductBasic("ticket", 100.00);
        assertEquals(100d, product.getPrice());
        assertEquals("ticket", product.getName());

        double discount = 10.00;
        Product discountedProduct = new DiscountedProduct(product, discount);
        assertEquals(90d, discountedProduct.getPrice());
        assertEquals("ticket - minus 10.0%", discountedProduct.getName());

        Product twiceDiscountedProduct = new DiscountedProduct(discountedProduct, discount);
        assertEquals(81d, twiceDiscountedProduct.getPrice());
        assertEquals("ticket - minus 10.0% - minus 10.0%", twiceDiscountedProduct.getName());
    }

}
