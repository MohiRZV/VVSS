package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.PartValidator;
import inventory.model.Product;
import inventory.repository.InventoryFileRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceRepoTest {
    private InventoryFileRepository inventoryFileRepository;
    private PartValidator partValidator;
    private InventoryService inventoryService;
    private Part p1;
    private Part p2;

    @Before
    public void setUp(){
        partValidator = mock(PartValidator.class);
        inventoryFileRepository = new InventoryFileRepository(partValidator);
        inventoryService = new InventoryService(inventoryFileRepository);
        p1 = mock(Part.class);
        p2 = mock(Part.class);
    }


    @Test
    public void test_add_part() throws Exception{
        Mockito.doNothing().when(partValidator).validate(any());

        assert inventoryService.getAllParts().size() == 0;

        inventoryService.addInhousePart("name", 20, 2, 1, 3, 2);
        assert inventoryService.getAllParts().size() == 1;
        assert inventoryFileRepository.getAllParts().size() == 1;
        Mockito.verify(partValidator, (times(1))).validate(any());

        inventoryService.addOutsourcePart("name", 20, 2, 1, 3, "2");
        assert inventoryService.getAllParts().size() == 2;
        assert inventoryFileRepository.getAllParts().size() == 2;
        Mockito.verify(partValidator, (times(2))).validate(any());

    }

}
