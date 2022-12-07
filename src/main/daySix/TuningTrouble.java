package main.daySix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TuningTrouble {

    private final String signal;
    public TuningTrouble() {
        try{
            BufferedReader read = new BufferedReader( new FileReader("src/resource/inputTuningTrouble.txt"));
            this.signal = read.readLine();
        }catch(IOException io){
            throw new RuntimeException("Error 404");
        }
    }

    public static void main(String[] args) {
        TuningTrouble tt = new TuningTrouble();
        System.out.println(tt.getLocOfFirstMarkerSlashMessage(4));
        System.out.println(tt.getLocOfFirstMarkerSlashMessage(14));
    }
    public int getLocOfFirstMarkerSlashMessage(int distinctCharacters) {
        char[] chars = new char[distinctCharacters];
        for(int o=0;o<chars.length;o++){
            chars[o] = signal.charAt(o);
        }
        for (int i=distinctCharacters;i<signal.length();i++) {
            if (isCharDoubleAssignedArray(chars)) {
                moveUp(chars, signal.charAt(i));
            } else if (i == 2390) {
                System.out.println("du hund");
            } else {
                return i;
            }
        }
        return -1;
    }
    private boolean isCharDoubleAssignedArray(char[] chars) {
        for (char c : chars) {
            if (countCharInArray(chars, c)>1) {
                return true;
            }
        }
        return false;
    }
    private void moveUp(char[] chars,char lastChar) {
        for (int i=0;i<chars.length-1;i++){
            chars[i] = chars[i+1];
        }
        chars[chars.length-1] = lastChar;
    }
    private int countCharInArray(char[] chars, char input) {
        int counter =0;
        for (char c:chars) {
            if (c == input){
                counter++;
            }
        }
        return counter;
    }
}
