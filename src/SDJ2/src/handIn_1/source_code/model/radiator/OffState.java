package handIn_1.source_code.model.radiator;

public class OffState implements RadiatorState
{
    private final int POWER = 0;
    private Radiator radiator;

    public OffState(Radiator radiator)
    {
        this.radiator = radiator;
    }

    @Override
    public RadiatorState turnUp() { return new Power1State(radiator); }

    @Override
    public RadiatorState turnDown() { return this; }

    @Override
    public int getPower()
    {
        return POWER;
    }
}
