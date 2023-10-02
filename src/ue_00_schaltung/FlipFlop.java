/**
 * Clemens Hodina, 5CN
 */

package ue_00_schaltung;

import java.util.List;

public class FlipFlop extends Component {

    private boolean state = false;
    public static final int SET = 0;
    public static final int RESET = 1;
    public static final int Q = 0;
    public static final int NOT_Q = 1;

    public FlipFlop(String name, List<Node> inputs, List<Node> outputs) {
        super(name, inputs, outputs);
    }

    @Override
    void calcState() {
        if (inputs.size() > RESET && inputs.get(RESET) != null && inputs.get(RESET).getState()) { // Liste hat zwei Stellen, mit erstem check checkt man ob es mindestens 2 lang is
            state = false;
        } else if (inputs.size() > SET && inputs.get(SET) != null && inputs.get(SET).getState()){ //aber hier braucht man theoretisch kein Reset also reicht auch eine Stelle
            state = true;
        }

        if (outputs.size() > Q && outputs.get(Q) != null) { // wenn man nur invertiertes haben will kann man einfach beim normalen null angeben und dann is es nur das andere
            outputs.get(Q).setState(state);
        }
        if (outputs.size() > NOT_Q && outputs.get(NOT_Q) != null) {
            outputs.get(NOT_Q).setState(state);
        }
    }

    public static void main(String[] args) {

    }
}
