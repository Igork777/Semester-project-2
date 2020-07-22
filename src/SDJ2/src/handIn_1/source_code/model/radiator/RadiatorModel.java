package handIn_1.source_code.model.radiator;


import java.beans.PropertyChangeListener;

public interface RadiatorModel
{
    void turnUp();
    void turnDown();
    RadiatorState getPowerState();
    void addListener(PropertyChangeListener propertyChangeListener);
    void removeListener(PropertyChangeListener propertyChangeListener);
}
