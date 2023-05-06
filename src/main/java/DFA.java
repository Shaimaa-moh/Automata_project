import java.io.*;
import java.util.*;
import java. util. Scanner;
public class DFA {
    static int start; //stores the id of the start state
    static Set<String> inputSymbols; // stores the set of input symbols
    static State dfa[]; //stores all the states data
    static Group final_states; //group of final states
    static Group non_final_states; //group of non-final states

    public static void input(String file) throws IOException {
    BufferedReader scan = null;
        try {
        scan = new BufferedReader(new FileReader(file));
    }
        catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    int n = Integer.parseInt(scan.readLine());
    dfa =new State[n];
    for (int i = 0; i<n ; i++)

    {
        dfa[i] = new State(i);
    }
        System.out.println(n);
}


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("1 - NFA to DFA");
//        System.out.println("2 - Minimize DFA");
//
//        int choice = sc.nextInt();
//
//        if (choice == 1) {
//            NFA_to_DFA.nfa_to_dfa();
//        } else {
//            DFA_Minimizer.minimize();


    }
}