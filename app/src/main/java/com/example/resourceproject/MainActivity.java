package com.example.resourceproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = newBase.getSharedPreferences("settings", MODE_PRIVATE);
        //Locales
        String localeTag =preferences.getString("locale", Locale.ENGLISH.toLanguageTag());
        Locale locale = Locale.forLanguageTag(localeTag);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        Context context = newBase.createConfigurationContext(configuration);
        //
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        // Orientations
        setRequestedOrientation(
                preferences.getInt("orientation", ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));
        //Locales
        if (getSupportActionBar()!=null)
            getSupportActionBar().setTitle(R.string.app_name);
/*        // Themes
         setTheme(com.google.android.material.R.style.Theme_Material3_Light_NoActionBar);
        //*/
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        final int itemId = item.getItemId();

        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //

        if (itemId == R.id.unspecifiedMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
        if (itemId == R.id.sensorMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_SENSOR).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
        if (itemId == R.id.sensorPortraitMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
        if (itemId == R.id.sensorLandscapeMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        if (itemId == R.id.fullSensorMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }
        if (itemId == R.id.fullUserMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_FULL_USER).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
        }
        if (itemId == R.id.portraitMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_PORTRAIT).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else if (itemId == R.id.landscapeMenu){
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else if (itemId == R.id.lockedMenu) {
            editor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_LOCKED).apply();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }
        //
        else if (itemId == R.id.defaultLocaleMenu) {
            editor.putString("locale", Locale.ENGLISH.toLanguageTag()).apply();
            recreate();
        }
        else if (itemId == R.id.enGBLocaleMenu) {
            editor.putString("locale", Locale.UK.toLanguageTag()).apply();
            recreate();

        }
        else if (itemId == R.id.ukLocaleMenu) {
            editor.putString("locale", Locale.forLanguageTag("uk").toLanguageTag()).apply();
            recreate();

        }
        else if (itemId == R.id.ruLocaleMenu) {
            editor.putString("locale", Locale.forLanguageTag("ru").toLanguageTag()).apply();
            recreate();

        }
        else if (itemId == R.id.deLocaleMenu) {
            editor.putString("locale", Locale.forLanguageTag("de").toLanguageTag()).apply();
            recreate();

        }
        return super.onOptionsItemSelected(item);
    }
}