import org.apache.commons.lang3.tuple.MutableTriple;

import java.util.*;

public class PDAGraph {

    // q0: {(q1, (ε,ε,z)), (q2, (ε,ε,ε))}
//    HashMap<...> adjList;
//    String, ArrayList<...>
//    MutablePair<...>
//    String, MutableTriple<...>
//    String, String, String





    HashMap<String, ArrayList<Transition>> wdigraph;

    private class Transition {
        String to;

        Weight weight = new Weight();
    }
    private class Weight {
        String read, pop, push;
    }


//     states; //aka: nodes //q0, q1, qf, etc.
//     transitions; //aka: edges //(q0, q1) , (q1, q2), etc.
//     weights; //read, pop, push //ε, ε -> z represented as (ε,ε,z)
//    //aka: next symbol on input tape, top symbol of stack, new top symbol of stack


    public PDAGraph() {

        this.wdigraph = new HashMap<>();
    }

    public String addState(String newStateName){ //optional param
        String stateName = newStateName;
        this.wdigraph.put(stateName, new ArrayList<Transition>());
        return stateName;
    }
    public String addState() {
        String stateName;
        if(this.wdigraph.isEmpty()){
            stateName = "q0";
        }
        else{
            stateName = "q" + this.wdigraph.size();
        }
        this.wdigraph.put(stateName, new ArrayList<Transition>());
        return stateName;
    }

    public void addTrans(String q0, String q1, MutableTriple<String, String, String> newTrans) {
        //building transition
        Transition tempTrans = new Transition();
        tempTrans.to = q1;

        tempTrans.weight.read = newTrans.getLeft();
        tempTrans.weight.pop = newTrans.getMiddle();
        tempTrans.weight.push = newTrans.getRight();

//        System.out.println("tempTrans: " + tempTrans.to + ", "
//                + "(" + tempTrans.weight.read + ", "
//                + tempTrans.weight.pop + ", "
//                + tempTrans.weight.push + ")");



        ArrayList<Transition> tempArr = new ArrayList<>();
        tempArr = this.wdigraph.get(q0); //q0: {this part}
        tempArr.add(tempTrans);
        //printing all transitions
        for (HashMap.Entry<String, ArrayList<Transition>> node: this.wdigraph.entrySet()) {

            for (int i = 0; i < node.getValue().size(); i++) {

                System.out.println("tempTrans for " + node.getKey() + ": " + node.getValue().get(i).to + ", "
                        + "(" + node.getValue().get(i).weight.read + ", "
                        + node.getValue().get(i).weight.pop + ", "
                        + node.getValue().get(i).weight.push + ")");
            }
        }


            System.out.println();
        //finally
        this.wdigraph.put(q0, tempArr);
        }





    public void printGraph() {
        System.out.println("hashmap: "+ this.wdigraph);
        System.out.println("values: " + this.wdigraph.values());
        //this.wdigraph.get("q0").;

    }



}