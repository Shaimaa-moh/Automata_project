import java.io.*;
import java.util.*;
import java. util. Scanner;
public class DFA {



    public static class NFATransitionTable {

        public Map<Integer, Map<String, Set<Integer>>> table;

        public NFATransitionTable() {

            table = new HashMap<>();
        }

        public void addTransition(int state, String symbol, int nextState) {
            Map<String, Set<Integer>> transitions = table.computeIfAbsent(state, k -> new HashMap<>());
            Set<Integer> nextStates = transitions.computeIfAbsent(symbol, k -> new HashSet<>());
            nextStates.add(nextState);
        }

        public Set<Integer> getNextStates(int state, char symbol) {
            Map<String, Set<Integer>> transitions = table.get(state);
            if (transitions == null) {
                return null;
            }
            Set<Integer> nextStates = transitions.get(symbol);
            return (nextStates != null) ? nextStates : new HashSet<Integer>();
        }

        public Set<Integer> getStates() {
            return table.keySet();
        }

    }


//
//    public static void main(String[] args) throws IOException {
//
////        Scanner sc = new Scanner(System.in);
////        System.out.println("1 - NFA to DFA");
////        System.out.println(dfa);
////        System.out.println("2 - Minimize DFA");
////
////        int choice = sc.nextInt();
////
////        if (choice == 1) {
////            NFA_to_DFA.nfa_to_dfa();
////        } else {
////            DFA_Minimizer.minimize();
//        NFATransitionTable table = new NFATransitionTable();
//        table.addTransition(0, "a", 1);
//        table.addTransition(0, "b", 0);
//        table.addTransition(1, "a", 1);
//        table.addTransition(1, "b", 2);
//        table.addTransition(2, "a", 2);
//        table.addTransition(2, "b", 2);
//        for(Map.Entry<Integer, Map<String, Set<Integer>>> set:table.table.entrySet()){
//            System.out.print(set.getKey()+ " " + set.getValue());
//
//        }





}