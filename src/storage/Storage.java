package storage;

import model.Deltager;
import model.HotelService;
import model.Konference;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Konference> konferencer = new ArrayList<>();
    private ArrayList<Deltager> deltagere = new ArrayList<>();
    private ArrayList<HotelService> services = new ArrayList<>();


    public void addKonference(Konference konference) {
        konferencer.add(konference);
    }

    public void addDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }

    public void addService(HotelService hotelService) {
        services.add(hotelService);
    }

    public ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public ArrayList<Deltager> getDeltagere() {
        return deltagere;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }
}
