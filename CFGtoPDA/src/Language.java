import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;

public class Language {

    //different ways to represent a language
    //DFA dfa = new DFA(); ...etc. but first, we will need methods to validate grammar family.
    // + maybe abstract inheritance classes?? if we will add dfa and nfa code to this project.
    CFG cfg = new CFG(); //Context-free Grammar
    CFG cnf = new CFG(cfg); //Chomsky's Normal Form
    CFG gnf = new CFG(cfg); //Greibach's Normal Form
    PDA pda = new PDA(cfg); //Pushdown Automata
    //TODO deterministic and non-deterministic pda




    //TODO fill in conversion functions between cfg->cnf->gnf->pda
    public void GNFtoPDA(){
        System.out.println(pda.toString());


        //first move: δ (q0, λ, z) = {(q1, Sz)}
        ArrayList<MutablePair<String, String>> tempArrPair = new ArrayList<>();

        tempArrPair.removeAll(tempArrPair);
        tempArrPair.add(new MutablePair<>("q1", "Sz")); //next state
        System.out.println("tempArrPair: " + tempArrPair);
        this.addTransitionPDA("q0","ε","z", tempArrPair);


        //final move: δ (q1, λ, z) = {(qf, z)}
        tempArrPair.removeAll(tempArrPair);
        tempArrPair.add(new MutablePair<>("qf", "z"));
        System.out.println("tempArrPair: " + tempArrPair);
        this.addTransitionPDA("q1","ε","z", tempArrPair);

        System.out.println("hashmap transitions: " + pda.transitions);


        //step 3: (q1, u) ∈ δ (q1, a, A), whenever A → au
        //step 4: If λ ∈ L, we add to the constructed npda the transition: δ (q0, ε, z) = {(qf, z)}

    }
    public void addTransitionPDA(String q, String a, String c, ArrayList<MutablePair<String, String>> tempArrPair){
        this.pda.transitions.put(MutableTriple.of(q, a, c), tempArrPair);
    }



}
