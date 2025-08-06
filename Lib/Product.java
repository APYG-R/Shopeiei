package Lib;
import javax.management.RuntimeErrorException;

/**
 * ADT ที่ไม่เปลี่ยนรูป (Immutable) สำหรับเก็บข้อมูลสินค้า
 * คลาสนี้เป็น final เพื่อป้องกันการสืบทอดและรับประกัน Immutability
 */
public final class Product {
    private final String productId;
    private final String productName;
    private final double price;

    // Rep Invarainy (RI):
    //  - productId and productName are not null or blank.
    //  - price >= 0.
    //
    // Abstraction Function (AF):
    //  - AF(productId, productName, price) = A product with the given ID, name, and price.

    /**
     * ตรวจสอบว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        if (productId == null || productId.isBlank()) {
            throw new RuntimeException("RI violated : productId error make sure there are no null/blank");
        }
        if (productName == null || productName.isBlank()) {
            throw new RuntimeException("RI violated : productName error make sure there are no null/blank");
        }
        if (price < 0 ) {
            throw new RuntimeException("RI violated : price error make sure price value is non-negative");
        }
    }

    /**
     * สร้างอ็อบเจกต์ Product
     * @param productId รหัสสินค้า ห้ามเป็น null/ช่องว่าง
     * @param productName ชื่อสินค้า ห้ามเป็น null/ช่องว่าง
     * @param price ราคา ห้ามติดลบ
     */

    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        checkRep(); // ตรวจสอบความถูกต้องทุกครั้งที่สร้าง
    }

    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return price;
    }
    
    /**
     * เปรียบเทียบ Product สองชิ้นโดยใช้ productId
     * @param obj อ็อบเจกต์ที่ต้องการเปรียบเทียบ
     * @return true หาก productId เหมือนกัน
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // ถ้าอ็อบเจกต์นี้เป็นอ็อบเจกต์เดียวกับที่ส่งเข้ามา ให้ถือว่าเท่ากัน
        if (obj == null || getClass() != obj.getClass()) return false; // ถ้าอ็อบเจกต์ที่ส่งมาเป็น null/classไม่ตรงกัน ก็ไม่เท่ากัน
        Product product = (Product) obj; // แปลงอ็อบเจกต์เป็นclass Product
        return productId.equals(product.productId);
    }
}
