/*
 * This program takes a dfa input file and determines what the alphabet is for
 * that dfa and uses the given transitions to see if a user's input string 
 * would be accepted or not.
 */
package cs475assign1;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Coby Zimmerman
 */
public class CS475Assign1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String startState = null;
        String fromState;
        Character label;
        String toState;
        
        ArrayList<String> acceptStates = new ArrayList<>();
        
        ArrayList<String> untrimmedStates = new ArrayList<>();
        ArrayList<String> states = new ArrayList<>();
        ArrayList<Character> untrimmedAlphabet = new ArrayList<>();
        ArrayList<Character> alphabet = new ArrayList<>();
        
        ArrayList<Transition> transitions = new ArrayList<>();
        
        String userInputString;
        String userConfirm;

        File textFile;
        
        //Sets up JFileChooser for user to choose a file
        JFileChooser chooseFile = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TEXT FILES", "txt", "text");
        chooseFile.setFileFilter(filter);
        int returnVal = chooseFile.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            textFile = chooseFile.getSelectedFile();
            
            //Try to read what is in the selected file
            try {
                //Determine the start state from first line of file
                try (Scanner scanner = new Scanner(textFile)) {
                    //Determine the start state from first line of file
                    startState = scanner.nextLine();
                    
                    //Take second line of file and put each accept state into
                    //arrayList as separate members (as long as one space between them)
                    String acceptedStatesLine = scanner.nextLine();
                    acceptStates = new ArrayList<>
                            (Arrays.asList(acceptedStatesLine.split(" ")));
                    
                    //While loop to help figure out the alphabet and total states.
                    //Also makes new transition objects from file and adds them
                    //to the transitions arrayList
                    while (scanner.hasNextLine()) {
                        String[] splitTrans = scanner.nextLine().split(" ");
                        fromState = splitTrans[0];
                        label = splitTrans[1].charAt(0);
                        toState = splitTrans[2];
                        untrimmedAlphabet.add(label);
                        untrimmedStates.add(fromState);
                        untrimmedStates.add(toState);
                        
                        Transition transition = new Transition(fromState, label,
                                toState);
                        transitions.add(transition);
                    }
                    
                    //Determines and displays the alphabet associated with the DFA
                    for (Character i : untrimmedAlphabet) {
                        if (!alphabet.contains(i)) {
                            alphabet.add(i);
                        }
                    }
                    System.out.println("This is the alphabet associated with "
                            + "the input DFA file: " + alphabet);
                    
                    //Determines the states in the DFA that was read
                    for (String j : untrimmedStates) {
                        if (!states.contains(j)) {
                            states.add(j);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.toString();
            }
            
            userConfirm = JOptionPane.showInputDialog(null, "Would you like to "
                    + "input a string (Y/N)?");
            
            //if for the cancel button to avoid null pointer
            if (userConfirm != null) {
                while(userConfirm.equals("Y")) {
                    userInputString = JOptionPane.showInputDialog("Please enter an "
                        + "input string with no spaces");
            
                    String current = startState;
                    ArrayList<String> fin;
                    fin = acceptStates;
                    //for each element in the user string
                    for (int i = 0; i < userInputString.length(); i++) {
                        /* 
                        check if any of the transition objects in the arraylist have 
                        this element associated with the current state. If so, change
                        the current state to new toState and break to move onto the
                        next character in the input string 
                        */
                        for (Transition j : transitions) {
                            if (j.getFromState().equals(current) && 
                               j.getLabel().equals(userInputString.charAt(i))) {
                                current = j.toState;
                                break;
                            }
                        }
                    }
                    if (fin.contains(current)) {
                        //System.out.println("The DFA accepted your string");
                        JOptionPane.showMessageDialog(null, "The dfa ACCEPTED"
                            + " your string");
                    }
                    else {
                        //System.out.println("The DFA did not accept your string");
                        JOptionPane.showMessageDialog(null, "The dfa DID NOT ACCEPT"
                            + " your string");
                    }
                    userConfirm = JOptionPane.showInputDialog(null, "Would you like"
                            + " to input another string (Y/N)?");
                }
            }
        } 
    }
}
