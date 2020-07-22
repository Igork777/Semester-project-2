package shared.wrappers;

import java.io.Serializable;

public class Instrument implements Serializable
{
    private String instrument;
    private int expertise;

    public Instrument(String instrument, int expertise) {
        this.instrument = instrument;
        this.expertise = expertise;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }

    public String getInstrument() {
        return instrument;
    }

    public int getExpertise() {
        return expertise;
    }
    @Override
    public String toString() {
        return "Instrument{" +
                "instrument='" + instrument + '\'' +
                ", expertise=" + expertise +
                '}';
    }
}
