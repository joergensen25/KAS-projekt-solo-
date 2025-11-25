package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Hotel;
import model.HotelService;

public class TilføjServiceWindow extends Stage {

    private Controller controller;
    private Hotel hotel;

    public TilføjServiceWindow(Controller controller, Hotel hotel) {
        this.controller = controller;
        this.hotel = hotel;

        setTitle("Tilføj service til " + hotel.getNavn());

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

        Label lblNavn = new Label("Servicenavn");
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

        Button btnTilføj = new Button("Tilføj service");
        pane.add(btnTilføj, 1, 5);

        btnTilføj.setOnAction(e -> {

            if (txfNavn.getText().isEmpty() || txfPris.getText().isEmpty() || txfBeskrivelse.getText().isEmpty()) {
                showAlert("Fejl", "Udfyld alle felter.");
                return;
            }

            double pris;
            try {
                pris = Double.parseDouble(txfPris.getText());

            } catch (NumberFormatException exception) {
                showAlert("Fejl", "Pris skal være et tal.");
                return;
            }

            HotelService service = controller.createHotelService(txfNavn.getText(), txfBeskrivelse.getText(), pris);

            hotel.addService(service);

            showAlert("Succes", "Servicen er tilføjet til hotellet");
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
