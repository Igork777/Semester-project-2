package handIn_2.shared;

import java.io.Serializable;

public class FormField<V> implements Serializable {
    private String key;
    private V val;

    public FormField(String key, V val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }
}
