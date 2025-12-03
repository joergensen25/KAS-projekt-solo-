package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Hotel;
import model.HotelService;

public class TilføjServiceWindow extends Stage {

    private Controller controller;
    private Hotel hotel;
    private ListView<HotelService> lvwServices = new ListView<>();

    public TilføjServiceWindow(Controller controller, Hotel hotel, Stage parentWindow) {
        this.controller = controller;
        this.hotel = hotel;

        setTitle("Tilføj service til " + hotel.getNavn());

        initModality(Modality.APPLICATION_MODAL);
        initOwner(parentWindow);

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

        Label lblServices = new Label("Services tilknyttet hotellet:");
        lvwServices.setPrefWidth(150);
        pane.add(lblServices, 0, 6);
        pane.add(lvwServices, 0, 7, 2, 1);

        lvwServices.getItems().setAll(hotel.getServices());

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

            lvwServices.getItems().add(service);

            txfBeskrivelse.clear();
            txfNavn.clear();
            txfPris.clear();

            showAlert("Succes", "Servicen er tilføjet til hotellet");
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
