import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Convert_to_DFA {
    Table<String, String, ArrayList<String>> NFATable = HashBasedTable.create();
    Table<String, String, String> DFATable = HashBasedTable.create();
    int noAlphabets; // no_of alphabets
    int noStates;
    String startState;
    ArrayList<String> Nfa_Finalstates = new ArrayList<>();
    ArrayList<String>NFA_states = new ArrayList<>();
    ArrayList<String> alphabets = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();
    SortedSet<String> newStates = new TreeSet <String>();//list of new states introduced



    void generate_nfa()
    {
        Scanner AlphabetCount = new Scanner(System.in);
        System.out.println("Enter no_of alphabets");
        noAlphabets= AlphabetCount.nextInt(); // store
        for (int i =0; i < noAlphabets ;i++)
        {
            Scanner Alphabetinput = new Scanner(System.in);
            System.out.println(" enter the alphabets");
            alphabets.add(Alphabetinput.nextLine());

        }
        System.out.println (alphabets);

        Scanner NO_states = new Scanner(System.in);
        System.out.println("Enter no_of states");
        noStates= NO_states.nextInt(); // store

        for (int i =0; i < noStates ;i++)
        {
            String stateName = "q0";

            if(NFA_states.isEmpty()){

                stateName = "q0";
            }
            else{

                stateName = "q" + i;

            }
            NFA_states.add(stateName);

        }
        System.out.println (NFA_states);

        Scanner startSt = new Scanner(System.in);
        System.out.println("Enter start state");
        startState = startSt .nextLine(); // store

        Scanner FinalSt = new Scanner(System.in);
        System.out.println("Enter Final state");
        String finalState = FinalSt.nextLine(); // store
        Nfa_Finalstates.add(finalState);

        Scanner tranistion_in = new Scanner(System.in);
        System.out.println("Enter no of transitions state");
        int trans_no = tranistion_in.nextInt(); // store


        for (int i=0 ; i<trans_no;i++){

            Scanner current = new Scanner(System.in);
            System.out.println("Enter your current state ");
            String currState= current.nextLine();

            Scanner input = new Scanner(System.in);
            System.out.println("Enter your alphabet ");
            String inAlpha= input.nextLine();

            Scanner nextState = new Scanner(System.in);
            System.out.println("Enter your next state ");
            String nextSt= nextState.nextLine();
            //System.out.println("Empty is : " +NFATable.get(currState,inAlpha));

            if(NFATable.get(currState,inAlpha)==null)
            {

                NFATable.put(currState,inAlpha,new ArrayList<>());
            }
            arr=(ArrayList<String>) NFATable.get(currState,inAlpha).clone();
            arr.add(nextSt);
            NFATable.put(currState,inAlpha,arr);

        }

        System.out.println("NFA Table is : " +NFATable) ;
        System.out.println();
    }

    void process_input()
    {

        //Step 1: Create DFA's start state.
        newStates.add(startState);
        for(String col: alphabets) { //where nextState is: [q0, q1]
            DFATable.put(startState, col, "");
        }
        newStates = updateTrans(newStates);


        //Step 2: Create DFA's transition table.
        SortedSet<String> newStates_flag = new TreeSet<String>();
        newStates_flag.addAll(newStates);

        newStates = updateStates(newStates);
        newStates = updateTrans(newStates);
        while(! newStates.toString().contentEquals(newStates_flag.toString())){
            newStates_flag.addAll(newStates);

            newStates = updateStates(newStates);
            newStates = updateTrans(newStates);
        }


        //Step 3: Create DFA's final state.
        SortedSet<String> DFAfinalStates = new TreeSet();

        for(String rowKey : DFATable.rowKeySet()){
            String str[] = rowKey.split("_");
            List<String> rowKeyArr = new ArrayList<String>();
            rowKeyArr = Arrays.asList(str);
            for(int i = 0; i < rowKeyArr.size(); i++){
                if(Nfa_Finalstates.contains(rowKeyArr.get(i))){  //does this sortedset contain anything in this string array?
                    DFAfinalStates.add(rowKey);
                }
            }
        }

        System.out.println("\n");
        System.out.println("DFA Table is : " +DFATable) ;
        System.out.println("DFA states are : " + newStates) ; //[q0, q0_q1, q0_q2]
        System.out.println("DFA final states are : " + DFAfinalStates) ;

    }

//    public  SortedSet<String> addTrans(SortedSet<String> newStates,
//                                       String startState, String input, ArrayList<String> nextState){ // add first row in nfa table
//        String nextState_str = String.join("_", nextState);
//        for(String alph: DFATable.columnKeySet()){
//            if (!nextState.isEmpty()) {  //no transitions from start state to alph
//                newStates.add(nextState_str);
//                DFATable.put(startState, input, nextState_str);
//            } else {
//                DFATable.put(startState, input, nextState_str);
//            }
//        }
//        return newStates;
//    }

    public  SortedSet<String> updateStates(SortedSet<String> newStates){

        Table <String, String, String> DFATable_copy = HashBasedTable.create();
        DFATable_copy.putAll(DFATable);
        for (Cell<String, String, String> cell: DFATable_copy.cellSet()){

            if((!DFATable.rowKeySet().contains(cell.getValue()))
                && (!cell.getValue().equals(""))){  //our table contains a nonempty cell (transState) that is not a rowKey

                newStates.add(cell.getValue());
                for(int i = 0; i < alphabets.size(); i++){
                    DFATable.put(cell.getValue(), alphabets.get(i), "");
                    System.out.println("new row " + cell.getValue() + " alphabets "+ alphabets.get(i) + " value ");

                }
            }

        }

        System.out.println("DFATable (after updateState): "+ DFATable);
        return newStates;
    }


    public  SortedSet<String> updateTrans(SortedSet<String> newStates){

        for (Cell<String, String, String> cell: DFATable.cellSet()){ //let's create transitions for each "" cell
            if(cell.getValue().equals("")){
                String str[] = cell.getRowKey().split("_");
                List<String> rowKeyArr = new ArrayList<String>();
                rowKeyArr = Arrays.asList(str);

                SortedSet<String> tempTrans = new TreeSet<>(); // [q0,q1]
                for(int i = 0; i < rowKeyArr.size(); i++){
                    String currentRow = rowKeyArr.get(i);
                    ArrayList<String> tempState = NFATable.get(currentRow, cell.getColumnKey());
                    if( ! (tempState==null)){
                        tempTrans.addAll(tempState);
                    }
                    //System.out.println( " current row " + currentRow);
                }

                String tempTrans_str = String.join("_", tempTrans);
                newStates.add(tempTrans_str);
                //System.out.println( " current column " + cell.getColumnKey());

                if(tempTrans_str.isEmpty()){
                    tempTrans_str = "dead";
                }

                DFATable.put(cell.getRowKey(), cell.getColumnKey(), tempTrans_str);
                System.out.println("updating trans at: ("+ cell.getRowKey()+", "+
                        cell.getColumnKey()+") value: "+ tempTrans_str);
            }

        }


        System.out.println("DFATable (after updateTrans): "+ DFATable);
        return newStates;

    }



}
