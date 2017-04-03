package net.xaviersala;

import com.guigarage.flatterfx.FlatterFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
  private static final String NOM_DEL_PROGRAMA = "New Game of the year";

  @Override
  public void start(Stage primaryStage) {
    try {

      HBox root = (HBox)FXMLLoader.load(getClass().getResource("/Game.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
      primaryStage.setTitle(NOM_DEL_PROGRAMA);
      primaryStage.setScene(scene);
      primaryStage.show();
      FlatterFX.style();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
