import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class CFG {

    private ArrayList<String> VList; //non-terminals
    private ArrayList<String> TList; //terminals
    private HashMap<String, ArrayList<String>> PMap;  //productions
    private String startSym; //start variable
    private boolean containsEmpty = false;


    public CFG(CFG cfg){
        this.VList = cfg.VList;
        this.TList = cfg.TList;
        this.PMap = cfg.PMap;
        this.startSym = cfg.startSym;
    }
    public CFG(){
        this.VList = new ArrayList<>();
        this.TList = new ArrayList<>();
        this.PMap = new HashMap<>();

        //this.TList.add("ε");

    }

    //removes λ(null)-productions, unit-productions, and useless productions
    public void simplify(){
        //this.removeNullable(this.findNullable());
        //Vector <String> nullableVars= this.findNullable();
        //System.out.println(nullableVars);
        //if(nullableVars.containsAll(List.of(concatenate(this.TList, this.VList)))){
        //    System.out.println("not context-free");
        //}
        //else{
        //    System.out.println(this.PMap);
        //}
    }
    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
    public void toChomsky(){}
    public void toGreibach(){}

    //N.B. ε is what we're looking for.

//    public Vector<String> findNullable(){
//
//        Vector<String> nullableVars = new Vector<>();
//        if(this.PMap.containsValue("ε")){  //grammar contains ε
//            for (String tempLHS : this.PMap.keySet()) {
//                ArrayList<String> tempRHS = this.PMap.get(tempLHS);
//                if(tempRHS.contains("ε")){  //rule contains ε
//                    nullableVars.add(tempLHS);
//                }
//            }
//
//            Vector<String> oldNullVars = (Vector<String>) nullableVars.clone();
//            Vector<String> newNullVars = null;
//            while(!(oldNullVars.equals(newNullVars))){
//                oldNullVars = (Vector<String>) nullableVars.clone();
//                for (String tempLHS : this.PMap.keySet()) {
//                    ArrayList<String> tempRHS = this.PMap.get(tempLHS);
//                    //for every element in tempRHS, if every char in that element is in nullableVar, then map.remove(tempLHS)
//                    //if, for example, A -> ε and B -> ε and C -> AB, then A, B and C are nullable.
//                    //worst case scenario is A->ε and B->A and C->B and S->ε. in which case, we will iterate 4 times.
//                    //In this case ε is a production of Language, and so this language cannot be converted to Chomsky.
//                    // Instead, If λ ∈ L, we add to the constructed npda the transition δ(0,ε,z) = {(f,z)}
//                    //Theorem 6.3 in Peter Linz
//                    for(String prod: tempRHS){
//                        boolean flag = false;
//                        for (int i = 0; i < prod.length(); i++){
//                            char c = prod.charAt(i);
//                            if(nullableVars.contains(c)){
//                                flag = true;
//                            }
//                        }
//                        if(flag==true){
//                            nullableVars.add(tempLHS);
//                        }
//                    }
//                }
//                newNullVars = (Vector<String>) nullableVars.clone();
//            }
//
//
//        }
//
//
//        //does this language contain empty string?
//        if(nullableVars.contains(this.startSym)){
//            this.containsEmpty = true;
//        }
//        return nullableVars;
//    }
    //this function produces grammar without ε-productions
//    public void removeNullable(Vector<String> nullableVars){
//
//    }


    public ArrayList<String> getVList() {
        return VList;
    }

    public void setVList(ArrayList<String> VList) {
        this.VList = VList;
    }

    public ArrayList<String> getTList() {
        return TList;
    }

    public void setTList(ArrayList<String> TList) {
        this.TList = TList;
    }

    public HashMap<String, ArrayList<String>> getPMap() {
        return PMap;
    }

    public void setPMap(HashMap<String, ArrayList<String>> PMap) {
        this.PMap = PMap;
    }

    public String getStartSym() {
        return startSym;
    }

    public void setStartSym(String startSym) {
        this.startSym = startSym;
    }

}
