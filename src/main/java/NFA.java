import java.io.*;
import java.util.*;
import java. util. Scanner;

public class NFA {
    int [] states;
    char[] alphabets;
    int[] transition;
    int start;
    int[] Final;
    Map<String, Integer> Transition_Table = new HashMap<String, Integer>();
    public NFA(int [] states,char[] alphabets, int[] transition, int start, int[] Final){
        this.states=states;
        this.alphabets=alphabets;
        this.transition=transition;
        this.start=start;
        this.Final=Final;

    }

//    public
}
