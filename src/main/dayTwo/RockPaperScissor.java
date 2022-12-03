package main.dayTwo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RockPaperScissor {
    List<String> moves;
    List<Integer> score;

    public RockPaperScissor() {
        this.moves = new ArrayList<>();
        this.score = new ArrayList<>();
        try {
            readMovesFromFile();
            computeScoreForMoves();
        }catch(IOException io){
            throw new RuntimeException("Error 404");
        }
    }
    private void readMovesFromFile() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("src/resource/inputRPS.txt"));
        String input;
        while((input = read.readLine())!=null){
            moves.add(input);
        }
    }
    private void computeScoreForMoves(){
        for (String s:moves) {
            int scoreToBeAdded = getPointsForYourPick(s.charAt(2),'X','Y');
            scoreToBeAdded += resultOfRound(scoreToBeAdded,s.charAt(0));
            score.add(scoreToBeAdded);
            System.out.println(scoreToBeAdded);
        }
    }

    private int resultOfRound(int yPick, char ePick) {
        int enemyPick = getPointsForYourPick(ePick,'A','B');
        if(yPick == enemyPick){
            return 3;
        } else{
            if (yPick==1) {
                if(enemyPick == 3){
                    return 6;
                }
            } else if (yPick ==2) {
                if(enemyPick == 1){
                    return 6;
                }
            } else {
                if(enemyPick == 2){
                    return 6;
                }
            }
        }
        return 0;
    }

    public int sumOfAllMoves(){
        int result =0;
        for (int i:score) {
            result += i;
        }
        return result;
    }

    private int getPointsForYourPick(char input,char rock,char paper) {
        if(input == rock){
            return 1;
        } else if (input == paper) {
            return 2;
        }else{
            return 3;
        }
    }

    public static void main(String[] args) {
        RockPaperScissor play = new RockPaperScissor();
        System.out.print(play.sumOfAllMoves());
    }
}
