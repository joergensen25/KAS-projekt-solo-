package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

public class TilmeldingWindow extends Stage {


    private final Controller controller;
    private final ArrayList<CheckBox> udflugtCheckBoxes = new ArrayList<>();
    private final ArrayList<CheckBox> serviceCheckBoxes = new ArrayList<>();

    public TilmeldingWindow(Controller controller) {
        this.controller = controller;

        setTitle("Tilmeld deltager");

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
        sizeToScene();

        show();
    }

    private void initContent(GridPane pane) {

        // Standardinfo:
        Label lblNavn = new Label("Navn");
        TextField txfNavn = new TextField();
        pane.add(lblNavn, 0, 1);
        pane.add(txfNavn, 0, 2);

        Label lblNationalitet = new Label("Nationalitet");
        TextField txfNationalitet = new TextField();
        pane.add(lblNationalitet, 1, 1);
        pane.add(txfNationalitet, 1, 2);

        Label lblAdresse = new Label("Adresse");
        TextField txfAdresse = new TextField();
        pane.add(lblAdresse, 0, 3);
        pane.add(txfAdresse, 0, 4, 2, 1);

        Label lblForedrag = new Label("Er du foredragsholder?");
        CheckBox chkForedrag = new CheckBox("Kryds af for 'ja'");
        pane.add(lblForedrag, 1, 5);
        pane.add(chkForedrag, 1, 6);

        Label lblTelefon = new Label("Telefon");
        TextField txfTelefon = new TextField();
        pane.add(lblTelefon, 0, 5);
        pane.add(txfTelefon, 0, 6);

        // Firmainfo:
        CheckBox chkFirma = new CheckBox("Tilknyttet et firma?");
        Label lblFirmanavn = new Label("Firmanavn");
        TextField txfFirmanavn = new TextField();
        Label lblFirmatlf = new Label("Firmatelefon");
        TextField txfFirmatlf = new TextField();

        pane.add(chkFirma, 0, 7);
        pane.add(lblFirmanavn, 0, 8);
        pane.add(txfFirmanavn, 0, 9);
        pane.add(lblFirmatlf, 0, 10);
        pane.add(txfFirmatlf, 0, 11);

        lblFirmanavn.setVisible(false);
        txfFirmanavn.setVisible(false);
        lblFirmatlf.setVisible(false);
        txfFirmatlf.setVisible(false);

        chkFirma.setOnAction(e -> {
            boolean valgt = chkFirma.isSelected();
            lblFirmanavn.setVisible(valgt);
            txfFirmanavn.setVisible(valgt);
            lblFirmatlf.setVisible(valgt);
            txfFirmatlf.setVisible(valgt);
        });


        // Ledsagerinfo:
        CheckBox chkLedsager = new CheckBox("Medbringer du en ledsager?");
        Label lblLedsager = new Label("Ledsagernavn");
        TextField txfLedsager = new TextField();

        pane.add(chkLedsager, 1, 7);
        pane.add(lblLedsager, 1, 8);
        pane.add(txfLedsager, 1, 9);

        lblLedsager.setVisible(false);
        txfLedsager.setVisible(false);


        // Konferenceinfo:
        Label lblKonference = new Label("Vælg konference");
        ComboBox<Konference> cmbKonference = new ComboBox<>();
        cmbKonference.getItems().addAll(controller.getStorage().getKonferencer());

        pane.add(lblKonference, 0, 12);
        pane.add(cmbKonference, 0, 13);

        // Datoer:
        Label lblAnkomst = new Label("Ankomstdato");
        DatePicker dpAnkomst = new DatePicker();
        Label lblAfrejse = new Label("Afrejsedato");
        DatePicker dpAfrejse = new DatePicker();

        pane.add(lblAnkomst, 0, 14);
        pane.add(dpAnkomst, 0, 15);
        pane.add(lblAfrejse, 1, 14);
        pane.add(dpAfrejse, 1, 15);


        // Hotel:
        Label lblHotel = new Label("Vælg hotel");
        ComboBox<Hotel> cmbHotel = new ComboBox<>();
        lblHotel.setVisible(false);
        cmbHotel.setVisible(false);

        pane.add(lblHotel, 0, 16);
        pane.add(cmbHotel, 0, 17);

        Label lblServices = new Label("Hotelservices:");
        VBox serviceBox = new VBox(5);
        lblServices.setVisible(false);
        serviceBox.setVisible(false);


        pane.add(lblServices, 1, 16);
        pane.add(serviceBox, 1, 17);


        // Udflugter:
        Label lblUdflugter = new Label("Udflugter for ledsager");
        VBox udflugtBox = new VBox(5);
        lblUdflugter.setVisible(false);
        udflugtBox.setVisible(false);

        pane.add(lblUdflugter, 0, 18);
        pane.add(udflugtBox, 0, 19);


        chkLedsager.setOnAction(e -> {
            boolean ledsager = chkLedsager.isSelected();

                lblLedsager.setVisible(ledsager);
                txfLedsager.setVisible(ledsager);
                lblUdflugter.setVisible(ledsager);
                udflugtBox.setVisible(ledsager);

                sizeToScene();

        });

        cmbKonference.setOnAction(e -> {
            Konference valgt = cmbKonference.getValue();

            cmbHotel.getItems().setAll(valgt.getHoteller());
            lblHotel.setVisible(true);
            cmbHotel.setVisible(true);

            cmbHotel.setOnAction(event -> {
                Hotel hotel = cmbHotel.getValue();

                serviceBox.getChildren().clear();
                serviceCheckBoxes.clear();
                if (hotel != null) {
                    lblServices.setVisible(true);
                    serviceBox.setVisible(true);


                    for (HotelService hotelService : hotel.getServices()) {
                        CheckBox cb = new CheckBox(hotelService.getNavn() + " (" + hotelService.getPris() + " kr)");
                        cb.setUserData(hotelService);
                        serviceBox.getChildren().add(cb);
                        serviceCheckBoxes.add(cb);
                    }
                }
                sizeToScene();
            });

            udflugtBox.getChildren().clear();
            udflugtCheckBoxes.clear();

            for (Udflugt udflugt : valgt.getUdflugter()) {
                CheckBox cb = new CheckBox(udflugt.getNavn() + " (" + udflugt.getPris() + " kr.)");
                cb.setUserData(udflugt);
                udflugtCheckBoxes.add(cb);
                udflugtBox.getChildren().add(cb);
            }
            sizeToScene();
        });


        Button btnTilmeld = new Button("Opret tilmelding");
        pane.add(btnTilmeld,1, 20);
        btnTilmeld.setOnAction(e -> {

            if (txfNavn.getText().isEmpty() || txfAdresse.getText().isEmpty() ||
                    txfNationalitet.getText().isEmpty() || txfTelefon.getText().isEmpty()) {
                showAlert("Fejl", "Udfyld nødvendige tekstfelter.");
                return;
            }
            if (cmbKonference.getValue() == null) {
                showAlert("Fejl", "Vælg en konference.");
                return;
            }
            if (dpAnkomst.getValue() == null || dpAfrejse.getValue() == null) {
                showAlert("Fejl", "Vælg ankomst- og afrejsedato.");
                return;
            }


            String firmanavn = chkFirma.isSelected() ? txfFirmanavn.getText() : "";
            String firmatlf = chkFirma.isSelected() ? txfFirmatlf.getText() : "";

            Deltager deltager = controller.createDeltager(txfNavn.getText(), txfAdresse.getText(),
                    txfNationalitet.getText(), txfTelefon.getText(), firmanavn, firmatlf);


            Konference konference = cmbKonference.getValue();

            boolean erForedragsholder = chkForedrag.isSelected();

            Tilmelding tilmelding = controller.createTilmelding(konference, deltager, dpAnkomst.getValue(),
                    dpAfrejse.getValue(), erForedragsholder);


            // Opretter ledsager:
            Ledsager ledsager = null;

            if (chkLedsager.isSelected()) {
                if (txfLedsager.getText().isEmpty()) {
                    showAlert("Fejl", "Indtast ledsagerens navn eller fjern afkrydsning.");
                    return;
                }
                ledsager = controller.createLedsager(tilmelding, txfLedsager.getText());
            }


            if (ledsager != null) {
                for (CheckBox cb : udflugtCheckBoxes) {
                    if (cb.isSelected()) {
                        Udflugt udflugt = (Udflugt) cb.getUserData();
                        udflugt.addTilmelding(new UdflugtTilmelding(ledsager, udflugt));
                    }
                }
            }

            if (cmbHotel.getValue() != null) {
                Hotel hotel = cmbHotel.getValue();
                Værelsestype værelsestype = chkLedsager.isSelected() ? Værelsestype.DOBBELT : Værelsestype.ENKELT;

                HotelReservation reservation = controller.createReservation(tilmelding, værelsestype, hotel);

                for (CheckBox cb : serviceCheckBoxes) {
                    if (cb.isSelected()) {
                        HotelService hotelService = (HotelService) cb.getUserData();
                        reservation.addService(hotelService);
                    }
                }
            }

            showAlert("Tilmelding oprettet", "Tilmelding for " + deltager.getNavn() + " er blevet oprettet.");
            dpAfrejse.setValue(null);
            dpAnkomst.setValue(null);
            chkLedsager.setSelected(false);
            txfLedsager.clear();
            txfLedsager.setVisible(false);
            chkForedrag.setSelected(false);



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
