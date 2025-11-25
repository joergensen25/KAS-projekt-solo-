package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Hotel;
import model.Konference;

public class TilføjUdflugtWindow extends Stage {

    private Controller controller;
    private Konference konference;

    public TilføjUdflugtWindow(Controller controller, Konference konference) {
        this.controller = controller;
        this.konference = konference;

        setTitle("Tilføj service til ");

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
        pane.add(btnTilføj, 1, 7);;



    }
}
