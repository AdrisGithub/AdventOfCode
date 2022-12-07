package main.dayFive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupplyStacks {

    private final BufferedReader read;
    private final List<List<Character>> containers;
    public SupplyStacks() {
        this.containers = new ArrayList<>();
        try{
            this.read = new BufferedReader(new FileReader("src/resource/inputSupplyStacks.txt"));
            readSupplyFromFile();
        }catch(IOException io){
            throw new RuntimeException("Error 404");
        }
    }

    private void readSupplyFromFile() throws IOException {
        String input;
        List<String> containerContent = new ArrayList<>();
        int amountOfContainers = 0;
        while(!(input = read.readLine()).equals("")){
            if(input.charAt(1) == '1'){
                amountOfContainers = Character.getNumericValue(input.charAt(input.length()-2));
            }else{
                containerContent.add(input);
            }
        }
        for (int i = 0; i<amountOfContainers;i++){
            List<Character> container = new ArrayList<>();
            for (int o = containerContent.size()-1;o>=0;o--){
                char content = containerContent.get(o).charAt(i*4+1);
                if(content!=' '){
                    container.add(content);
                }
            }
            containers.add(container);
        }
    }
    private void doCraneOrders(int amount,int from,int to){
        for (int i=0;i<amount;i++){
            char toBeMoved = containers.get(from-1).get(containers.get(from-1).size()-1);
            containers.get(to-1).add(toBeMoved);
            int index = containers.get(from-1).size()-1;
            containers.get(from-1).remove(index);
        }
    }
    private void doMultiCraneOrders(int amount, int from, int to) {
        for (int i=0;i<amount;i++){
            char toBeMoved = containers.get(from-1).get(containers.get(from-1).size()-amount+i);
            containers.get(to-1).add(toBeMoved);
            int index = containers.get(from-1).size()-amount+i;
            containers.get(from-1).remove(index);
        }
    }
    public void solvePuzzle() throws IOException {
        String input;
        while((input = read.readLine())!=null){
            String[] test = input.split(" ");
            //doCraneOrders(Integer.parseInt(test[1]),Integer.parseInt(test[3]),Integer.parseInt(test[5]));
            doMultiCraneOrders(Integer.parseInt(test[1]),Integer.parseInt(test[3]),Integer.parseInt(test[5]));
        }
        printHighestContentInContainer();
    }
    private void printHighestContentInContainer() {
        StringBuilder str = new StringBuilder();
        for (List<Character> container:containers) {
            str.append(container.get(container.size()-1));
        }
        System.out.println(str);
    }


    public static void main(String[] args) {
        SupplyStacks sup = new SupplyStacks();
        try{
            sup.solvePuzzle();
        }catch(IOException io){
            throw new RuntimeException();
        }
    }




}
