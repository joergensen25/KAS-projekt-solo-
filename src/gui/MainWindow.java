package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainWindow extends Application {
    private Controller controller = new Controller();

    public void start(Stage stage) {
        controller.initData();

        stage.setTitle("Konference Administration System - Hovedmenu");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    private void initContent(GridPane pane) {

        Button btnTilmeld = new Button("Tilmeld konference");
        pane.add(btnTilmeld, 0, 0);

        Button btnOpretKonf = new Button("Opret konference");
        pane.add(btnOpretKonf, 0, 1);

        Button btnAdmin = new Button("Administration");
        pane.add(btnAdmin, 0, 2);

        btnTilmeld.setOnAction(e -> new TilmeldingWindow(controller));
        btnOpretKonf.setOnAction(e -> new KonferenceWindow(controller));
        btnAdmin.setOnAction(e -> new OversigtWindow());

    }

}
