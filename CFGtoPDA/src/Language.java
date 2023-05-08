import org.apache.commons.lang3.tuple.MutableTriple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Language {

    //different ways to represent a language
    //DFA dfa = new DFA(); ...etc. but first, we will need methods to validate grammar family.
    // + maybe abstract inheritance classes?? if we will add dfa and nfa code to this project.
    CFG cfg = new CFG(); //Context-free Grammar
//    CFG cnf = new CFG(cfg); //Chomsky's Normal Form
//    CFG gnf = new CFG(cfg); //Greibach's Normal Form
    PDA pda = new PDA(cfg); //Pushdown Automata




    //converts deterministic context-free grammar to pda.
    public void DCFGtoDPDA(){

        PDAGraph pdaGraph = new PDAGraph();

        // step 1: q0 -> q1 where ε, ε->z
        String q0 = pdaGraph.addState(); //initial state
        String q1 = pdaGraph.addState();
        pdaGraph.addTrans("q0", "q1", MutableTriple.of("ε", "ε", "z"));


        // step 2: q1 -> q2 where ε, ε->S and S is the start variable
        String startVar = cfg.getStartSym();
        String q2 = pdaGraph.addState(); //q2 is qloop, a general mid-state
        pdaGraph.addTrans("q1", "q2", MutableTriple.of("ε", "ε", startVar));

        // step 3: q2 -> q2 , a general mid-state
        // for all terminals, a,a->ε
        for(int i = 0; i < cfg.getTList().size(); i++){
            pdaGraph.addTrans("q2", "q2", MutableTriple.of(cfg.getTList().get(i), cfg.getTList().get(i), "ε"));
        }

        //step 4: fill qloop (q2)
        // S -> aBc | ab
        for (HashMap.Entry<String, ArrayList<String>> rule : cfg.getPMap().entrySet()) {
            String LHS = rule.getKey(); // returns "S"
            ArrayList<String> RHS = rule.getValue(); // returns " {aBc, ab}
            for(int i = 0; i < RHS.size(); i++){
                String RHS_rule = RHS.get(i); // returns aBc
                String tempPop=null, oldState=null, newState=null;
                if(RHS_rule.length() == 1){ //corner case to save time: if rule length is 1, just a single trans.

                    System.out.println("LHS: " + LHS + "  RHS: " + RHS);
                    System.out.println("RHS_rule: " + RHS_rule);
                    System.out.println("Transition added: q2, q2, "
                            + "ε, " + LHS + ", " + RHS_rule);

                    pdaGraph.addTrans("q2", "q2", MutableTriple.of("ε", LHS, RHS_rule));
                }
                else{
                    for(int j = 0; j < RHS_rule.length(); j++){  //loops over aBc, char by char
                        String RHS_rule_char = String.valueOf(RHS_rule.charAt(RHS_rule.length() - j - 1)); //returns c
                        if(j == 0){
                            tempPop = LHS;
                            oldState = "q2";
                            newState = pdaGraph.addState();
                        }
                        else if(j == RHS_rule.length() - 1){
                            tempPop = "ε";
                            oldState = newState;
                            newState = "q2";
                        }
                        else{
                            tempPop = "ε";
                            oldState = newState;
                            newState = pdaGraph.addState();
                        }

                        System.out.println("LHS: " + LHS + "  RHS: " + RHS);
                        System.out.println("RHS_rule: " + RHS_rule + "  RHS_rule_char: " + RHS_rule_char);
                        System.out.println("Transition added: " + oldState + ", " + newState + ", "
                                + "ε, " + tempPop + ", " + RHS_rule_char);

                        pdaGraph.addTrans(oldState, newState, MutableTriple.of("ε", tempPop, RHS_rule_char));
                    }
                }


            }


        }



        // step 5: q2 -> qf , a final state
        String qf = pdaGraph.addState("qf");
        pdaGraph.addTrans("q2", "qf", MutableTriple.of("ε", "z", "ε"));
        pdaGraph.printGraph();




    }

//    public void DCFGtoDPDA(){
//        System.out.println(pda.toString());
//
//
//        //first move: δ (q0, λ, z) = {(q1, Sz)}
//        ArrayList<MutablePair<String, String>> tempArrPair = new ArrayList<>();
//
//        tempArrPair.removeAll(tempArrPair);
//        tempArrPair.add(new MutablePair<>("q1", "Sz")); //next state
//        System.out.println("tempArrPair: " + tempArrPair);
//        this.addTransitionPDA("q0","ε","z", tempArrPair);
//
//
//        //final move: δ (q1, λ, z) = {(qf, z)}
//        tempArrPair.removeAll(tempArrPair);
//        tempArrPair.add(new MutablePair<>("qf", "z"));
//        System.out.println("tempArrPair: " + tempArrPair);
//        this.addTransitionPDA("q1","ε","z", tempArrPair);
//
//        System.out.println("hashmap transitions: " + pda.transitions);
//
//
//        //step 3: (q1, u) ∈ δ (q1, a, A), whenever A → au
//        //step 4: If λ ∈ L, we add to the constructed npda the transition: δ (q0, ε, z) = {(qf, z)}
//
//    }
//    public void addTransitionPDA(String q, String a, String c, ArrayList<MutablePair<String, String>> tempArrPair){
//        this.pda.transitions.put(MutableTriple.of(q, a, c), tempArrPair);
//    }



}
