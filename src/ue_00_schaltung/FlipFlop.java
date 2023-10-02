/**
 * Clemens Hodina, 5CN
 */

package ue_00_schaltung;

public class FlipFlop extends Component {
    // Konstruktur mit inputs und outputs
    // Component irgendwie implementieren --> oder als Anmerkung oben
    // mit Methode getInputPort
    // r und s als konstanten
    // also definieren, dass r der Eingang 0 oder 1 is und das gleiche mit s
    // der Flipflop muss nicht funktionieren
    // und Klassendiagramm anpassen

    private boolean state = false;
    private Node set; //sind Nodes weil wir ja Inputs und Outputs nicht implementieren müssen (laut BRE)
    private Node reset;
    private Node q;
    private Node not_q;

    public FlipFlop(String name, Node set, Node reset, Node q, Node not_q) {
        super(name);
        this.set = set;
        this.reset = reset;
        this.q = q;
        this.not_q = not_q;
    }

    @Override
    void calcState() {
        if (reset != null && reset.getState()) {
            state = false;
        } else if (set != null && set.getState()){
            state = true;
        }

    }

    public static void main(String[] args) {

    }
}
