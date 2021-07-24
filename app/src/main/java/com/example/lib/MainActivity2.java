package com.example.lib;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.threadactivity.R;
import com.hbb20.CountryCodePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.yariksoffice.lingver.Lingver;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity {
    Button ar, en;
    LottieAnimationView animationView;
    boolean swich = false;
    CountryCodePicker countryCodePicker;
    String number = "123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    private void language() {
        ar = findViewById(R.id.btn_ar);
        en = findViewById(R.id.btn_en);

        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lingver.getInstance().setLocale(MainActivity2.this, "ar");
                recreate();
            }
        });

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lingver.getInstance().setLocale(MainActivity2.this, "en");
                recreate();
            }
        });
    }

    private void toasty() {

        Toasty.error(this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
        Toasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show();
        Toasty.info(this, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
        Toasty.warning(this, "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
        Toasty.normal(this, "Normal toast w/o icon").show();
        Toasty.normal(this, "Normal toast w/ icon", R.drawable.flag_egypt).show();

        // custom
        Toasty.custom(this, "I'm a custom Toast", R.drawable.flag_egypt, R.color.design_default_color_on_primary, 1, true,
                true).show();


    }

    private void Dexter() {
        // يجب عمل برميشن في المني فيست لامكانية الوصول
        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {

                    // في حالة قبول الوصول
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                    }

                    // في حالة رفض الوصول
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        finish();
                        Toast.makeText(MainActivity2.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    }
                }).check();

        // Multiple permissions
//        Dexter.withContext(this)
//                .withPermissions(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.RECORD_AUDIO
//                ).withListener(new MultiplePermissionsListener() {
//            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
//            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
//        }).check();


    }

    private void loti_json() {

        animationView=findViewById(R.id.json);
        animationView.setOnClickListener(v -> {

            // start
            if (swich) {
                animationView.setMinAndMaxProgress(0.35f, 1.0f);
                animationView.playAnimation();
                swich = false;
            }

            // end
            else {
                animationView.setMinAndMaxProgress(0.0f , 0.35f);
                animationView.playAnimation();
                swich = true;
            }
        });
    }

    private void Country_code () {
        countryCodePicker = findViewById(R.id.country);
        String phone = countryCodePicker.getSelectedCountryCode() + number;
        Toast.makeText(this, "the prine is : "+phone, Toast.LENGTH_SHORT).show();
    }

}