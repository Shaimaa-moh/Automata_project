import java.util.*;
import java.lang.*;

public class NFA {
    int[] states;
    char[] alphabets;
    int[][] transition;
    int start;
    int[] Final;
    HashMap<String, String> Transition_Table = new HashMap<String, String>();

    HashMap<Integer, Integer> No_States = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> No_Alphabets = new HashMap<Integer, Integer>();

    public NFA(int[] states, char[] alphabets, int[][] transition, int start, int[] Final) {
        this.states = states;
        this.alphabets = alphabets;
        this.transition = transition;
        this.start = start;
        this.Final = Final;

    }

}

//    public void convert(){
//        for(int i=0;i<states.length;i++ ){
//            No_States.put(states[i],i);
//        }
//        for(int i=0;i<alphabets.length;i++ ){
//            No_Alphabets.put((int) alphabets[i],i);
//        }
//        for(int i=0;i<states.length;i++ ){
//            for(int j=0;j<alphabets.length;j++ ) {
//                Transition_Table.put(Integer.toString(i), Integer.toString(j));
//            }
//        }
//        for(int k=0; k<transition.length;k++){
//            Transition_Table.get(No_States.get(transition[k][0])).concat(String.valueOf(No_Alphabets.get(transition[k][1]))).append(No_States.get(transition[k][2]));
//
//        }
//
//
//
////        for i in range(self.no_state):
////        for j in range(self.no_alphabet):
////        self.transition_table[str(i) + str(j)] = []
////        for i in range(self.no_transition):
////        self.transition_table[str(self.states_dict[self.transitions[i][0]])
////                + str(self.alphabets_dict[
////                self.transitions[i][1]])].append(
////                self.states_dict[self.transitions[i][2]])
//
//    }
//}
