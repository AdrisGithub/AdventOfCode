package main.dayOne;

import java.io.*;
import java.util.ArrayList;

public class CalorieCalc {

    private final ArrayList<ArrayList<Integer>> elvesCalories;
    private final ArrayList<Integer> elvesSumCalories;

    public CalorieCalc() {
        this.elvesCalories = new ArrayList<>();
        this.elvesSumCalories = new ArrayList<>();
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
        calcSumOfEachElf();
    }
    private void calcSumOfEachElf(){
        for (ArrayList<Integer> ints:elvesCalories) {
            int temp =0;
            for (Integer i:ints) {
                temp += i;
            }
            elvesSumCalories.add(temp);
        }
    }
    public long calc3HighestElves(){
        Object[] sortedCal= elvesSumCalories.stream().sorted().toArray();
        return((int) sortedCal[sortedCal.length-1]+(int) sortedCal[sortedCal.length-2]+(int) sortedCal[sortedCal.length-3]);
    }
    public int calcMostCalories(){
        Object[] sortedCal= elvesSumCalories.stream().sorted().toArray();
        return (int) sortedCal[sortedCal.length-1];
    }

    public static void main(String[] args) {
        CalorieCalc calc = new CalorieCalc();
        System.out.println(calc.calcMostCalories());
        System.out.println(calc.calc3HighestElves());
    }
}
