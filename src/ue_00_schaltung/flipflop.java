/**
 * Clemens Hodina, 5CN
 */

package ue_00_schaltung;

public class flipflop {
    private boolean isTrue = false;

    public void setState() {
        isTrue = true;
    }

    public void resetState() {
        isTrue = false;
    }

    public boolean getQ () {
        return isTrue;
    }

    public boolean getInvertedQ() {
        return !isTrue;
    }

    public static void main(String[] args) {
        flipflop f1 = new flipflop();

        f1.setState();
        System.out.println(f1.getQ());
        System.out.println(f1.getInvertedQ());

        f1.resetState();
        System.out.println(f1.getQ());
        System.out.println(f1.getInvertedQ());


    }
}
