package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;

public class Controller {

    public void initData() {
        Konference k1 = createKonference("Hav og Himmel", "Odense", LocalDate.of(2025, 11, 28),
                LocalDate.of(2025, 11, 30), 500);

        Konference k2 = createKonference("Nordic Climate Forum", "Oslo", LocalDate.of(2025, 12, 10),
                LocalDate.of(2025, 12, 12), 1200);

        Konference k3 = createKonference("Liv i Åen", "Aarhus", LocalDate.of(2026, 1, 6),
                LocalDate.of(2026, 1, 9), 800);

        Hotel h1 = createHotel(k1, "Den Hvide Svane", 1050, 1250);
        Hotel h2 = createHotel(k1, "Hotel Phønix", 700, 800);

        Hotel h3 = createHotel(k2, "Pension Tusindfryd", 500, 600);
        Hotel h4 = createHotel(k2, "Super Stay Oslo", 800, 1000);

        Hotel h5 = createHotel(k3, "Radisson Blu", 1200, 1450);
        Hotel h6 = createHotel(k3, "Scandic City", 600, 750);

        HotelService morgenmad = createHotelService("Morgenmad", "Morgenmadsbuffet", 250);
        HotelService wellness = createHotelService("Wellness", "Spa, sauna og massage", 150);
        HotelService friBar = createHotelService("Fri-bar", "Fri-bar i alle alm. drikkevarer", 350);
        HotelService vask = createHotelService("Vask af tøj", "Få dit tøj og udstyr vasket", 120);

        h1.addService(morgenmad);
        h1.addService(wellness);
        h1.addService(friBar);

        h2.addService(morgenmad);
        h2.addService(vask);

        h3.addService(vask);
        h3.addService(friBar);

        h4.addService(morgenmad);
        h4.addService(wellness);

        h5.addService(wellness);
        h5.addService(friBar);

        h6.addService(morgenmad);
        h6.addService(vask);

        Udflugt u1 = createUdflugt(k1, "Byrundtur, Odense", "inkl. frokost", 125, LocalDate.of(2025, 11, 28));
        Udflugt u2 = createUdflugt(k1, "Egeskov", "Gåtur m. guide", 75, LocalDate.of(2025, 11, 29));

        Udflugt u3 = createUdflugt(k2, "Nationalmuseet", "inkl. valgfri drik", 250, LocalDate.of(2025, 12, 11));
        Udflugt u4 = createUdflugt(k2, "Øl-festival", "inkl. 3 øl", 120, LocalDate.of(2025, 12, 12));

        Udflugt u5 = createUdflugt(k3, "Aftensmad på Streetfood", "inkl. hovedret og drik", 100,LocalDate.of(2026, 1, 7));
        Udflugt u6 = createUdflugt(k3, "Tur til Aarhus Ø", "Guidet tur", 150, LocalDate.of(2026, 1, 9));

    }

    private Storage storage = new Storage();

    public Konference createKonference(String navn, String sted, LocalDate startdato, LocalDate slutdato, double dagspris) {
        Konference konference = new Konference(navn, sted, startdato, slutdato, dagspris);
        storage.addKonference(konference);
        return konference;
    }

    public Deltager createDeltager(String navn, String adresse, String nationalitet, String telefon, String firmanavn, String firmatelefon) {
        Deltager deltager = new Deltager(navn, adresse, nationalitet, telefon, firmanavn, firmatelefon);
        storage.addDeltager(deltager);
        return deltager;
    }

    public HotelService createHotelService(String navn, String beskrivelse, double pris) {
        HotelService hotelService = new HotelService(navn, beskrivelse, pris);
        storage.addService(hotelService);
        return hotelService;
    }

    public Hotel createHotel(Konference konference, String navn, double prisEnkelt, double prisDobbelt) {
        return konference.createHotel(navn, prisEnkelt, prisDobbelt);
    }

    public Udflugt createUdflugt(Konference konference, String navn, String beskrivelse, double pris, LocalDate dato) {
        return konference.createUdflugt(navn, beskrivelse, pris, dato);
    }

    public Tilmelding createTilmelding(Konference konference, Deltager deltager, LocalDate ankomst, LocalDate afrejse, boolean erForedragsholder) {
        return konference.createTilmelding(deltager, ankomst, afrejse, erForedragsholder);
    }

    public Ledsager createLedsager(Tilmelding tilmelding, String navn) {
        return tilmelding.createLedsager(navn);
    }

    public HotelReservation createReservation(Tilmelding tilmelding, Værelsestype værelsestype, Hotel hotel) {
        return tilmelding.createReservation(værelsestype, hotel);
    }




    public Storage getStorage() {
        return storage;
    }
}
