package handIn_2.shared;

import java.beans.PropertyChangeListener;

public interface Subject
{
    void addListener(PropertyChangeListener listener);
    void removeListener(PropertyChangeListener listener);
}
