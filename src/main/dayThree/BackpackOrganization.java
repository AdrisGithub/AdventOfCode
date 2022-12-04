package main.dayThree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackpackOrganization {
    List<String> backpacks;
    List<Integer> priorities;

    public BackpackOrganization(){
        this.backpacks = new ArrayList<>();
        this.priorities = new ArrayList<>();
        try {
            readBackpacksFromFile();
            for (String s:backpacks) {
                calcPriorities(getDuplicateItemOutOfBackpack(s));
            }

        }catch (IOException io){
            throw new RuntimeException("Error 404");
        }
    }

    private char getDuplicateItemOutOfBackpack(String s) {
        char[][] cutString = cutStringInHalf(s);
        for (int i=0;i<cutString[0].length;i++){
            for (int o=0;o<cutString[0].length;o++){
                if(cutString[0][i]==cutString[1][o]){
                    return cutString[1][o];
                }
            }
        }
        return 0;
    }

    private char[][] cutStringInHalf(String s) {
        char[][] result = new char[2][s.length()/2];
        for (int i=0;i<s.length();i++){
            if(i<s.length()/2){
                result[0][i]= s.charAt(i);
            }else{
                result[1][i-s.length()/2]= s.charAt(i);
            }
        }
        return result;
    }

    private void calcPriorities(char item) {
        if(item == 0){
            throw new RuntimeException("Something went wrong i can feel it");
        }
        char lowerCase = Character.toLowerCase(item);
        int result = lowerCase - 96;
        if(item <=90){
            priorities.add(result+26);
        }else{
            priorities.add(result);
        }
    }


    private void readBackpacksFromFile() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("src/resource/inputBackpack.txt"));
        String input;
        while((input = read.readLine())!=null){
            backpacks.add(input);
        }
    }
    public int sumOfAllPriorities(){
        int result =0;
        for (Integer i:priorities) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) {
        BackpackOrganization orga = new BackpackOrganization();
        System.out.println(orga.sumOfAllPriorities());
    }
}
