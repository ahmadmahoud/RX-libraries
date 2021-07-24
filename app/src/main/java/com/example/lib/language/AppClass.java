package com.example.lib.language;

import android.app.Application;
import android.content.Context;

import com.yariksoffice.lingver.Lingver;

public class AppClass extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Lingver.init(this, new MyShared().getLanguage());

        // or
//         Lingver.init(this,"ar");

    }
}
