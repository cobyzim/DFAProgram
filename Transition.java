/*
 * 
 */
package cs475assign1;

/**
 *
 * Transition class with constructor for transition objects, setters and getters
 * for the object data fields, and a toString method for debugging.
 * 
 * @author Coby Zimmerman
 */
public class Transition {
    public String fromState;
    public Character label;
    public String toState;
    
    public Transition(String fromState, Character label, String toState) {
        this.fromState= fromState;
        this.label = label;
        this.toState = toState;
    }

    @Override
    public String toString() {
        return "Transition{" + "fromState=" + fromState + ", label=" + label + ", toState=" + toState + '}';
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public void setLabel(Character label) {
        this.label = label;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public String getFromState() {
        return fromState;
    }

    public Character getLabel() {
        return label;
    }

    public String getToState() {
        return toState;
    }
    
    
}
