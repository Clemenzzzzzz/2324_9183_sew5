package ue_00_schaltung;

import java.util.List;

public abstract class Component {
    String name;
    List<Node> inputs;
    List<Node> outputs;

    public Component(String name, List<Node> inputs, List<Node> outputs) {
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    abstract void calcState();


}
