package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private String sted;
    private LocalDate startdato;
    private LocalDate slutDato;
    private double dagspris;

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();

    public Konference(String navn, String sted, LocalDate startdato,
                      LocalDate slutDato, double dagspris) {
        this.navn = navn;
        this.sted = sted;
        this.startdato = startdato;
        this.slutDato = slutDato;
        this.dagspris = dagspris;
    }

    public Tilmelding createTilmelding(Deltager deltager, LocalDate ankomstdato, LocalDate afrejsedato, boolean erForedragsholder) {
        Tilmelding tilmelding = new Tilmelding(deltager, this, ankomstdato, afrejsedato, erForedragsholder);
        tilmeldinger.add(tilmelding);
        return tilmelding;
    }

    public Udflugt createUdflugt(String navn, String beskrivelse, double pris, LocalDate dato) {
        Udflugt udflugt = new Udflugt(navn, beskrivelse, pris, dato, this);
        udflugter.add(udflugt);
        return udflugt;
    }

    public Hotel createHotel(String navn, double prisEnkelt, double prisDobbelt) {
        Hotel hotel = new Hotel(navn, prisEnkelt, prisDobbelt, this);
        hoteller.add(hotel);
        return hotel;
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Hotel> getHoteller() {
        return hoteller;
    }


    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public double getDagspris() {
        return dagspris;
    }

    @Override
    public String toString() {
        return navn +
                ", " + sted +
                " (" + startdato +
                " - " + slutDato +
                ") Dagspris: " + dagspris + " kr.";
    }
}
