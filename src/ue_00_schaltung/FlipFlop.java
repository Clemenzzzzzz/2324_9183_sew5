/**
 * Clemens Hodina, 5CN
 */

package ue_00_schaltung;

public class FlipFlop {
    //TODO Konstruktur mit inputs und outputs
    // Component irgendwie implementieren --> oder als Anmerkung oben
    // mit Methode getInputPort
    // r und s als konstanten
    // also definieren, dass r der Eingang 0 oder 1 is und das gleiche mit s
    // der Flipflop muss nicht funktionieren
    // und Klassendiagramm anpassen

    private boolean isTrue = false;

    /**
     * sets the State of the FlipFlop to true
     */
    public void setState() {
        isTrue = true;
    }

    /**
     * resets the state of the FlipFLop to its default value
     */
    public void resetState() {
        isTrue = false;
    }

    /**
     * returns the output of the q
     * @return value of q output
     */
    public boolean getQ () {
        return isTrue;
    }

    /**
     * returns the output of the inverted q
     * @return value of inverted q output
     */
    public boolean getInvertedQ() {
        return !isTrue;
    }

    public static void main(String[] args) {
        FlipFlop f1 = new FlipFlop();

        f1.setState();
        System.out.println(f1.getQ());
        System.out.println(f1.getInvertedQ());

        f1.resetState();
        System.out.println(f1.getQ());
        System.out.println(f1.getInvertedQ());


    }
}
