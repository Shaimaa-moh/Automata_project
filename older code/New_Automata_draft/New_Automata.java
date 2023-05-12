/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_automata;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author HP
 */
public class New_Automata {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
        
            Convert_to_DFA DFA = new  Convert_to_DFA();
            DFA.generate_nfa();
            DFA.process_input();
            
          
        
    }
    
}
