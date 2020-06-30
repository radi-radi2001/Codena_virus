package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        char[][] world = {{'#', '#', '#'}, {'#', '#', '#'}, {'#', '#', '#'}};
        int [] first_infected = {1,1};

        int[] result = codenavirus(world, first_infected);

        System.out.println();
        System.out.printf("[%d, %d, %d, %d]", result[0], result[1], result[2], result[3]);

    }

    public static int[] codenavirus(char[][] world, int[] firstInfected) {
        int[] result = new int[4];

        int rows = world.length;
        int cols = world[0].length;

        int days = 0;
        int infected = 0;
        int recovered = 0;
        int un_infected = rows * cols;

        int rowFirstInf = firstInfected[0];
        int colFirstInf = firstInfected[1];


        int[][] recoveryCounter = new int[rows][cols];
        for (int[] ints : recoveryCounter) {

            Arrays.fill(ints, -1);

        }


        world[rowFirstInf][colFirstInf] = 'I';
        recoveryCounter[rowFirstInf][colFirstInf] = 3;
        days++;
        infected++;
        un_infected--;

        while (true){

            boolean spread_end = true;

            List<int[]> infectedPositions = new ArrayList<>();


            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    recoveryCounter[row][col]--;
                    int current = recoveryCounter[row][col];


                    if (recoveryCounter[row][col] == 0){

                        recovered++;
                        infected--;
                        world[row][col] = 'R';

                    }


                    if ((current >= 1 & current <= 3)){

                        int[] array = {row, col};
                        infectedPositions.add(array);

                    }
                }
            }


            if (infectedPositions.isEmpty()){

                break;

            }


            for (int[] infectedPosition : infectedPositions) {

                int row = infectedPosition[0];
                int col = infectedPosition[1];

                if (col + 1 < cols){
                    if (world[row][col + 1] == '#') {

                        infect(world, recoveryCounter, row, col + 1);
                        infected++;
                        un_infected--;
                        spread_end = false;
                        continue;

                    }
                }


                if (row - 1 >= 0){
                    if (world[row - 1][col] == '#') {

                        infect(world, recoveryCounter, row - 1, col);
                        infected++;
                        un_infected--;
                        spread_end = false;
                        continue;

                    }


                }


                if (col - 1 >= 0){
                    if (world[row][col - 1] == '#'){

                        infect(world, recoveryCounter, row, col - 1);
                        infected++;
                        un_infected--;
                        spread_end = false;
                        continue;

                    }

                }


                if (row + 1 < cols){
                    if (world[row + 1][col] == '#'){

                        infect(world, recoveryCounter, row + 1, col);
                        infected++;
                        un_infected--;
                        spread_end = false;

                    }
                }
            }
            days++;


            System.out.printf("Day: %d\n", days);
            for (int i = 0; i < world.length; i++) {
                for (int j = 0; j < world[i].length; j++) {

                    System.out.print(world[i][j]);

                }

                System.out.println();

            }

            System.out.println();

            if (spread_end){

                break;

            }

        }

        result[0] = days;
        result[1] = infected;
        result[2] = recovered;
        result[3] = un_infected;

        return result;

    }

    private static void infect(char[][] world, int[][] counter_for_recovery, int row, int col) {
        world[row][col] = 'I';
        counter_for_recovery[row][col] = 3;

    }

}