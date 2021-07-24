package com.example.lib.language;

import android.content.Context;
import android.content.SharedPreferences;

public class MyShared {

    SharedPreferences sharedPreferences;

    public MyShared() {
        sharedPreferences = AppClass.context.getSharedPreferences("userData", Context.MODE_PRIVATE);
    }

    public void setLanguage (String language) {
        sharedPreferences.edit().putString("language",language).apply();
    }

    public String getLanguage() {
        return sharedPreferences.getString("language","default");
    }

}
