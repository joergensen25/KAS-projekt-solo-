package model;

import java.util.ArrayList;

public class HotelReservation {

    private Værelsestype værelsestype;
    private double pris;
    private Hotel hotel;
    private Tilmelding tilmelding;

    private ArrayList<HotelService> services = new ArrayList<>();

    public HotelReservation(Værelsestype værelsestype, Hotel hotel, Tilmelding tilmelding) {
        this.værelsestype = værelsestype;
        this.hotel = hotel;
        this.tilmelding = tilmelding;
    }



    public Tilmelding getTilmelding() {
        return tilmelding;
    }

    public Værelsestype getVærelsestype() {
        return værelsestype;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public double getPris() {
        double pris = (værelsestype == Værelsestype.DOBBELT) ? hotel.getPrisDobbelt() : hotel.getPrisEnkelt();

        for (HotelService s : services) {
            pris += s.getPris();
        }

        return pris;
    }

    public void addService(HotelService hotelService) {
        services.add(hotelService);
    }
}
