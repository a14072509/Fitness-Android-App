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

    public String parseDate(String date) {
        String retStr = "";
        String[] dateArray = date.split(" ", 3);
        retStr = retStr.concat(dateArray[2] + "-");
        switch (dateArray[0]) {
            case "January":
                retStr = retStr.concat("01");
                break;
            case "February":
                retStr = retStr.concat("02");
                break;
            case "March":
                retStr = retStr.concat("03");
                break;
            case "April":
                retStr = retStr.concat("04");
                break;
            case "May":
                retStr = retStr.concat("05");
                break;
            case "June":
                retStr = retStr.concat("06");
                break;
            case "July":
                retStr = retStr.concat("07");
                break;
            case "August":
                retStr = retStr.concat("08");
                break;
            case "September":
                retStr = retStr.concat("09");
                break;
            case "October":
                retStr = retStr.concat("10");
                break;
            case "November":
                retStr = retStr.concat("11");
                break;
            case "December":
                retStr = retStr.concat("12");
                break;
        }
        retStr = retStr.concat("-" + (Integer.parseInt(dateArray[1]) > 9 ? dateArray[1] : "0" + dateArray[1]));
        return retStr;
    }
}