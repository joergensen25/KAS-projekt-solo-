package model;

import java.time.LocalDate;

public class Tilmelding {
    private Deltager deltager;
    private Konference konference;

    private LocalDate ankomstdato;
    private LocalDate afrejsedato;

    private double totalpris;
    private boolean erForedragsholder;

    private Ledsager ledsager;
    private HotelReservation reservation;

    public Tilmelding(Deltager deltager, Konference konference, LocalDate ankomstdato,
                      LocalDate afrejsedato, boolean erForedragsholder) {

        this.deltager = deltager;
        this.konference = konference;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        this.erForedragsholder = erForedragsholder;
    }

    public Ledsager createLedsager(String navn) {
        Ledsager ledsager = new Ledsager(navn, this);
        this.ledsager = ledsager;
        return ledsager;
    }

    public HotelReservation createReservation(Værelsestype værelsestype, Hotel hotel) {
        HotelReservation hotelReservation = new HotelReservation(værelsestype, hotel, this);
        this.reservation = hotelReservation;
        return hotelReservation;
    }

    public int getAntalDage() {
        return (int) (afrejsedato.toEpochDay() - ankomstdato.toEpochDay());
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public LocalDate getAnkomstdato() {
        return ankomstdato;
    }

    public LocalDate getAfrejsedato() {
        return afrejsedato;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }
}
