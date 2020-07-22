package handIn_2.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Form implements Serializable {
    ArrayList<FormField> fields;

    public Form() {
        this.fields = new ArrayList<>();
    }

    public Form(ArrayList<FormField> fields) {
        this.fields = fields;
    }

    public Form(FormField... fields) {
        this.fields = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            add(fields[i]);
        }
    }

    public void add(FormField ff) {
        if (isFormFieldUnique(ff)) {
            fields.add(ff);
        } else {
            throw new RuntimeException("FormField keys must be unique!");
        }
    }

    public <V> void add(String key, V value) {
        add(new FormField(key, value));
    }

    public FormField get(String key) {
        for (FormField ff : fields) {
            if (ff.getKey().equalsIgnoreCase(key)) {
                return ff;
            }
        }
        return null;
    }

    public <V> V getValue(String key) {
        for (FormField ff : fields) {
            if (ff.getKey().equalsIgnoreCase(key)) {
                return (V) ff.getVal();
            }
        }
        return null;
    }

    public void remove(FormField ff) {
        fields.remove(ff);
    }

    public void remove(String key) {
        fields.remove(get(key));
    }


    private boolean isFormFieldUnique(FormField ff) {
        for (int i = 0; i < fields.size(); i++) {
            if (ff.getKey().equalsIgnoreCase(fields.get(i).getKey())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<FormField> getFields() {
        return fields;
    }

}
