package model;

public class HotelService {
    private String navn;
    private String beskrivelse;
    private double pris;

    public HotelService(String navn, String beskrivelse, double pris) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getPris() {
        return pris;
    }

    @Override
    public String toString() {
        return navn + " ("+ beskrivelse + "), " + pris + " kr.";
    }
}
