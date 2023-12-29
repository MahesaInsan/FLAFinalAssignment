package state;

import models.Medicine;

public abstract class State {
    protected Medicine med;

    public State(Medicine med) {
        this.med = med;
    }
    
    public abstract void changeState();
    protected abstract boolean checkStock();
    public abstract String showState();
}
