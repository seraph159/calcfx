//coded by seraph159 aka Kaizer (Discord - Kaizer#9821)

package CalcFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Calc.fxml"));
        primaryStage.setTitle("CalcFx");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("calcTheme.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
