package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {

    private String navn;
    private String beskrivelse;
    private double pris;
    private LocalDate dato;
    private Konference konference;

    private ArrayList<UdflugtTilmelding> tilmeldinger = new ArrayList<>();

    public Udflugt(String navn, String beskrivelse, double pris, LocalDate dato, Konference konference) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.dato = dato;
        this.konference = konference;
    }



    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public void addTilmelding(UdflugtTilmelding udflugtTilmelding) {
        tilmeldinger.add(udflugtTilmelding);
    }

    @Override
    public String toString() {
        return "Udflugt{" +
                "navn='" + navn + '\'' +
                ", pris=" + pris +
                '}';
    }
}
