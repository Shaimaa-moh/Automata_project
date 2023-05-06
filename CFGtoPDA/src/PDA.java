import java.util.ArrayList;
import java.util.HashMap;


import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;


public class PDA {

    //M = (Q, Σ, Γ, δ, q0, z,F)
    ArrayList<String> states; //Q
    ArrayList<String> pdaInputs; //sigma alphabet
    ArrayList<String> stackInputs; //┌ gamma pushdown symbols
    HashMap<MutableTriple<String, String, String>, ArrayList<MutablePair<String, String>>>  transitions; //mapping function δ(q,a,c) = {(r1,d), (r1,c)}
    String startState; //q0 ∈ Q
    String startSymbol; //z ∈ Γ
    ArrayList<String> finalStates; //F ⊆ Q

    //Triple<String, String, String> instantDesc; //triple (q, w, α)

    public PDA(){
        this.states = new ArrayList<>();
        this.pdaInputs = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.transitions = new HashMap<>();

    }
    public PDA(CFG cfg){
        //for ε-free cfg, there exist NPDA:
        //M = ({q0, q1, qf}, T,V ∪{z}, δ,q0, z, {qf})
        this();
        this.states.add("q0"); //initial state
        this.states.add("q1"); //mid state
        this.states.add("qf"); //final state
        this.pdaInputs = (ArrayList<String>) cfg.getTList().clone();
        this.stackInputs = (ArrayList<String>) cfg.getVList().clone();
        this.stackInputs.add("z"); //start stack symbol
        this.startState = "q0";
        this.startSymbol = "z";
        this.finalStates.add("qf");
    }

}
