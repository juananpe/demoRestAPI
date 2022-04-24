module eus.ehu.demoapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires org.kordamp.bootstrapfx.core;
    requires okhttp3;
    requires com.google.gson;
    requires batik.transcoder;
    requires java.desktop;

    opens eus.ehu.demoapi to javafx.fxml, com.google.gson;

    exports eus.ehu.demoapi;
}
