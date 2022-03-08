package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InMemoryRepoProduct {
    // Declare fields
    private ObservableList<Product> products;
    private int autoProductId;

    public InMemoryRepoProduct(){
        this.products = FXCollections.observableArrayList();
        this.autoProductId=0;
    }

    // Declare methods
    /**
     * Add new product to observable list products
     * @param product
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Remove product from observable list products
     * @param product
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * Accepts search parameter and if an ID or name matches input, that product is returned
     * @param searchItem
     * @return
     */
    public Product lookupProduct(String searchItem) {
        for(Product p: products) {
            if(p.getName().contains(searchItem) || (p.getProductId()+"").equals(searchItem)) return p;
        }
        return null;
    }

    /**
     * Update product at given index
     * @param index
     * @param product
     */
    public void updateProduct(int index, Product product) {
        products.set(index, product);
    }

    /**
     * Getter for Product Observable List
     * @return
     */
    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> list) {
        products=list;
    }

    /**
     * Method for incrementing product ID to be used to automatically
     * assign ID numbers to products
     * @return
     */
    public int getAutoProductId() {
        autoProductId++;
        return autoProductId;
    }

    public void setAutoProductId(int id){
        autoProductId=id;
    }
}
