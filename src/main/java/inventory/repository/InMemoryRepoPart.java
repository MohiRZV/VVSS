package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InMemoryRepoPart {

    // Declare fields
    private ObservableList<Part> allParts;
    private int autoPartId;


    public InMemoryRepoPart(){
        this.allParts= FXCollections.observableArrayList();
        this.autoPartId=0;
    }

    // Declare methods
    /**
     * Add new part to observable list allParts
     * @param part
     */
    public void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * Removes part passed as parameter from allParts
     * @param part
     */
    public void deletePart(Part part) {
        allParts.remove(part);
    }

    /**
     * Accepts search parameter and if an ID or name matches input, that part is returned
     * @param searchItem
     * @return
     */
    public Part lookupPart(String searchItem) {
       if (searchItem.isEmpty())
            return null;
       for(Part p:allParts) {
            if(p.getName().contains(searchItem))
                return p;
            if((p.getPartId()+"").equals(searchItem))
                return p;
        }
        return null;
    }

    /**
     * Update part at given index
     * @param index
     * @param part
     */
    public void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    /**
     * Getter for allParts Observable List
     * @return
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @param list
     */
    public void setAllParts(ObservableList<Part> list) {
        allParts=list;
    }

    /**
     * Method for incrementing part ID to be used to automatically
     * assign ID numbers to parts
     * @return
     */
    public int getAutoPartId() {
        autoPartId++;
        return autoPartId;
    }

    public void setAutoPartId(int id){
        autoPartId=id;
    }
}
