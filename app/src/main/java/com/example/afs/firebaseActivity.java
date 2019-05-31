package com.example.afs;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class firebaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public ArrayList<Food> parseStrToFoodlist(String foodStr) {
        ArrayList<Food> parsedList = new ArrayList<Food>();
        int len = foodStr.length();
        foodStr = foodStr.substring(5, len - 1);
        System.out.println(foodStr);
        while(foodStr != "") {
            String[] nameTemp = foodStr.split("=", 2);
            //System.out.println("nameaaaa\n"+nameTemp[0]+"split"+nameTemp[1]);
            if(!nameTemp[1].contains("=")) {
                parsedList.add(new Food(nameTemp[0], Integer.parseInt(nameTemp[1])));
                return parsedList;
            }
            String[] caloTemp = nameTemp[1].split(", ", 2);
            //System.out.println("caloaaaa\n"+caloTemp[0]+"divide"+caloTemp[1]);
            parsedList.add(new Food(nameTemp[0], Integer.parseInt(caloTemp[0])));
            foodStr = caloTemp[1];
        }
        return parsedList;
    }

    public int calculateCalories(String foodStr) {
        int calories = 0;
        int len = foodStr.length();
        foodStr = foodStr.substring(5, len - 1);
        //System.out.println(foodStr);
        while(foodStr != "") {
            String[] nameTemp = foodStr.split("=", 2);
            //System.out.println("nameaaaa\n"+nameTemp[0]+"split"+nameTemp[1]);
            if(!nameTemp[1].contains("=")) {
                calories += Integer.parseInt(nameTemp[1]);
                return calories;
            }
            String[] caloTemp = nameTemp[1].split(", ", 2);
            //System.out.println("caloaaaa\n"+caloTemp[0]+"divide"+caloTemp[1]);
            calories += Integer.parseInt(caloTemp[0]);
            foodStr = caloTemp[1];
        }
        return calories;
    }


}