package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;


public class OversigtWindow extends Stage {

    private final Controller controller;

    private ComboBox<Konference> cmbKonferencer = new ComboBox<>();

    private RadioButton rbDeltagere = new RadioButton("Deltageroversigt");
    private RadioButton rbUdflugter = new RadioButton("Udflugtoversigt");
    private RadioButton rbHoteller = new RadioButton("Hoteloversigt");

    private TextArea txaResultat = new TextArea();


    public OversigtWindow(Controller controller) {
        this.controller = controller;

        setTitle("Oversigtsmenu");

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


        Label lblOversigt = new Label("Vælg en konference");
        cmbKonferencer.getItems().addAll(controller.getStorage().getKonferencer());
        pane.add(lblOversigt, 0, 1);
        pane.add(cmbKonferencer, 0, 2);

        ToggleGroup group = new ToggleGroup();
        rbDeltagere.setToggleGroup(group);
        rbUdflugter.setToggleGroup(group);
        rbHoteller.setToggleGroup(group);

        pane.add(rbDeltagere, 0, 3);
        pane.add(rbUdflugter, 0, 4);
        pane.add(rbHoteller, 0, 5);

        txaResultat.setPrefSize(500, 300);
        txaResultat.setEditable(false);
        pane.add(txaResultat, 0, 6, 2, 1);

        cmbKonferencer.setOnAction(e -> opdaterOversigt());
        rbDeltagere.setOnAction(e -> opdaterOversigt());
        rbUdflugter.setOnAction(e -> opdaterOversigt());
        rbHoteller.setOnAction(e -> opdaterOversigt());


        Label lblSøg = new Label("Søg efter deltager");
        TextField txfSøg = new TextField();
        Button btnSøg = new Button("Søg");

        pane.add(lblSøg, 1, 1);
        pane.add(txfSøg, 1, 2);
        pane.add(btnSøg, 1, 3);


    }

    private void opdaterOversigt() {

        Konference k = cmbKonferencer.getValue();

        if (k == null) {
            txaResultat.setText("Vælg først en konference.");
            return;
        }

        if (rbDeltagere.isSelected()) {
            visDeltagerOversigt(k);
        }
        else if (rbUdflugter.isSelected()) {
            visUdflugtOversigt(k);
        }
        else if (rbHoteller.isSelected()) {
            visHotelOversigt(k);
        }

    }


    private void visDeltagerOversigt(Konference k) {
        StringBuilder sb = new StringBuilder();

        sb.append("Deltageroversigt for ").append(k.getNavn()).append(":\n\n");

        ArrayList<Tilmelding> sorted = new ArrayList<>(k.getTilmeldinger());
        sorted.sort((t1, t2) -> t1.getDeltager().getNavn().compareToIgnoreCase(t2.getDeltager().getNavn()));

        for (Tilmelding t : sorted) {
            Deltager d = t.getDeltager();
            sb.append(" - ").append(d.getNavn()).append(" , tlf. ").append(d.getTelefon()).append(" (").append(t.getAnkomstdato())
                    .append(" - ").append(t.getAfrejsedato()).append(")\n");
        }

        txaResultat.setText(sb.toString());
    }

//    private void visUdflugtOversigt(Konference k) {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("Udflugtoversigt for ").append(k.getNavn()).append(":\n\n");
//
//        for (Udflugt u : k.getUdflugter()) {
//            sb.append(u.getNavn()).append(" - ").append(u.getDato()).append(":\n");
//
//            for (UdflugtTilmelding ut : u.getTilmeldinger()) {
//                sb.append(" - ").append(ut.getLedsager().getNavn()).append(" (Ledsager til: ")
//                        .append(ut.getLedsager().getTilmelding().getDeltager().getNavn()).append(")\n");
//            }
//        }
//        txaResultat.setText(sb.toString());
//    }

    private void visUdflugtOversigt(Konference k) {
        StringBuilder sb = new StringBuilder();
        sb.append("Udflugtoversigt:\n\n");

        for (Udflugt u : k.getUdflugter()) {
            sb.append(u.getNavn()).append(" - ").append(u.getDato()).append(":\n");

            for (UdflugtTilmelding ut : u.getTilmeldinger()) {
                Ledsager l = ut.getLedsager();
                sb.append("   - ").append(l.getNavn())
                        .append(" (Ledsager til: ").append(ut.getLedsager().getTilmelding().getDeltager().getNavn())
                        .append(" tlf. ").append(ut.getLedsager().getTilmelding().getDeltager().getTelefon()).append(")\n");
            }
            sb.append("\n");
        }
        txaResultat.setText(sb.toString());
    }


    private void visHotelOversigt(Konference k) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hoteloversigt:\n\n");

        for (Hotel h : k.getHoteller()) {
            sb.append(h.getNavn()).append(":\n");

            for (HotelReservation hr : h.getReservationer()) {
                sb.append(" - Deltager ")
                        .append(hr.getTilmelding().getDeltager().getNavn()).append(" og ledsager ").append(hr.getTilmelding().getLedsager())
                        .append(" (tlf. ").append(hr.getTilmelding().getDeltager().getTelefon()).append(")\n");
            }
            sb.append("\n");
        }
        txaResultat.setText(sb.toString());
    }

}
