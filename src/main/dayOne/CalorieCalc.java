package main.dayOne;

import java.io.*;
import java.util.ArrayList;

public class CalorieCalc {

    private final ArrayList<ArrayList<Integer>> elvesCalories;

    public CalorieCalc() {
        this.elvesCalories = new ArrayList<>();
        try {
            readCaloriesFromFile();
        }catch(IOException no){
            throw new RuntimeException("Error 404");
        }
    }

    private void readCaloriesFromFile() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("src/resource/inputCalories.txt"));
        String input;
        ArrayList<Integer> elf= new ArrayList<>();
        while((input = read.readLine())!=null){
            if(!input.equals("")){
                elf.add(Integer.parseInt(input));
            }else{
                elvesCalories.add(elf);
                elf = new ArrayList<>();
            }
        }
    }
    public int calcMostCalories(){
        int highestCal=0;
        for (ArrayList<Integer> ar:elvesCalories) {
            int elvesCal = 0;
            for (Integer i:ar) {
                elvesCal += i;
            }
            if(elvesCal>highestCal){
                highestCal = elvesCal;
            }
        }
        return highestCal;
    }

    public static void main(String[] args) {
        CalorieCalc calc = new CalorieCalc();
        System.out.println(calc.calcMostCalories());
    }
}
