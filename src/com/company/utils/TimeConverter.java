package com.company.utils;

public class TimeConverter {

    public static String convertAbilityTime(int milliseconds){
        if(milliseconds>1000){
            return (int)Math.ceil((milliseconds / 1000d)) + "s";
        }
        else if(milliseconds<1000 && milliseconds>0){
            return String.format("%.1f", milliseconds/1000d) + "s";
        }
        else if(milliseconds == 0){
            return "ready";
        }
        else{
            return "active";
        }
    }
}