package eus.ehu.demoapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("matches.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
    stage.setTitle("REST Client");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
