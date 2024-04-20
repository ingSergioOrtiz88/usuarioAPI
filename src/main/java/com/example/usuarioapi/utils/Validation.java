package com.example.usuarioapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {


    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[0-9a-zA-Z]+.[a-zA-Z]{2,4}+([.][a-zA-Z]{2,3})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password, String expresion) {

        Pattern pattern = Pattern.compile(expresion);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
