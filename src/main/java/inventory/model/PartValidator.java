package inventory.model;

public class PartValidator {
    public void validate(Part part) throws Exception {
        String errorMessage = "";
        if(part.getName() == null || part.getName().equals("")) {
            errorMessage += "name is not a string\n";
        }
        if(part.getPrice() < 0.01) {
            errorMessage += "The price must be greater than 0.\n";
        }
        if(part.getMin() > part.getMax()) {
            errorMessage += "The Min value must be less than the Max value.\n";
        }
        if(part.getInStock() < part.getMin() || part.getInStock() > part.getMax()){
            errorMessage+= "inStock not between min and max\n";
        }

        if(!errorMessage.equals("")){
            throw new Exception(errorMessage);
        }
    }
}
