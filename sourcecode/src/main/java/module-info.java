module com.simulation.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.swing;
    requires java.desktop;

    opens com.simulation.view to javafx.fxml;
    opens com.simulation.controller to javafx.fxml;
    opens com.simulation.util to javafx.fxml;
    exports com.simulation.util;
    exports com.simulation.controller;
    exports com.simulation.view;
}