import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class App {

    Language language;
    public App(){
        this.language = new Language();
        try {
            this.ReceiveCFGInput();
            this.ConvertCFGtoPDA();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void ConvertCFGtoPDA() {
        this.language.GNFtoPDA();
    }


    public void ReceiveCFGInput() throws IOException {
        System.out.println("Please enter CFG.");
        //G = (V, T, P, S), where V: non-terminals
        //T: terminals, P: productions, S: start variable

        Scanner sc = new Scanner(System.in);
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

        ////////////////////////////////////////////////////////
        System.out.print("non-terminals (write the symbols in one line): ");
        String[] tempArr = bi.readLine().split(" ");
        ArrayList<String> tempList = language.cfg.getVList();
        Collections.addAll(tempList, tempArr);
        language.cfg.setVList(tempList);
        for(String ele : language.cfg.getVList()){
            System.out.println(ele);
        }

        ////////////////////////////////////////////////////////
        System.out.print("terminals (write the symbols in one line): ");
        tempArr = bi.readLine().split(" ");
        tempList = language.cfg.getTList();
        Collections.addAll(tempList, tempArr);
        language.cfg.setTList(tempList);
        for(String ele : language.cfg.getTList()){
            System.out.println(ele);
        }
        ////////////////////////////////////////////////////////
        System.out.print("start symbol: ");
        language.cfg.setStartSym(sc.nextLine());
        System.out.println(language.cfg.getStartSym());
        ////////////////////////////////////////////////////////
        System.out.print("production rules (press Ctrl+D when done): "); //multiline input
        try{
            String currentLine;
            String[] prodRule;
            while(sc.hasNextLine())
            {
                currentLine = sc.nextLine();
                prodRule = currentLine.split("( )*( |->|- >|\\|)( )*");
                System.out.println("LHS: " + prodRule[0]);
                System.out.println("RHS: ");
                for(int i = 1; i < prodRule.length; i++){
                    System.out.println("ele: " + prodRule[i]);
                }
                ArrayList<String> tempLHS = new ArrayList<>();
                for(int i = 1; i < prodRule.length; i++){
                    tempLHS.add(prodRule[i]);
                }
                HashMap<String, ArrayList<String>> tempPMap = language.cfg.getPMap();
                tempPMap.put(prodRule[0], tempLHS);
                System.out.println(tempPMap.getClass());
                language.cfg.setPMap(tempPMap);

            }
        }
        catch(NoSuchElementException e){
            System.out.println("(prod terminated)");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }



    }


    public void ProcessCFGInput() {
        //language.cfg.simplify();
        //language.cfg.toChomsky();
        //language.cfg.toGreibach();
    }

    public void CreatePDA() {
    }

    public void DrawPDA() {
    }
}