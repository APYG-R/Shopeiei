package Discount;
import Lib.*;

public class DefaultPricingStrategy implements DiscountStrategy {

    public double calculatePrice(CartItem item) {
        return item.getProduct().getPrice() * item.getQuantity();
    }
}
