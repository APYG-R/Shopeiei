package Lib;

import java.util.*;

public class ProductCatalog {
    public ArrayList<Product> products = new ArrayList<>();

    // Rep Invariant (RI):
    //  - ArrayList is not null and product must be unique.
    //
    // Abstraction Function (AF):
    //  - AF(products) = A catalog of all available products.

    /**
     * ตรวจสอบว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        if (products == null) {
            throw new RuntimeException("RI violated : products error make sure there are no null");
        }
        for(int i=0; i<products.size(); i++) {
            for(int j=i+1; j<products.size(); j++) {
                if(products.get(i).equals(products.get(j))) {
                    throw new RuntimeException("RI violated : products error make sure there must be unique");
                }
            }
        }
    }

    /**
     * สร้างอ็อบเจกต์ Product
     * @param products สินค้า ห้ามเป็น null
     */
    public ProductCatalog() {
        checkRep();
    }

     /**
     * เพิ่มจำนวนสินค้าในรายการ
     * @param productId รหัสสินค้าที่ต้องการค้นหา
     * @return อ็อบเจกต์ Product หากพบ , หรือ null หากไม่พบ
     */
    public void addProduct(Product product){
        if (product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }
    public Product findById(String productId){
        for(Product p : products){
            if(p.getProductId().equals(productId)) return p;
        }
        return null;
    }
}
