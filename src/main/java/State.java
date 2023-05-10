import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.*;
public class State extends NullPointerException {


    static int noAlphabets; // no_of alphabets
    static int noStates;
    static String startState;

    static ArrayList<String> Finalstates = new ArrayList<>();
    static ArrayList<String>NFA_states = new ArrayList<>();
    static ArrayList<String> alphabets = new ArrayList<>();
    String currentState;
    Map <Integer, Map<String, Set<Integer>>> table2; //dfa

    ArrayList<String>DFA_states = new ArrayList<>();

    static DFA.NFATransitionTable table = new DFA.NFATransitionTable();
    Map <String, Set<Integer>> s= table.table.get(table.table.keySet().toArray()[0]); //value of first key
    Map<String, Set<Integer>> values= table.table.get (table.table.keySet()); //split value {0=[1,2}, 1=[q]}

    public static void main(String[] args) {

 ArrayList<String> transitions = new ArrayList<>();
 //HashMap<String, String> transitions = new HashMap<String, String>();
///////////////////////////////////////////////////////////////////////////////////////////////


Scanner AlphabetCount = new Scanner(System.in);
 System.out.println("Enter no_of alphabets");
 noAlphabets= AlphabetCount.nextInt(); // store 
 for (int i =0; i < noAlphabets ;i++)
{
Scanner Alphabetinput = new Scanner(System.in);
System.out.println(" enter the alphabets");
alphabets.add(Alphabetinput.nextLine());

        }
        Scanner NO_states = new Scanner(System.in);
 System.out.println("Enter no_of states");
 noStates= NO_states.nextInt(); // store

 for (int i =0; i < noStates ;i++) {


     String stateName = "q0";

     if (NFA_states.isEmpty()) {
         stateName = "q0";

     } else {

         stateName = "q" + i;

     }

     NFA_states.add(stateName);
 }

  for (int j=0;j<noStates;j++){
   System.out.println (NFA_states.get(j));
}
 Scanner startSt = new Scanner(System.in);
 System.out.println("Enter start state");
 startState= startSt .nextLine(); // store


 Scanner FinalSt = new Scanner(System.in);
 System.out.println("Enter Final state");
 String finalState = FinalSt.nextLine(); // store  
 Finalstates.add(finalState);

 Scanner tranistion_in = new Scanner(System.in);
 System.out.println("Enter no of trainsitions state");
int trans_no = tranistion_in.nextInt(); // store

 //ConversionNFA table = new ConversionNFA();
 //current , alphabet , next state

for (int i=0 ; i<trans_no;i++){

    Scanner current = new Scanner(System.in);
    System.out.println("Enter your current state ");
    int currState= current.nextInt();

    Scanner input = new Scanner(System.in);
    System.out.println("Enter your alphabet ");
    String inAlpha= input.nextLine();

    Scanner nextState = new Scanner(System.in);
    System.out.println("Enter your next state ");
    int nextSt= nextState.nextInt();
     table.addTransition(currState,inAlpha,nextSt);


}


    System.out.print("NFA table is : ");


 for(Integer i : table.table.keySet()) {
     System.out.print(i + "->" + table.table.get(i));
     System.out.println();


 }


 //System.out.println("transitions of start state is : ");


 //for(Map.Entry<Integer, Map<String, Set<Integer>>> set:table.table.entrySet()){
 // String start_dfa="q0";
 //DFA_states.add(start_dfa); //list feha q0
 // Map <String, Set<Integer>> s= table.table.get(table.table.keySet().toArray()[0]); //value of first key
         ArrayList<String> Alpha = new ArrayList<>();

 Map<String, Set<Integer>> values= table.table.get (table.table.keySet());
//for(Map.Entry<String, Set<Integer>> set:values.entrySet()){
for(String i : values.keySet()){
  Alpha.add(i) ;

}
for (int k=0;k<Alpha.size();k++) {
    System.out.println("inputs are " + Alpha.get(k));
}
//find the possible set of states for each input symbol. If this set of states is not in Q', then add it to Q'.
 // we can split transition to get alphabet



 }

}
