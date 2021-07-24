package com.example.rx_java;

import java.util.ArrayList;

public class ArrayActivity {

    public static ArrayList<TaskData> array() {
        ArrayList<TaskData> tasks = new ArrayList<>();
        tasks.add(new TaskData("geecoders",true,2));
        tasks.add(new TaskData("the web developer",false,5));
        tasks.add(new TaskData("the android developer",false,1));
        tasks.add(new TaskData("let's go to learn new programing language",true,3));
        tasks.add(new TaskData("geecoders is the best website to learn programing language by simple",false,4));
        return tasks;
    }

}
