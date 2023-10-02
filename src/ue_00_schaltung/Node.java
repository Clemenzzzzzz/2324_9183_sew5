package ue_00_schaltung;

public class Node { //für Testzwecke nicht abstract, sonst natürlich schon
    private boolean state = false;

    /**
     * Gets the current state of the given Node
     *
     * @return state of the node
     */
    public boolean getState() {
        return state;
    }

    /**
     * sets the State of the given Node
     *
     * @param state state to which it should be set
     */
    public void setState(boolean state) {
        this.state = state;
    }
}
