package com.example.final_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MediaPlayer welcomeEn;
    MediaPlayer welcomeFr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Using Block 2
        // Display language in either English or French using strings.xml
        setContentView(R.layout.activity_main);

        // Using Block 4
        // Play welcome message clip according to locale language selected
        welcomeEn = MediaPlayer.create(this, R.raw.en_speech);
        welcomeFr = MediaPlayer.create(this, R.raw.fr_speech);
        String locale =  Locale.getDefault().getLanguage();
        if (locale == "fr")
            welcomeFr.start();
        else
            welcomeEn.start();

        // Using Block 1
        // Convert between miles and kilometer on button press
        Button buttonConMileToKm = (Button) findViewById(R.id.button_MilesToKm);
        buttonConMileToKm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText textBoxMile = (EditText) findViewById(R.id.editText_miles);
                EditText textBoxKm = (EditText) findViewById(R.id.editText_km);
                double vMile = Double.valueOf(textBoxMile.getText().toString());
                double vKm = vMile / 0.62137;
                DecimalFormat vFormat = new DecimalFormat("##.##");
                textBoxKm.setText(vFormat.format(vKm));
            }
        });
        Button buttonConKmToMile = (Button) findViewById(R.id.button_KmToMiles);
        buttonConKmToMile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText textBoxMile = (EditText) findViewById(R.id.editText_miles);
                EditText textBoxKm = (EditText) findViewById(R.id.editText_km);
                double vKm = Double.valueOf(textBoxKm.getText().toString());
                double vMile = vKm * 0.62137;
                DecimalFormat vFormat = new DecimalFormat("##.##");
                textBoxMile.setText(vFormat.format(vMile));
            }
        });
    }

    // Using Block 9
    // Send conversion result to SMS message
    public void sendMessage (View v){
        // retrieve the results and compose message
        EditText textBoxMile = (EditText) findViewById(R.id.editText_miles);
        EditText textBoxKm = (EditText) findViewById(R.id.editText_km);
        String mileString = textBoxMile.getText().toString();
        String kmString = textBoxKm.getText().toString();
        String message = mileString + " miles is equals to " + kmString + " km!";
        Uri destination = Uri.parse("smsto:5556");
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, destination);
        // pass the composed message to the messaging activity
        smsIntent.putExtra("sms_body", message);
        // launch the intent
        startActivity(smsIntent);
    }
}
