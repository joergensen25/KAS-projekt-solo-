package model;

public class UdflugtTilmelding {
    private Ledsager ledsager;
    private Udflugt udflugt;

    public UdflugtTilmelding(Ledsager ledsager, Udflugt udflugt) {
        this.ledsager = ledsager;
        this.udflugt = udflugt;

    }

    public Ledsager getLedsager() {
        return ledsager;
    }
}
