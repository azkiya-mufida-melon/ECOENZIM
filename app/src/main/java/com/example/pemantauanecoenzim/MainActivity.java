package com.example.pemantauancoenzim;


import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textTemp, textHumidity;
    Switch switchHot, switchCool;
    Button btnNotifikasi;

    Handler handler = new Handler();
    Random random = new Random();

    Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            double suhu = 30 + (10 * random.nextDouble()); // suhu antara 30-40
            int kelembapan = 40 + random.nextInt(20);      // kelembapan 40-60

            textTemp.setText(String.format("%.1fC", suhu));
            textHumidity.setText(String.format("%dC", kelembapan));

            handler.postDelayed(this, 60000); // update tiap 60 detik
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTemp = findViewById(R.id.textTemp);
        textHumidity = findViewById(R.id.textHumidity);
        switchHot = findViewById(R.id.switchHot);
        switchCool = findViewById(R.id.switchCool);
        btnNotifikasi = findViewById(R.id.btnNotifikasi);

        // Tombol notifikasi
        btnNotifikasi.setOnClickListener(v ->
                Toast.makeText(this, "Notifikasi menyala!", Toast.LENGTH_SHORT).show()
        );

        // Switch HOT
        switchHot.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "HOT diaktifkan" : "HOT dimatikan", Toast.LENGTH_SHORT).show()
        );

        // Switch COOL
        switchCool.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "COOL diaktifkan" : "COOL dimatikan", Toast.LENGTH_SHORT).show()
        );

        // Mulai update suhu/kelembapan tiap menit
        handler.post(updateRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateRunnable);
    }
}
