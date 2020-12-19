package carshowroomsimulator;

import carshowroomsimulator.data.DataGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("mainAppGUI.fxml"));
       // loader = new FXMLLoader(getClass().getResource("mainAppGUI.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Car Showrooms Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Controller controller = loader.getController();
        DataGenerator dataGenerator = DataGenerator.getInstance();
        assert dataGenerator != null;
        controller.setDataGenerator(dataGenerator);
        controller.setData(dataGenerator);
    }


    public static void main(String[] args) {

        launch(args);
    }
}
