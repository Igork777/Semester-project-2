package handIn_1.source_code.model.radiator;

public class Power2State implements RadiatorState
{
    private  final int POWER = 2;
    private Radiator radiator;
    public Power2State(Radiator radiator)
    {
        this.radiator = radiator;
    }

    @Override
    public RadiatorState turnUp()
    {
        return new Power3State(radiator);
    }

    @Override
    public RadiatorState turnDown() {return new Power1State(radiator); }

    @Override
    public int getPower()
    {
        return POWER;
    }
}
