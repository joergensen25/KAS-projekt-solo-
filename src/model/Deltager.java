package model;

import java.util.ArrayList;

public class Deltager {
    private String navn;
    private String adresse;
    private String nationalitet;
    private String telefon;
    private String firmanavn;
    private String firmatelefon;

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Deltager(String navn, String adresse, String nationalitet, String telefon,
                    String firmanavn, String firmatelefon) {
        this.navn = navn;
        this.adresse = adresse;
        this.nationalitet = nationalitet;
        this.telefon = telefon;

        this.firmanavn = firmanavn; // Kan være null
        this.firmatelefon = firmatelefon; // Kan være null
    }

    public void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }


    public String getNavn() {
        return navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdresse() {
        return adresse;
    }
}
