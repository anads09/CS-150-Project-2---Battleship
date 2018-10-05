/*
Austin Nadler
CS150-002
Program #2
10/05/2018
*/
package cs150proj02;

public class Space {  
    
    public static final String BLANK = ".";
    public static final String MISS = "o";
    public static final String NEAR = "!";
    public static final String HIT = "X";    
    private String value;
    private boolean hasShip;
    private boolean hasBeenShotAt = false;
    //private boolean hasWreckage = false;
    
    public Space () {
        value = BLANK;
        hasShip = false;
        hasBeenShotAt = false;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValueToBLANK() {
        value = BLANK;
    }
    
    public void setValueToMISS() {
        value = MISS;
    }
    
    public void setValueToNEAR() {
        value = NEAR;
    }
    
    public void setValueToHIT() {
        value = HIT;
    }
    
    public void setShip() {
        hasShip = true;
    }
    
    public void destroyShip() {
        hasShip = false;
    }
    
    public boolean doesSpaceHaveShip() {
        return hasShip;
    }
    
    public boolean hasSpaceBeenShotAt() {
        if (value != BLANK) {
            return true;
        } else {
            return false;
        }
    }
    
}
