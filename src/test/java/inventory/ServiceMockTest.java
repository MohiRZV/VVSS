package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryFileRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

public class ServiceMockTest {
    private InventoryFileRepository inventoryFileRepository;

    private InventoryService inventoryService;

    @BeforeEach
    public void setUp(){
        inventoryFileRepository = mock(InventoryFileRepository.class);
        inventoryService = new InventoryService(inventoryFileRepository);
    }

    @Test
    public void test_size_repo_part() throws Exception {
        InhousePart part = new InhousePart(0,"name",20,2,1,3,2);
        InhousePart part2 = new InhousePart(1,"name",5,2,1,3,2);
        Mockito.when(inventoryFileRepository.getAllParts()).thenReturn(FXCollections.observableArrayList(part2));

        Mockito.verify(inventoryFileRepository, never()).getAllParts();

        Mockito.verify(inventoryFileRepository, never()).addPart(part);
        assertEquals(1, inventoryService.getAllParts().size());

        Mockito.verify(inventoryFileRepository, times(1)).getAllParts();
    }

    @Test
    public void test_size_repo_product() throws Exception {
        Product prod1 = new Product(2,"name",20,2,1,3,FXCollections.observableArrayList());
        Product prod2 = new Product(3,"aa",22,2,1,3,FXCollections.observableArrayList());
        Mockito.when(inventoryFileRepository.getAllProducts()).thenReturn(FXCollections.observableArrayList(prod2));

        Mockito.verify(inventoryFileRepository, never()).getAllProducts();

        Mockito.verify(inventoryFileRepository, never()).addProduct(prod1);
        assertEquals(1, inventoryService.getAllProducts().size());

        Mockito.verify(inventoryFileRepository, times(1)).getAllProducts();
    }

}
