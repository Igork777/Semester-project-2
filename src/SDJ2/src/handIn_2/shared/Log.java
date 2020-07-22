package handIn_2.shared;

import java.util.ArrayList;

public class Log {
    private ArrayList<String> logs;

    public Log() {
        this.logs = new ArrayList<>();
    }

    public void log(String s) {
        System.out.println(s);
        logs.add(s);
    }
}
