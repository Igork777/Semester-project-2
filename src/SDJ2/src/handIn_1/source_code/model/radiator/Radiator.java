package handIn_1.source_code.model.radiator;

import handIn_1.source_code._util.MyTimer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Radiator implements RadiatorModel
{
    private RadiatorState currentState;
    protected PropertyChangeSupport support;
    protected MyTimer timer;

    public Radiator()
    {
        support = new PropertyChangeSupport(this);
        currentState = new OffState(this);
        timer = new MyTimer();
//       Passing the event further using the Radiator
        timer.addListener(evt -> support.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue()));
    }

    public void setPowerState(RadiatorState state)
    {
        RadiatorState oldState = currentState;
        currentState = state;
    }

    public void turnUp()
    {
        setPowerState(currentState.turnUp());
    }
    public void turnDown()
    {
        setPowerState(currentState.turnDown());
    }

    @Override
    public RadiatorState getPowerState() {
        return currentState;
    }

    @Override
    public void addListener(PropertyChangeListener propertyChangeListener)
    {
        support.addPropertyChangeListener(propertyChangeListener);
    }

    @Override
    public void removeListener(PropertyChangeListener propertyChangeListener)
    {
        support.removePropertyChangeListener(propertyChangeListener);
    }

}
