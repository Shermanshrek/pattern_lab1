package Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> observers = new ArrayList<>();
    private boolean changed = false;

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    protected void setChanged() {
        changed = true;
    }

    public void notifyObservers() {
        if (changed) {
            for (Observer o : observers) {
                o.update(this);
            }
            changed = false;
        }
    }
}
