package model;

public class UdflugtTilmelding {
    private int id;
    private Ledsager ledsager;
    private Udflugt udflugt;

    public UdflugtTilmelding(int id, Ledsager ledsager, Udflugt udflugt) {
        this.id = id;
        this.ledsager = ledsager;
        this.udflugt = udflugt;

        ledsager.addUdflugtTilmelding(this);

        udflugt.addTilmelding(this);
    }


}
