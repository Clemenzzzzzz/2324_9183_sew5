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


}
