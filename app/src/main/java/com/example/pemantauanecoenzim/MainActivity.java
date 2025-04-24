package com.example.pemantauanecoenzim;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textTemp, textHumidity;
    private Switch switchHot, switchCool;
    private Button btnNotifikasi;

    private final Handler handler = new Handler();
    private final Random random = new Random();

    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            double suhu = 30 + (10 * random.nextDouble()); // 30.0 - 40.0
            int kelembapan = 40 + random.nextInt(21);      // 40 - 60

            textTemp.setText(String.format(Locale.getDefault(), "%.1f°C", suhu));
            textHumidity.setText(String.format(Locale.getDefault(), "%d%%", kelembapan));

            handler.postDelayed(this, 60000); // update setiap 60 detik
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Event: Tombol Notifikasi
        btnNotifikasi.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Notifikasi menyala!", Toast.LENGTH_SHORT).show()
        );

        // Event: Switch HOT
        switchHot.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "HOT diaktifkan" : "HOT dimatikan";
            Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
        });

        // Event: Switch COOL
        switchCool.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "COOL diaktifkan" : "COOL dimatikan";
            Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
        });

        // Mulai update suhu dan kelembapan secara periodik
        handler.post(updateRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateRunnable); // Hentikan update ketika activity dihancurkan
    }
}
