import sun.awt.image.ImageWatched;

import java.util.LinkedList;

//Homework 2 Subirach Part
//Author: Krishna Garg
//Subirachs square is interesting because its corners, center, horizontal lines, vertical lines, and diagonals all add up to 33
import java.util.*;
class globalVars{
    public static LinkedList<Integer> square = new LinkedList<>();
    public static LinkedList<LinkedList<Integer>> all_subsets = new LinkedList<>();

}

public class Main {
    public static void main(String[] args) {
        //create the boards
        globalVars.square.add(1);
        globalVars.square.add(14);
        globalVars.square.add(14);
        globalVars.square.add(4);
        globalVars.square.add(11);
        globalVars.square.add(7);
        globalVars.square.add(6);
        globalVars.square.add(9);
        globalVars.square.add(8);
        globalVars.square.add(10);
        globalVars.square.add(10);
        globalVars.square.add(5);
        globalVars.square.add(13);
        globalVars.square.add(2);
        globalVars.square.add(3);
        globalVars.square.add(15);

        create_subsets(globalVars.square);
        //count all four combinations that add up to 33
        int num_four_combos = 0;
        for(LinkedList<Integer> i : globalVars.all_subsets){
            if (i.size() == 4 && sum_list(i) == 33) {
                num_four_combos += 1;
            }
        }
        System.out.println("Number of four combos that add up to 33 = " + num_four_combos + " combos");

        //count all combos that add up to 33
        int num_combos_sum = 0;
        for(LinkedList<Integer> i : globalVars.all_subsets){
            if (sum_list(i) == 33) {
                num_combos_sum += 1;
            }
        }
        System.out.println("Number of combos that add up to 33 = " + num_combos_sum + " combos");

        //how many ways every possible sum can be formed
        int total_sum = sum_list(globalVars.square);
        LinkedList<Integer> sums = new LinkedList<>();
        LinkedList<Integer> sorted_sums = new LinkedList<>();
        //System.out.println(total_sum);
        for(int i = 0; i <= total_sum; i++){
            int this_sum_total = 0;
            for(LinkedList<Integer> j : globalVars.all_subsets){
                if(sum_list(j) == i){
                    this_sum_total += 1;
                }
            }
            sums.add(this_sum_total); //the index will be the sum that you were looking for
            sorted_sums.add(this_sum_total);
            System.out.println("There are " + this_sum_total + " combos for getting the sum of " + i);
        }

        sorted_sums.sort(Comparator.naturalOrder());
        int greatest_num_sums = sorted_sums.getLast();
        int sum_index = sums.indexOf(greatest_num_sums);
        System.out.println("\n" + sum_index + " can be created with the greatest number of combos at " + greatest_num_sums + " combos");
    }

    //This function will create a linked list of all the subsets of the Subiarchs square
    //Input = Linked List of Integers
    //Output = Linked List of every subset

    static void create_subsets(LinkedList<Integer> input){
        for(int i = 0; i < Math.pow(2, input.size()); i++){
            LinkedList<Integer> subset_to_add = new LinkedList<>();
            //get the binary representation
            LinkedList<Integer> bin = get_rev_binary(i, input.size());
            //find all the on bits and add the corresponding values to subset_to_add
            for(int j = 0; j < bin.size(); j++){
                if(bin.get(j) == 1){
                    subset_to_add.add(input.get(j));
                }
            }
            globalVars.all_subsets.add(subset_to_add);
        }//end of big for
    }//end of create_subsets

    //Gets the binary representation of a given number
    static LinkedList<Integer> get_rev_binary(int num, int size){
        LinkedList<Integer> binary = new LinkedList<>();
        int holder = num;
        int bit;
        for(int i = 0; i < size; i++){
            bit = holder % 2;
            holder = holder/2;
            binary.addLast(bit);
        }
        return binary;
    }

    static int sum_list(LinkedList<Integer> input){
        int sum = 0;
        for(Integer i : input){
            sum += i;
        }
        return sum;
    }

}