package com.example.pemantauanecoenzim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNama;
    Button buttonEdit, buttonSimpan;
    SharedPreferences sharedPreferences;
    String PREF_NAME = "profil_pref";
    String KEY_NAMA = "nama_pengguna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        editTextNama = findViewById(R.id.editText_nama);
        buttonEdit = findViewById(R.id.button_edit);
        buttonSimpan = findViewById(R.id.button_simpan);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Load nama yang disimpan sebelumnya
        String savedNama = sharedPreferences.getString(KEY_NAMA, "Nadia");
        editTextNama.setText(savedNama);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNama.setEnabled(true);
                editTextNama.requestFocus();
                buttonSimpan.setVisibility(View.VISIBLE);

                // Tampilkan keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextNama, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaBaru = editTextNama.getText().toString();

                // Simpan nama ke SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAMA, namaBaru);
                editor.apply();

                editTextNama.setEnabled(false);
                buttonSimpan.setVisibility(View.GONE);

                Toast.makeText(MainActivity.this, "Nama disimpan: " + namaBaru, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
