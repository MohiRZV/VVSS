package inventory;

import inventory.model.Part;
import inventory.model.PartValidator;
import inventory.repository.InventoryFileRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LookupPartTest {
    static private InventoryFileRepository repo;

    @BeforeAll
    static void setUp(){
        repo = new InventoryFileRepository(new PartValidator());
    }

    @Test
    void lookupPart_emptyKey_returnsNull() {
        try {
            Part part = repo.lookupPart("");

            assert (part == null);
        }catch (Exception ex){
            assert(false);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"})
    void lookupPart_firstElementMatchesQuery_returnFirstElement(String keyword) {
        try {
            Part part = repo.lookupPart(keyword);

            assert (part.getName().equals("part1"));
        }catch (Exception ex){
            assert(false);
        }
    }

    @Test
    void lookupPart_elementIdMatchesQuery_returnElement() {
        String keyword = "1";
        try {
            Part part = repo.lookupPart(keyword);

            assert (part.getPartId()==1);
        }catch (Exception ex){
            assert(false);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"123"})
    void lookupPart_nothingMatchesQuery_returnNull(String keyword) {
        try {
            Part part = repo.lookupPart(keyword);

            assert (part == null);
        }catch (Exception ex){
            assert(false);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Spr"})
    void lookupPart_secondElementMatchesQuery_returnSecondElement(String keyword) {
        try {
            Part part = repo.lookupPart(keyword);

            assert (part.getPartId() == 2);
        }catch (Exception ex){
            assert(false);
        }
    }
}
