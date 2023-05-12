/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_automata;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author HP
 */

public class Convert_to_DFA {
       //Step 1: Create DFA's start state.
                Table<String, String, ArrayList<String>> NFATable = HashBasedTable.create();
                Table<String, String, String> DFATable = HashBasedTable.create();
                int noAlphabets; // no_of alphabets
                int noStates;
                String startState;
            ArrayList<String> Nfa_Finalstates = new ArrayList<>();
            ArrayList<String>NFA_states = new ArrayList<>();
            ArrayList<String> alphabets = new ArrayList<>();
            ArrayList<String> arr = new ArrayList<>();
            String currentState;
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
            System.out.println("Enter no of trainsitions state");
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
                   arr=(ArrayList<String>) NFATable.get(currState,inAlpha).clone();
                   arr.add(nextSt);
                    NFATable.put(currState,inAlpha,arr); 
               }
               
               else{
                   arr=(ArrayList<String>) NFATable.get(currState,inAlpha).clone();
                   arr.add(nextSt);
                   NFATable.put(currState,inAlpha,arr); 
               }
                 
           }
           
           System.out.println("NFA Table is : " +NFATable) ;
           System.out.println();
             }
             
             void process_input()
             {
                 
                 newStates.add(startState);

                 Map <String, ArrayList< String >> tranStart= NFATable.row(startState); //row looks like: 0=q0,q1  , 1=q0
                  for(Map.Entry <String, ArrayList<String>> nextState:tranStart.entrySet()){ //where set is: [q0, q1]
                         if(!nextState.getValue().isEmpty()){
                             
                              System.out.println("add trans start state "+startState);
                              System.out.println("add next state key "+nextState.getKey());
                              System.out.println("add next state value "+nextState.getValue());
                             addTrans(startState, nextState.getKey(), nextState.getValue()); // addTrans(q0, a, [q0,q1])
                                  }
                                         else{
                                                 //dead state
                                         }
                            }
              //Step 2: Create DFA's transition table.
			//SortedSet<String> newStates_flag = new TreeSet<String>();
			// newStates_flag.addAll(newStates);
			
			newStates = updateStates(newStates);
			newStates = updateTrans(newStates);
			//System.out.print("dfa " +DFATable);
                        System.out.println("row keys "+DFATable.rowKeySet().toString());
                        System.out.println("new states " + newStates.toString());
			//while(! newStates.toString().contentEquals(DFATable.rowKeySet().toString())){
				//newStates_flag.addAll(newStates);	
				newStates = updateStates(newStates);
				newStates = updateTrans(newStates);
                                newStates = updateStates(newStates);
				newStates = updateTrans(newStates);
                                newStates = updateStates(newStates);
				newStates = updateTrans(newStates);
			//};
           
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
                    System.out.println("DFA Table is : " +DFATable) ;
                    System.out.println("new states are : " + newStates) ; //[q0,q1q2]// 0=q1, 1=[q0,q1]
 
             }
            
             public  void addTrans(String startState, String input, ArrayList<String> nextState){ // add first row in nfa table
		String nextState_str = String.join("_", nextState);
                if(! nextState.isEmpty())
		DFATable.put(startState, input, nextState_str);
	 }   

	 public  SortedSet<String> updateStates(SortedSet<String> newStates){
	 
		for (Cell<String, String, String> cell: DFATable.cellSet()){
		
			if(!newStates.contains(cell.getValue())){
				newStates.add(cell.getValue());
		           for(int i = 0; i < alphabets.size(); i++){
			        DFATable.put(cell.getValue(), alphabets.get(i), "");
                                      System.out.println("cell value" + cell.getValue());
                                      System.out.println("alphabets"+ alphabets.get(i));
                                      
				}	
			}
						
		}
                
		return newStates; 
	 }
	 
	 
         public  SortedSet<String> updateTrans(SortedSet<String> newStates){
	 
		for (String rowKey : DFATable.rowKeySet()){
		
			String str[] = rowKey.split("_");
			List<String> rowKeyArr = new ArrayList<String>();
			rowKeyArr = Arrays.asList(str);                    
			for(String alphabet : DFATable.columnKeySet()){
				SortedSet<String> tempTrans = new TreeSet<>(); // [q0,q1]
				for(int i = 0; i < rowKeyArr.size(); i++){
					String currentRow = rowKeyArr.get(i);   // TODO Null pointer exception
					ArrayList<String> tempState = NFATable.get(currentRow, alphabet);        
                                        if( ! (tempState==null)){
                                          tempTrans.addAll(tempState);   
                                        }
                                    System.out.println( " current row " + currentRow);    
				}
				
				String tempTrans_str = String.join("_", tempTrans);
                                newStates.add(tempTrans_str);
                                System.out.println( " current column " + alphabet);
                                DFATable.put(rowKey,alphabet,tempTrans_str);
			}	
                        
				
		}
            return newStates;
		 
	 }	 

	 
	 
}
