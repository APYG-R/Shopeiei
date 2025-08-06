package Lib;
import java.util.ArrayList;
import Discount.DefaultPricingStrategy;

/**
 * คลาสสำหรับจัดการโปรโมชั่นและคำนวณราคา
 */

public class PricingService {
    private record StrategyRule(String sku, DiscountStrategy strategy){} 
    private final ArrayList<StrategyRule> strategies = new ArrayList<>();
    private final DiscountStrategy defaultStrateg = new DefaultPricingStrategy();

    /**
     * ลงทะเบียนกลยุทธ์ส่วนลดสำหรับสินค้า sku ที่กำหนด
     * หากมีโปรโมชั่นสำหรับ sku นี่อยู่แล้ว จำถูกแทนที่ด้วยอันใหม่
     * @param sku รหัสสินค้าที่ต้องการผูกกับโปรโมชั่น
     * @param strategy กลยุทธ์ส่วนลดที่จะใช้
     */
    public void addStrategy(String sku, DiscountStrategy strategy) {
        StrategyRule ruleToRemove = null;
        for(StrategyRule rule : strategies) {
            if(rule.sku().equals(sku)) {
                ruleToRemove = rule;
                break;
            }
        }
        if (ruleToRemove != null) {
            strategies.remove(ruleToRemove);
        }
        strategies.add(new StrategyRule(sku, strategy));
    }

    /**
     * คำนวณราคาสุทธิของสินค้า 1 รายการโดยใช้กลยุทธ์ ที่เหมาะสม
     * @param item รายการสินค้าที่ต้องการคำนวณราคา
     * @return ราคาสุทธิหลังหักส่วนลด
     */
    public double calculateItemPrice(CartItem item) {
        String sku = item.getProduct().getProductId();
         for(StrategyRule rule : strategies) {
            if(rule.sku().equals(sku)) {
                return rule.strategy().calculateItemPrice(item);
            }
        }
        return defaultStrateg.calculateItemPrice(item);
    }
}
