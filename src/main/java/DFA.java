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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = Integer.parseInt(scan.readLine());
        dfa = new State[n];
        for (int i = 0; i < n; i++) {
            dfa[i] = new State(i);
//        System.out.println(dfa[i]);
        }
        String temp;
        inputSymbols = new HashSet<>();

        temp = scan.readLine();
        start = Integer.parseInt(temp.substring(6));

        temp = scan.readLine();
        while (!temp.startsWith("final")) {
            StringTokenizer st = new StringTokenizer(temp);

            int from = Integer.parseInt(st.nextToken());
            String at = st.nextToken();
            int to = Integer.parseInt(st.nextToken());
            dfa[from].put(at, dfa[to]);
            inputSymbols.add(at);
            temp = scan.readLine();
        }


        final_states = new Group(0);

        StringTokenizer st = new StringTokenizer(temp);
        st.nextToken();
        while (st.hasMoreTokens()) {
            final_states.add(dfa[Integer.parseInt(st.nextToken())]);
        }


    }
    public static class NFATransitionTable {

        private Map<Integer, Map<String, Set<Integer>>> table;

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



    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("1 - NFA to DFA");
//        System.out.println(dfa);
//        System.out.println("2 - Minimize DFA");
//
//        int choice = sc.nextInt();
//
//        if (choice == 1) {
//            NFA_to_DFA.nfa_to_dfa();
//        } else {
//            DFA_Minimizer.minimize();
        NFATransitionTable table = new NFATransitionTable();
        table.addTransition(0, "a", 1);
        table.addTransition(0, "b", 0);
        table.addTransition(1, "a", 1);
        table.addTransition(1, "b", 2);
        table.addTransition(2, "a", 2);
        table.addTransition(2, "b", 2);
        for(Map.Entry<Integer, Map<String, Set<Integer>>> set:table.table.entrySet()){
            System.out.print(set.getKey()+ " " + set.getValue());

        }


    }


}