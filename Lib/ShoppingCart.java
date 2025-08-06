package Lib;

import java.util.ArrayList;

public class ShoppingCart {
    public ArrayList<CartItem> cartItems = new ArrayList<>();
    private final PricingService service;
    private final ProductCatalog catalog;

    // Rep Invariant (RI):
    //  - ArrayList is not null and cartItems must be unique.
    //
    // Abstraction Function (AF):
    //  - AF(cartItems) = A shopping cart containing items, each represented by a CartItem.

    /**
     * ตรวจสอบว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        if (cartItems == null) {
            throw new RuntimeException("RI violated : cartItems error make sure there are no null");
        }
        for(int i=0; i<cartItems.size(); i++) {
            for(int j=i+1; j<cartItems.size(); j++) {
                if(cartItems.get(i).equals(cartItems.get(j))) {
                    throw new RuntimeException("RI violated : cartItems error make sure there must be unique");
                }
            }
        }
    }

    /**
     * สร้างอ็อบเจกต์ ShoppingCart
     * @param pricingService บริการคำนวณราคา
     * @param productCatalog แคตตาล็อกสินค้า
     */

    public ShoppingCart(PricingService service, ProductCatalog catalog) {
        this.service = service;
        this.catalog = catalog;
        checkRep();
    }

    /**
     * เพิ่มสินค้าในตะกร้า
     * @param sku รหัสสินค้าที่ต้องการเพิ่ม
     * @param quantity จำนวนที่ต้องการเพิ่ม
     */
    public void addItem(String sku, int quantity) {
        if (quantity <= 0) {
            return;
        }

        Product product = catalog.findById(sku);
        if (product == null) {
            return;
        }

        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                checkRep();
                return;
            }
        }

        cartItems.add(new CartItem(product, quantity));
        checkRep();
    }

    public void removeItem(String sku) {
        CartItem toRemove = null;
        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId().equals(sku)) {
                toRemove = item;
                break;
            }
        }
        cartItems.remove(toRemove);
        checkRep();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += service.calculateItemPrice(item);
        }
        return total;
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public void clearCart() {
        cartItems.clear();
        checkRep();
    }
}

