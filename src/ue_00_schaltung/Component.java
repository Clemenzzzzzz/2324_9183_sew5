package ue_00_schaltung;

public abstract class Component {
    String name;

    public Component(String name) {
        this.name = name;
    }

    abstract void calcState();


}
