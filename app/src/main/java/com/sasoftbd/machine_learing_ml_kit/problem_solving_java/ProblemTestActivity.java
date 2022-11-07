package com.sasoftbd.machine_learing_ml_kit.problem_solving_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class ProblemTestActivity extends AppCompatActivity {

    ArrayList<com.sasoftbd.machine_learing_ml_kit.problem_solving_java.modelData> model = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO code application logic here

        String id[] = {"1", "1", "3", "4"};
        String name[] = {"jamal", "jamal", "rahim", "karim"};
        addValues(id, name);



        //1 Rotate Array in Java
//        int array[] = {1,2,3,4,5,6,7};
//        int k = 3;
//
//        String array2[] = {"a", "b", "c", "d", "s"};
//
//
//
//        for(int i = 0; i < model.size(); i++){
//            System.out.println("Hello World"+model.get(i));
//        }


        //rotateArrayJava(array, k);


        //int array[]= {1,34,5,7,5};
        //rotateWithBubble(array, 3);

//        for(int o = 0; o < array.length; o++){
//            System.out.println(""+array[o]);
//        }


//        JavaProblems javaProblems = new JavaProblems();
//        javaProblems.rotateArrayJava(array, k);


    }

    private void printdata(ArrayList<modelData> model) {

            for (int j = 0; j < model.size(); j++) {
                System.out.println("id=" + this.model.get(j).getId() + " name=" + this.model.get(j).getName() + "\n");
            }
            remove(model);
            AfterDeleted(model);
    }

    private void AfterDeleted(ArrayList<modelData> model) {
        for (int j = 0; j < model.size(); j++) {
            System.out.println("\n After Delete\n" + "id=" + this.model.get(j).getId() + " name=" + this.model.get(j).getName() + "\n");
        }

    }

    private void remove(ArrayList<modelData> model) {
        for (int k = 0; k < model.size(); k++) {
            if (model.get(k).getName() == "jamal") {
                model.remove(k);

            }

        }

        //printdata(model);
    }

    private void addValues(String[] id, String[] name) {

        for (int i = 0; i < 4; i++) {
            model.add(new modelData(id[i], name[i]));
        }

        //printdata(model);
    }


    /**
     * Bubble Rotate
     */

    public static void BubbleRoted(int[] arr, int order){
        if(arr == null || order < 0){
            throw new IllegalArgumentException("Illegal Arguments");
        }
        for(int i = 0; i < order; i++){
            for (int j = arr.length-1; j > 0;j--){
                int temp = arr[i];
                arr[j] = arr[j-1];
                arr[j - 1] = temp;
            }
        }
    }


}