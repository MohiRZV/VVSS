package inventory.service;

import inventory.model.InhousePart;
import inventory.model.PartValidator;
import inventory.repository.InMemoryRepoPart;
import inventory.repository.InventoryFileRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    static private InventoryService service;
    static private String name;
    static private final double price = 42.01;
    static private int inStock;
    static private final int min = 2;
    static private final int max = 30;
    static private final int partDynamicValue = 16;
    @BeforeAll
    static void setUp(){
        service = new InventoryService(new InventoryFileRepository(new PartValidator()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"X", "Chestie"})
    void addPart_validName(String name) {
        inStock = 15;
        try {
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
            InhousePart p = (InhousePart) service.getAllParts().get(service.getAllParts().size() - 1);

            assert (name == p.getName() && price == p.getPrice() && inStock == p.getInStock() && min == p.getMin() && max == p.getMax() && partDynamicValue == p.getMachineId());
        }catch (Exception ex){
            assert(false);
        }
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    void addPart_invalidName(String name){
        inStock = 15;
        try {
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
            assert(false);
        }catch (Exception ex){
            assert(ex.getMessage().equals("name is not a string\n"));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {max})
    void addPart_validInStock(int inStock){
        name = "Chestie";

        try {
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
            InhousePart p = (InhousePart) service.getAllParts().get(service.getAllParts().size() - 1);

            assert (name == p.getName() && price == p.getPrice() && inStock == p.getInStock() && min == p.getMin() && max == p.getMax() && partDynamicValue == p.getMachineId());
        }catch(Exception ex){
            assert(false);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {min -1, 70})
    void addPart_invalidInStock(int inStock){
        name = "Chestie";

        try {
            service.addInhousePart(name, price, inStock, min, max, partDynamicValue);
            assert(false);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            assert (ex.getMessage().equals("inStock not between min and max\n"));
        }
    }


    @AfterAll
    @Disabled
    static void tearDown(){

    }
}