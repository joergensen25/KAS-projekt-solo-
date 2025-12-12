package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tilmelding {
    private Deltager deltager;
    private Konference konference;

    private LocalDate ankomstdato;
    private LocalDate afrejsedato;

    private double totalpris;
    private boolean erForedragsholder;

    private Ledsager ledsager;
    private HotelReservation reservation;
    private ArrayList<HotelReservation> reservationer = new ArrayList<>();

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

        reservationer.add(hotelReservation);
        hotel.addReservation(hotelReservation);

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

    public Konference getKonference() {
        return konference;
    }

    public double getTotalPris() {
        double total = 0;

        if (!erForedragsholder) {
            long antalDage = ChronoUnit.DAYS.between(ankomstdato, afrejsedato);
            total += antalDage * konference.getDagspris();
        }

        if (reservation != null) {
            total += reservation.getPris();
        }

        if (ledsager != null) {
            for (UdflugtTilmelding ut : ledsager.getUdflugtTilmeldinger()) {
                total += ut.getUdflugt().getPris();
            }
        }
        return total;
    }
}

