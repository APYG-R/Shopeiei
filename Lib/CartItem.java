package Lib;

/**
 * ADT ที่เปลี่ยนแปลงได้ (Mutable) สำหรับเก็บข้อมูลสินค้า 1 รายการในตะกร้า
 */
public class CartItem {
    private final Product product;
    private int quantity;

    // Rep Invarainy (RI):
    //  - product is not null.
    //  - quantity > 0.
    //
    // Abstraction Dunction (AF):
    //  -AF(product, quantity) = An item in a shopping cart for
    //  with the specified 'quantity'.

    /**
     * ตรวจสอบว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        if (product == null) {
            throw new RuntimeException("RI violated : product error make sure there are no null");
        }
        if (quantity <= 0) {
            throw new RuntimeException("RI violated : price error make sure quantity must have value > 0");
        }
    }

    /**
     * สร้างอ็อบเจกต์ Product
     * @param product สินค้า ห้ามเป็น null
     * @param quantity จำนวนสินค้า ต้องมีค่ามากกว่า 0
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        checkRep();
    }
    
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }

    /**
     * เพิ่มจำนวนสินค้าในรายการ
     * @param amount จำนวนที่ต้องการเพิ่ม (ต้องเป็นค่าบวก)
     */
    public void increaseQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
        checkRep(); //ตรวจสอบหลังการเปลี่ยนแปลงสถานะ
    }
}
