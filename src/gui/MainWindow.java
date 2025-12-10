package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainWindow extends Application {

    private Controller controller = new Controller();

    public void start(Stage stage) {
        controller.initData();

        stage.setTitle("Konference Administration System - Hovedmenu");


        GridPane pane = new GridPane();
        pane.setPadding(new Insets(40));
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.setVgap(20);


        initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    private void initContent(GridPane pane) {

        Label lblTitle = new Label("Konference Administrationssystem");
        lblTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333;");
        pane.add(lblTitle, 0, 0);

        Label lblSubtitle = new Label("Vælg en funktion nedenfor");
        lblSubtitle.setStyle("-fx-font-size: 16px; -fx-text-fill: #666;");
        pane.add(lblSubtitle, 0, 1);

        VBox menuBox = new VBox(20);
        menuBox.setPadding(new Insets (30));
        menuBox.setStyle("-fx-background-color: #ffffff; " +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #ccc;" +
                "-fx-border-width: 1.5;");
        menuBox.setAlignment(Pos.CENTER);


        Button btnTilmeld = new Button("Tilmeld konference");
        btnTilmeld.setMinWidth(200);
        btnTilmeld.setStyle("-fx-font-size: 14px;");

        Button btnOpretKonf = new Button("Opret konference");
        btnOpretKonf.setMinWidth(200);
        btnOpretKonf.setStyle("-fx-font-size: 14px;");

        Button btnAdmin = new Button("Administration");
        btnAdmin.setMinWidth(200);
        btnAdmin.setStyle("-fx-font-size: 14px;");

        menuBox.getChildren().addAll(btnTilmeld, btnOpretKonf, btnAdmin);
        pane.add(menuBox, 0, 2);


        btnTilmeld.setOnAction(e -> new TilmeldingWindow(controller));
        btnOpretKonf.setOnAction(e -> new KonferenceWindow(controller));
        btnAdmin.setOnAction(e -> new OversigtWindow(controller));

    }

    private void styleMainButton(Button btn) {
        btn.setMinWidth(220);
        btn.setMinHeight(40);
        btn.setStyle("-fx-font-size: 15px;" +
                "-fx-background-color: #3A7BDC;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;");

        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-font-size: 15px;" +
                    "-fx-background-color: #2F6AC9; " +
                    "-fx-text-fill: white; " +
                    "-fx-background-radius: 8;");
        }
        );

        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-font-size: 15px; " +
                    "-fx-background-color: #3A7BDC; " +
                    "-fx-text-fill: white; " +
                    "-fx-background-radius: 8;");
        });

    }

}
