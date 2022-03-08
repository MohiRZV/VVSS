package inventory;

import inventory.repository.InventoryFileRepository;
import inventory.service.InventoryService;
import inventory.controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        InventoryFileRepository repo= new InventoryFileRepository();
        InventoryService service = new InventoryService(repo);
        System.out.println(service.getAllProducts());
        System.out.println(service.getAllParts());
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));

        Parent root=loader.load();
        MainScreenController ctrl=loader.getController();
        ctrl.setService(service);

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}