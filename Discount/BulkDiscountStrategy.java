package Discount;
import Lib.*;

/**
 *  ซื้อเยอะยิ่งลด
 */

public class BulkDiscountStrategy implements DiscountStrategy {
    private final int minimumQuantity;
    private final double discountPercent;
    
    public BulkDiscountStrategy(int minimumQuantity, double discountPercent) {
        this.minimumQuantity = minimumQuantity;
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateItemPrice(CartItem item) {
        double originalPrice = item.getProduct().getPrice() * item.getQuantity();
        if (item.getQuantity() >= minimumQuantity) {
            return originalPrice * (1.0-discountPercent);
        }
        return originalPrice;
    }
}
