package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Hotel;
import model.Konference;
import model.Udflugt;

public class TilføjUdflugtWindow extends Stage {

    private Controller controller;
    private Konference konference;

    private ListView<Udflugt> lvwUdflugter = new ListView<>();

    public TilføjUdflugtWindow(Controller controller, Konference konference) {
        this.controller = controller;
        this.konference = konference;

        setTitle("Tilføj udflugter til " + konference.getNavn() + " (" + konference.getStartdato() + "-" + konference.getSlutDato() + ")");

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

        Label lblNavn = new Label("Udflugtsnavn");
        TextField txfNavn = new TextField();
        pane.add(lblNavn, 0, 1);
        pane.add(txfNavn, 0, 2);

        Label lblPris = new Label("Pris");
        TextField txfPris = new TextField();
        pane.add(lblPris, 1, 1);
        pane.add(txfPris, 1, 2);

        Label lblBeskrivelse = new Label("Beskrivelse");
        TextField txfBeskrivelse = new TextField();
        pane.add(lblBeskrivelse, 0, 3);
        pane.add(txfBeskrivelse, 0, 4, 2, 1);

        Label lblDato = new Label("Dato");
        DatePicker dpDato = new DatePicker();
        pane.add(lblDato, 0, 5);
        pane.add(dpDato, 0, 6);

        Button btnTilføj = new Button("Tilføj udflugt");
        pane.add(btnTilføj, 1, 6);;

        lvwUdflugter.setPrefHeight(150);
        Label lblUdflugter = new Label("Udflugter tilknyttet konferencen:");
        pane.add(lblUdflugter, 0, 7);
        pane.add(lvwUdflugter,0,8, 2, 1);
        lvwUdflugter.getItems().setAll(konference.getUdflugter());

        Button btnTilbage = new Button("<-- Gå tilbage");
        pane.add(btnTilbage, 0, 9);

        Button btnAfslut = new Button("Afslut");
        pane.add(btnAfslut, 1, 9);


        btnTilføj.setOnAction(e -> {

            if (txfNavn.getText().isEmpty() || txfBeskrivelse.getText().isEmpty() ||
                    txfPris.getText().isEmpty() || dpDato.getValue() == null) {

                showAlert("Fejl", "Udfyld venligst alle felter.");
                return;
            }

            double pris;
            try {
                pris = Double.parseDouble(txfPris.getText());
            } catch (NumberFormatException exception) {
                showAlert("Fejl", "Pris skal være et tal.");
                return;
            }

            Udflugt udflugt = controller.createUdflugt(konference, txfNavn.getText(),
                    txfBeskrivelse.getText(), pris, dpDato.getValue());

            lvwUdflugter.getItems().add(udflugt);

            txfNavn.clear();
            txfBeskrivelse.clear();
            txfPris.clear();
            dpDato.setValue(null);
        });

        btnAfslut.setOnAction(e -> {
            showAlert("Succes", "Konferencen er oprettet.");


            this.close();
        });

        btnTilbage.setOnAction(e -> {
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
