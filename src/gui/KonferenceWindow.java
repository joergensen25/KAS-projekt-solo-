package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Konference;

public class KonferenceWindow extends Stage {

    private final Controller controller;

    public KonferenceWindow(Controller controller) {
        this.controller = controller;

        setTitle("Opret ny konference");
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
        show();
    }

    private void initContent(GridPane pane) {

        Label lblNavn = new Label("Konferencenavn");
        TextField txfNavn = new TextField();
        pane.add(lblNavn, 0, 1);
        pane.add(txfNavn, 0, 2, 2, 1);

        Label lblSted = new Label("Lokation");
        TextField txfSted = new TextField();
        pane.add(lblSted, 0 , 3);
        pane.add(txfSted, 0, 4);

        Label lblPris = new Label("Dagspris");
        TextField txfPris = new TextField();
        pane.add(lblPris, 1, 3);
        pane.add(txfPris, 1, 4);

        Label lblStartdato = new Label("Startdato");
        DatePicker dpStart = new DatePicker();
        pane.add(lblStartdato, 0, 5);
        pane.add(dpStart, 0, 6);

        Label lblSlutdato = new Label("Slutdato");
        DatePicker dpSlut = new DatePicker();
        pane.add(lblSlutdato, 1, 5);
        pane.add(dpSlut, 1, 6);

        Button btnOpretKonf = new Button("Opret konference");
        pane.add(btnOpretKonf, 0, 7);

        btnOpretKonf.setOnAction(e -> {

            if (txfNavn.getText().isEmpty() || txfSted.getText().isEmpty() || txfPris.getText().isEmpty()
                    || dpStart.getValue() == null || dpSlut.getValue() == null) {
                showAlert("Fejl", "Udfyld venligst alle tekstfelter.");
                return;
            }

            double pris;
            try {
                pris = Double.parseDouble(txfPris.getText());
            } catch (NumberFormatException exception) {
                showAlert("Fejl", "Dagspris skal være et tal");
                return;
            }

            Konference konference = controller.createKonference(txfNavn.getText(), txfSted.getText(), dpStart.getValue(), dpSlut.getValue(), pris);

            new TilføjHotelWindow(controller, konference);

            this.close();
        });


    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
