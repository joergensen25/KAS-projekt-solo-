package model;

import java.util.ArrayList;

public class Hotel {

    private String navn;
    private double prisEnkelt;
    private double prisDobbelt;

    private Konference konference;

    private ArrayList<HotelService> services = new ArrayList<>();
    private ArrayList<HotelReservation> reservationer = new ArrayList<>();

    public Hotel(String navn, double prisEnkelt, double prisDobbelt, Konference konference) {
        this.navn = navn;
        this.prisEnkelt = prisEnkelt;
        this.prisDobbelt = prisDobbelt;
        this.konference = konference;
    }

    public void addService(HotelService hotelService) {
        services.add(hotelService);
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return navn +
                " (Enkeltværelse " + prisEnkelt + " kr." +
                ", Dobbeltværelse " + prisDobbelt + " kr.";
    }
}
