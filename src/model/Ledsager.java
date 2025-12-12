package model;

import java.util.ArrayList;

public class Ledsager {

    private String navn;
    private Tilmelding tilmelding;

    private ArrayList<UdflugtTilmelding> udflugtTilmeldinger = new ArrayList<>();

    public Ledsager(String navn, Tilmelding tilmelding) {
        this.navn = navn;
        this.tilmelding = tilmelding;
    }

    public String getNavn() {
        return navn;
    }

    public Tilmelding getTilmelding() {
        return tilmelding;
    }

    public ArrayList<UdflugtTilmelding> getUdflugtTilmeldinger() {
        return udflugtTilmeldinger;
    }

    public void addUdflugtTilmelding(UdflugtTilmelding udflugtTilmelding) {
        udflugtTilmeldinger.add(udflugtTilmelding);
    }
}
