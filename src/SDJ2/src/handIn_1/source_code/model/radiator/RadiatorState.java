package handIn_1.source_code.model.radiator;

public interface RadiatorState
{
    RadiatorState turnUp();
    RadiatorState turnDown();
    int getPower();
}
