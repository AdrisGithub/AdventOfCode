package main.dayFour;

import java.io.BufferedReader;///
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CampCleanup {
    private final List<List<Integer>> elfPairs;
    private int counter;

    public CampCleanup() {
        this.elfPairs = new ArrayList<>();
        this.counter = 0;
        try{
            readPairsFromFile();
        }catch(IOException io){
            throw new RuntimeException("Error 404");
        }
    }

    private void readPairsFromFile() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("src/resource/inputCampCleanup.txt"));
        String line;
        while((line = read.readLine())!= null){
            ArrayList<Integer> sections = new ArrayList<>();
            StringBuilder test = new StringBuilder();
            for (int i=0;i<line.length();i++){
                if (line.charAt(i) != '-' && line.charAt(i)!=',' && line.charAt(i)!='\n'){
                    test.append(line.charAt(i));
                }else{
                    sections.add(Integer.parseInt(test.toString()));
                    test = new StringBuilder();
                }
            }
            sections.add(Integer.parseInt(test.toString()));
            elfPairs.add(sections);
        }
    }
    public int updateUselessElvesCounter(){
        for (List<Integer> sections: elfPairs) {
            if(numberFitIntoNumber(sections.get(0),sections.get(1),sections.get(2),sections.get(3))||numberFitIntoNumber(sections.get(2),sections.get(3),sections.get(0),sections.get(1))||numberIsInNumber(sections.get(2),sections.get(3),sections.get(0),sections.get(1))||numberIsInNumber(sections.get(0),sections.get(1),sections.get(2),sections.get(3))){
                counter++;
            }
        }
        return counter;
    }

    private boolean numberFitIntoNumber(Integer elfOneNumOne, Integer elfOneNumTwo, Integer elfTwoNumOne, Integer elfTwoNumTwo) {
        return elfOneNumOne >= elfTwoNumOne && elfOneNumTwo <= elfTwoNumTwo && elfOneNumOne <= elfTwoNumTwo && elfOneNumTwo >= elfTwoNumOne;
    }
    private boolean numberIsInNumber(Integer elfOneNumOne, Integer elfOneNumTwo, Integer elfTwoNumOne, Integer elfTwoNumTwo){
        return (elfOneNumOne >= elfTwoNumOne && elfOneNumTwo <= elfTwoNumTwo || elfOneNumOne <= elfTwoNumTwo && elfOneNumTwo >= elfTwoNumOne);
    }

    public static void main(String[] args) {
        CampCleanup clean = new CampCleanup();
        System.out.println(clean.updateUselessElvesCounter());
    }
}
