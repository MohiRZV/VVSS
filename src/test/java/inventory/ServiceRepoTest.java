package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.PartValidator;
import inventory.model.Product;
import inventory.repository.InventoryFileRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceRepoTest {
    private InventoryFileRepository inventoryFileRepository;
    private PartValidator partValidator;
    private InventoryService inventoryService;
    private Part p1;
    private Part p2;

    @BeforeEach
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
        Mockito.when(p1.getName()).thenReturn("name");
        Mockito.when(p1.getPartId()).thenReturn(100);
        Mockito.when(p1.getInStock()).thenReturn(2);
        Mockito.when(p1.getMax()).thenReturn(3);
        Mockito.when(p1.getMin()).thenReturn(1);
        Mockito.when(p1.getPrice()).thenReturn(20.0);
        Mockito.when(p1.toString()).thenReturn("O,100,name,20.0,2,1,3,ab");

        Mockito.when(p2.getName()).thenReturn("name");
        Mockito.when(p2.getPartId()).thenReturn(102);
        Mockito.when(p2.getInStock()).thenReturn(2);
        Mockito.when(p2.getMax()).thenReturn(3);
        Mockito.when(p2.getMin()).thenReturn(1);
        Mockito.when(p2.getPrice()).thenReturn(20.0);
        Mockito.when(p2.toString()).thenReturn("I,102,name,20.0,2,1,3,1");

        inventoryFileRepository.addPart(p1);
        inventoryFileRepository.addPart(p2);
        int currentSize = inventoryService.getAllParts().size();
        inventoryService.deletePart(p1);
        assert inventoryService.getAllParts().size() == currentSize - 1;
        inventoryService.deletePart(p2);
        assert inventoryService.getAllParts().size() == currentSize - 2;

    }

}
