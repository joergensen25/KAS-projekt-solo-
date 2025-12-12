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
        btnSøg.setOnAction(e -> søgDeltager(txfSøg.getText()));

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
        sb.append("Hoteloversigt for: ").append(k.getNavn()).append("\n\n");

        for (Hotel h : k.getHoteller()) {
            sb.append("Hotel: ").append(h.getNavn()).append(":\n");
            sb.append("-----------------------------------\n");

            if (h.getReservationer().isEmpty()) {
                sb.append("Ingen reservationer\n\n");
                continue;
            }

            for (HotelReservation hr : h.getReservationer()) {

                Tilmelding t = hr.getTilmelding();
                Deltager d = t.getDeltager();
                Ledsager l = t.getLedsager();

                sb.append("- Deltager: ").append(d.getNavn()).append("\n");
                sb.append(" Telefon: ").append(d.getTelefon()).append("\n");

                sb.append(" Værelse: ");
                if (hr.getVærelsestype() == Værelsestype.DOBBELT) {
                    sb.append("Dobbeltværelse\n");
                } else {
                    sb.append("Enkeltværelse\n");
                }

                sb.append(" Ophold: ")
                        .append(t.getAnkomstdato())
                        .append(" til ")
                        .append(t.getAfrejsedato())
                        .append("\n");

                if (l != null) {
                    sb.append(" Ledsager: ").append(l.getNavn()).append("\n");
                } else {
                    sb.append(" Ledsager: Ingen\n");
                }

                if (hr.getServices().isEmpty()) {
                    sb.append(" Services: Ingen\n");
                } else {
                    sb.append(" Services: ");
                    for (int i = 0; i < hr.getServices().size(); i++) {
                        sb.append(hr.getServices().get(i).getNavn());
                        if (i < hr.getServices().size() - 1) {
                            sb.append(", ");
                        }
                    }
                    sb.append("\n");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        txaResultat.setText(sb.toString());
    }

    private void søgDeltager(String navn) {
        Deltager fundet = controller.findDeltagerByName(navn);

        if (fundet == null) {
            txaResultat.setText("Ingen deltager fundet.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Deltagerinfo:\n");
        sb.append(fundet.getNavn()).append("\n");
        sb.append("Adresse: ").append(fundet.getAdresse()).append("\n");
        sb.append("Telefon: ").append(fundet.getTelefon()).append("\n\n");

        sb.append("Tilmeldinger:\n");
        for (Tilmelding t : controller.getTilmeldingerFor(fundet)) {
            sb.append("- ").append(t.getKonference().getNavn())
                    .append(": ").append(t.getTotalPris()).append(" kr\n");
        }

        txaResultat.setText(sb.toString());
    }

}
