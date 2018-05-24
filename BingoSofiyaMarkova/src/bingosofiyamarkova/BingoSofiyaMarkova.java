/*
Sofiya Markova
Bingo
March 26, 2018
Bingo game application that generates a bingo card, with all diffrent numbers in certain ranges. gnerates ranodm numbers from 1 to 75 and fills up card until get a win: row, column or diagonal. then prompts to play again
 */
package bingosofiyamarkova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Random;

public class BingoSofiyaMarkova {

    static InputStreamReader inStream = new InputStreamReader(System.in);
    static BufferedReader stdin = new BufferedReader(inStream);
    public static boolean win = false;

    public static void main(String[] args) throws IOException {

        int bingo[][] = new int[5][5];                                          //declare and allocate space of bingo card array

        int flag = 1;

        do {

            populateCard(bingo);                                                //filling bingo card with numbers

            displayBingoCard(bingo);                                            //calling to display bingo card

            lookingForDrawnNumber(bingo);

            System.out.println("would you like to continue? Yes = 1");
            flag = Integer.parseInt(stdin.readLine());
            win = false;

        } while (flag == 1);

    }

    public static int generateNumForAtIndexNum(int high) {
        Random rand = new Random();

        int result = rand.nextInt(high) + 0;

        return (result);

    }

    public static ArrayList<Integer> createArrayList(int start, int end) {      //method to create list, passed start and end values since diffrent for each column

        ArrayList<Integer> list = new ArrayList<Integer>();                     //declare and allocate space of ArrayList "list"

        for (int i = start; i <= end; i++) {                                    //creates num strat to end (inclusive), b/c values of each column can be start to end

            list.add(i);

        }

        return (list);
    }

    public static void displayBingoCard(int[][] bingo) {
        System.out.println("B I N G O");
        for (int i = 0; i < bingo.length; i++) {

            for (int j = 0; j < bingo[i].length; j++) {
                System.out.print(bingo[i][j] + " ");
            }

            System.out.println("");
        }

    }

    public static void populateCard(int[][] bingo) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();

        for (int j = 0; j < bingo.length; j++) {

            int start = 1 + (15 * j), end = 15 * (j + 1);                       //start and end for ArrayList method. need to have it be able to change
            list1 = createArrayList(start, end);

            for (int i = 0; i < bingo.length; i++) {
                int index = generateNumForAtIndexNum(14 - i);                   //so can decrement between what number to generate as the ArrayList "list" shrinks                                   
                bingo[i][j] = list1.get(index);                                 //sets number at index of array to bingo element

                list1.remove(index);

            }

        }

    }

    public static int generateNumForCheck(int k) {                              //generate index for card check

        Random rand = new Random();

        int index = rand.nextInt(74 - k) + 0;

        return (index);

    }

    public static ArrayList<Integer> createArrayListForCheck() {                

        ArrayList<Integer> draw = new ArrayList<Integer>();                     //declare and allocate space of ArrayList "list"

        for (int i = 1; i <= 75; i++) {                                         //creates num strat to end (inclusive), b/c values of each column can be start to end

            draw.add(i);

        }

        return (draw);
    }

    public static void lookingForDrawnNumber(int[][] bingo) {                   //method to draw numbers and check win types

        int k = 0;
        ArrayList<Integer> listCheck = new ArrayList<Integer>();

        listCheck = createArrayListForCheck();

        do {

            int index = generateNumForCheck(k);
            int drawn = listCheck.get(index);

            for (int i = 0; i < bingo.length; i++) {

                for (int j = 0; j < bingo[i].length; j++) {
                    if (bingo[i][j] == drawn) {
                        bingo[i][j] = 0;

                    }

                }

            }
            checkWinRow(bingo);
            checkWinColumn(bingo);
            checkWinDiagnol(bingo);

            listCheck.remove(index);

            k = k + 1;
        } while (win == false && k < 75);                                       
        displayBingoCard(bingo);
    }

    public static void checkWinRow(int[][] bingo) {                             //if win row= all 0 in row= therefroe sum of all elements = 0 

        for (int i = 0; i < bingo.length; i++) {

            if ((bingo[i][0] + bingo[i][1]) + bingo[i][2] + bingo[i][3] + bingo[i][4] == 0) {
                System.out.println("win at row " + (i + 1) + "!");

                win = true;                                                     //win is true is sum of all elements is 0
            }

        }

        
    }

    public static void checkWinColumn(int[][] bingo) {                          //to win column all numbers in column need to add to 0 (since was relplaced by 0 when present)

        for (int i = 0; i < bingo.length; i++) {

            if ((bingo[0][i] + bingo[1][i]) + bingo[2][i] + bingo[3][i] + bingo[4][i] == 0) {
                System.out.println("win at column " + (i + 1) + "!");
                win = true;


            }

        }

    }

    public static void checkWinDiagnol(int[][] bingo) {                         //diagonal win check 

        if ((bingo[0][0] + bingo[1][1]) + bingo[2][2] + bingo[3][3] + bingo[4][4] == 0) {
            System.out.println("win at \\ diagonal !");
            win = true;
        }

        if ((bingo[4][0] + bingo[3][1]) + bingo[2][2] + bingo[1][3] + bingo[0][4] == 0) {
            System.out.println("win at / diagonal !");
            win = true;
        }

    }

}
