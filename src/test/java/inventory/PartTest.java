package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import org.junit.jupiter.api.Test;


public class PartTest {

    @Test
    public void validatePart_noErrorMessage(){
        Part part = new InhousePart(1,"name",20,2,1,3,2);
        String errorMessage = Part.isValidPart(part.getName(),part.getPrice(),part.getInStock(),part.getMin(),
                part.getMax(),"");
        assert errorMessage.equals("");
    }

    @Test
    public void validatePart_priceLessThanZero(){
        Part part = new InhousePart(1,"name",-5,2,1,3,2);
        String errorMessage = Part.isValidPart(part.getName(),part.getPrice(),part.getInStock(),part.getMin(),
                part.getMax(),"");
        assert errorMessage.equals("The price must be greater than 0.\n");
    }
}
