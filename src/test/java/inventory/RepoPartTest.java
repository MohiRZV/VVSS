package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.PartValidator;
import inventory.repository.InMemoryRepoPart;
import inventory.repository.InventoryFileRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class RepoPartTest {
    private InMemoryRepoPart repo;
    private PartValidator partValidator;
    private Part part;
    @BeforeEach
    public void setUp(){
        partValidator = mock(PartValidator.class);
        repo = new InMemoryRepoPart(partValidator);
        part = mock(Part.class);
    }

    @Test
    public void test_add_valid_part() throws Exception {
        Part p = new InhousePart(0,"name",20,2,1,3,2);
        Part p2 = new InhousePart(1,"name",5,2,1,3,2);
        try {
            repo.addPart(p);
            repo.addPart(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert 2 == repo.getAllParts().size();

        Mockito.doNothing().when(partValidator).validate(part);

        try {
            repo.addPart(part);
        } catch (Exception e) {
            assert false;
        }
        Mockito.verify(partValidator, times(1)).validate(part);
    }

    @Test
    public void test_add_invalid_part() throws Exception {
        Part p = new InhousePart(0,"name",20,2,1,3,2);
        Part p2 = new InhousePart(1,"name",5,2,1,3,2);
        try {
            repo.addPart(p);
            repo.addPart(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert 2 == repo.getAllParts().size();

        Mockito.doThrow(new Exception("invalid part")).when(partValidator).validate(part);

        try {
            repo.addPart(part);
            assert false;
        } catch (Exception e) {
            assert 2 == repo.getAllParts().size();
            assert e.getMessage().equals("invalid part");
        }
        Mockito.verify(partValidator, times(1)).validate(part);
    }


}
