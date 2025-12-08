package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Hotel;
import model.Konference;

public class TilføjHotelWindow extends Stage {

    private Controller controller;
    private Konference konference;

    private ListView<Hotel> lvwHoteller = new ListView<>();

    public TilføjHotelWindow(Controller controller, Konference konference) {
        this.controller = controller;
        this.konference = konference;

        setTitle("Tilføj hoteller til " + konference.getNavn());

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

        Label lblNavn = new Label("Hotelnavn");
        TextField txfNavn = new TextField();

        Label lblEnkelt = new Label("Pris for enkeltværelse");
        TextField txfEnkelt = new TextField();

        Label lblDobbelt = new Label("Pris for dobbeltværelse");
        TextField txfDobbelt = new TextField();

        pane.add(lblNavn, 0, 1);
        pane.add(txfNavn, 0, 2, 2, 1);

        pane.add(lblEnkelt, 0, 3);
        pane.add(txfEnkelt, 0, 4);

        pane.add(lblDobbelt, 1, 3);
        pane.add(txfDobbelt, 1, 4);

        Button btnTilføjHotel = new Button("Tilføj hotel");
        pane.add(btnTilføjHotel, 1, 5);

        Button btnFjernHotel = new Button("Fjern hotel");
        pane.add(btnFjernHotel, 0, 5);

        Label lblHotel = new Label("Hoteller tilknyttet konferencen:");
        pane.add(lblHotel, 0, 6);

        lvwHoteller.setPrefWidth(150);
        pane.add(lvwHoteller, 0, 7, 2, 1);
        lvwHoteller.getItems().setAll(konference.getHoteller());

        Button btnService = new Button("Tilføj service til valgt hotel");
        pane.add(btnService, 0, 8);

        Button btnSkip = new Button("Spring over / gå videre");
        pane.add(btnSkip, 1, 8);

        btnTilføjHotel.setOnAction(e -> {

            if (txfNavn.getText().isEmpty() || txfEnkelt.getText().isEmpty() || txfDobbelt.getText().isEmpty()) {
                showAlert("Fejl", "Udfyld venligst alle felter.");
                return;
            }

            double prisE, prisD;
            try {
                prisE = Double.parseDouble(txfEnkelt.getText());
                prisD = Double.parseDouble(txfDobbelt.getText());

            } catch (NumberFormatException exception) {
                showAlert("Fejl", "Priserne skal være tal.");
                return;
            }

            Hotel hotel = controller.createHotel(konference, txfNavn.getText(), prisE, prisD);

            lvwHoteller.getItems().add(hotel);

            txfNavn.clear();
            txfEnkelt.clear();
            txfDobbelt.clear();
        });

        btnFjernHotel.setOnAction(e -> {
            Hotel valgt = lvwHoteller.getSelectionModel().getSelectedItem();

            if (valgt == null) {
                showAlert("Fejl", "Intet hotel valgt.");
                return;
            }

            konference.getHoteller().remove(valgt);

            lvwHoteller.getItems().setAll(konference.getHoteller());

        });

        btnService.setOnAction(e -> {
            Hotel valgtHotel = lvwHoteller.getSelectionModel().getSelectedItem();

            if (valgtHotel == null) {
                showAlert("Fejl", "Vælg først et hotel fra listen.");
                return;
            }

            new TilføjServiceWindow(controller, valgtHotel, this);
        });

        btnSkip.setOnAction(e -> {
            new TilføjUdflugtWindow(controller, konference);
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
