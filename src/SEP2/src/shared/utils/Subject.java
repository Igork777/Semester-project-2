package shared.utils;

import java.beans.PropertyChangeListener;

public interface Subject
{
    /**
     * Add a property change listener to a subject
     * @param name of the property change
     * @param listener of the subject
     */
    void addListener(String name, PropertyChangeListener listener);

    /**
     * Remove a property change listener to a subject
     * @param name of the property change
     * @param listener of the subject
     */
    void removeListener(String name, PropertyChangeListener listener);
}
