package main.dayEight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeHouse {
    private final List<List<Integer>> heightMap;
    private final boolean[][] viewed;

    public TreeHouse(){
        this.heightMap = new ArrayList<>();
        try{
            readHeightMapFromFile();
            this.viewed = new boolean[heightMap.size()][heightMap.size()];
        }catch(IOException io){
            throw new RuntimeException("Error 404");
        }
    }

    private void readHeightMapFromFile() throws IOException {
        BufferedReader read = new BufferedReader( new FileReader("src/resource/test.txt"));
        String input;
        while((input = read.readLine()) != null){
            List<Integer> row = new ArrayList<>();
            for (int i=0;i<input.length();i++){
                row.add(Character.getNumericValue(input.charAt(i)));
            }
            heightMap.add(row);
        }
    }
    public int calcViewableTrees(){
        int sumOfALlTrees=countAmountOFTreesEast();
        sumOfALlTrees += countAmountOFTreesNorth();
        sumOfALlTrees += countAmountOFTreesSouth();
        sumOfALlTrees += countAmountOFTreesWest();
        return sumOfALlTrees;
    }
    private int countAmountOFTreesNorth(){
        int amountOfTrees=0;
        int[] highestHeight = new int[heightMap.size()];
        Arrays.fill(highestHeight, -1);
        for (int o=0;o< heightMap.size();o++) {
            for (int i=0;i<heightMap.size();i++){
                if(highestHeight[i]<heightMap.get(o).get(i)&& !viewed[o][i]){
                    viewed[o][i] = true;
                    amountOfTrees++;
                    highestHeight[i] = heightMap.get(o).get(i);
                }
            }
        }
        return amountOfTrees;
    }
    private int countAmountOFTreesWest(){
        int amountOfTrees=0;
        int[] highestHeight = new int[heightMap.size()];
        Arrays.fill(highestHeight, -1);
        for (int o=0;o< heightMap.size();o++) {
            for (int i=0;i<heightMap.size();i++){
                if(highestHeight[i]<heightMap.get(i).get(o)&& !viewed[i][o]){
                    viewed[i][o] = true;
                    amountOfTrees++;
                    highestHeight[i] = heightMap.get(i).get(o);
                }
            }
        }
        return amountOfTrees;
    }
    private int countAmountOFTreesEast(){
        int amountOfTrees=0;
        int[] highestHeight = new int[heightMap.size()];
        Arrays.fill(highestHeight, -1);
        for (int o=heightMap.size()-1;o>=0;o--) {
            for (int i=0;i<heightMap.size();i++){
                if(highestHeight[i]<heightMap.get(i).get(o)&& !viewed[i][o]){
                    viewed[i][o] = true;
                    amountOfTrees++;
                    highestHeight[i] = heightMap.get(i).get(o);
                }
            }
        }
        return amountOfTrees;
    }

    private int countAmountOFTreesSouth(){
        int amountOfTrees=0;
        int[] highestHeight = new int[heightMap.size()];
        Arrays.fill(highestHeight, -1);
        for (int i=heightMap.size()-1;i>=0;i--) {
            for (int o=heightMap.size()-1;o>=0;o--){
                if(highestHeight[i]<heightMap.get(o).get(i)&& !viewed[o][i]){
                    viewed[o][i] = true;
                    amountOfTrees++;
                    highestHeight[i] = heightMap.get(o).get(i);
                }
            }
        }
        return amountOfTrees;
    }
    public static void main(String[] args) {
        TreeHouse th = new TreeHouse();
        System.out.println(th.calcViewableTrees());
    }
}
