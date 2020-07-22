package handIn_1.source_code.model.radiator;

public class Power1State implements RadiatorState
{
    private final int POWER  = 1;
    private Radiator radiator;

    public Power1State(Radiator radiator) {
        this.radiator = radiator;
    }

    @Override
    public RadiatorState turnUp()
    {
        return new Power2State(radiator);
    }

    @Override
    public RadiatorState turnDown()
    {
        return new OffState(radiator);
    }

    @Override
    public int getPower() {
        return POWER;
    }
}
