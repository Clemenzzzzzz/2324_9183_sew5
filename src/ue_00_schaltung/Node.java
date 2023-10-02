package ue_00_schaltung;

public abstract class Node {
    private boolean state = false;



    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
