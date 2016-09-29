package br.com.potatotech.contrafluxo.Model;

/**
 * Created by Allan on 26/09/2016.
 *
 */

public class BusLocation {
    String hr;
    BusLocationXY[] vs;

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public BusLocationXY[] getVs() {
        return vs;
    }

    public void setVs(BusLocationXY[] vs) {
        this.vs = vs;
    }
}
