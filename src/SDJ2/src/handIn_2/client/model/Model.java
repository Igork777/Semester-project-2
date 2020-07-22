package handIn_2.client.model;

import handIn_2.shared.Subject;

import java.beans.PropertyChangeListener;

public interface Model extends Subject, PropertyChangeListener {
     String getName();
}