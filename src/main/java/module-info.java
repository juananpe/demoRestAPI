module eus.ehu.demoapi {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.kordamp.bootstrapfx.core;
  requires okhttp3;
  requires com.google.gson;

  opens eus.ehu.demoapi to javafx.fxml, com.google.gson;

  exports eus.ehu.demoapi;
}
