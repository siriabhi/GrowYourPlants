package com.example.growyourplants;

import java.util.ArrayList;

public class Steps_Data {
    public ArrayList<String> steps_chilli =new ArrayList<>();

    public Steps_Data(){
        steps_chilli.add("file:///android_asset/c1.html");
        steps_chilli.add("file:///android_asset/c2.html");
        steps_chilli.add("file:///android_asset/c3.html");
    }

    public ArrayList<String> getSteps_chilli() {
        return steps_chilli;
    }
}
