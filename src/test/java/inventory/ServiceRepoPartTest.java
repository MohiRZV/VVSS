package inventory;

import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import inventory.model.PartValidator;
import inventory.repository.InventoryFileRepository;
import inventory.service.InventoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ServiceRepoPartTest {private InventoryFileRepository inventoryFileRepository;
    private PartValidator partValidator;
    private InventoryService inventoryService;


    @Before
    public void setUp(){
        partValidator = mock(PartValidator.class);
        inventoryFileRepository = new InventoryFileRepository(partValidator);
        inventoryService = new InventoryService(inventoryFileRepository);
    }

    @Test
    public void test_add_part() throws Exception{
        Mockito.doNothing().when(partValidator).validate(any());

        int initialSize = inventoryService.getAllParts().size();

        inventoryService.addInhousePart("name", 20, 2, 1, 3, 2);
        assert inventoryService.getAllParts().size() == 1 + initialSize;
        assert inventoryFileRepository.getAllParts().size() == 1 + initialSize;
        Mockito.verify(partValidator, (times(1))).validate(any());

        inventoryService.addOutsourcePart("name", 20, 2, 1, 3, "2");
        assert inventoryService.getAllParts().size() == 2 + initialSize;
        assert inventoryFileRepository.getAllParts().size() == 2 + initialSize;
        Mockito.verify(partValidator, (times(2))).validate(any());
    }

    @Test
    public void test_delete_part() throws Exception{
        Part p1 = new OutsourcedPart(100, "name", 20.0, 2, 1, 3, "ab");
        Part p2 = new InhousePart(102, "name", 20.0, 2, 1, 3, 1);

        inventoryFileRepository.addPart(p1);
        inventoryFileRepository.addPart(p2);
        int currentSize = inventoryService.getAllParts().size();
        inventoryService.deletePart(p1);
        assert inventoryService.getAllParts().size() == currentSize - 1;
        inventoryService.deletePart(p2);
        assert inventoryService.getAllParts().size() == currentSize - 2;

    }
}
