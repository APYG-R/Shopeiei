package Discount;
import Lib.*;

/**
 *  1 แถม 1
 */

public class BogoDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateItemPrice(CartItem item) {
        int quantity = item.getQuantity();
        double price = item.getProduct().getPrice();
        int quantityToPay = quantity / 2 + quantity % 2;
        return price * quantityToPay;
    }
}
