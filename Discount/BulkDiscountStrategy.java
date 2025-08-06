package Discount;
import Lib.*;

public class BulkDiscountStrategy implements DiscountStrategy {
    private final int minimumQuantity;
    private final double discountPercent;
    
    public BulkDiscountStrategy(int minimumQuantity, double discountPercent) {
        this.minimumQuantity = minimumQuantity;
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculatePrice(CartItem item) {
        double originalPrice = item.getProduct().getPrice() * item.getQuantity();
        if (item.getQuantity() >= minimumQuantity) {
            return originalPrice * (1.0-discountPercent);
        }
        return originalPrice;
    }
}
