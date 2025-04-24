package com.example.pemantauanecoenzim;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView namaText;
    private EditText editNama;
    private Button btnSimpan;
    private ImageView iconEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        namaText = findViewById(R.id.nama);
        editNama = findViewById(R.id.editNama);
        btnSimpan = findViewById(R.id.btnSimpanNama);
        iconEdit = findViewById(R.id.iconEdit);

        iconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNama.setVisibility(View.VISIBLE);
                btnSimpan.setVisibility(View.VISIBLE);
                editNama.setText(namaText.getText().toString().replace("Nama: ", ""));
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editNama.getText().toString().trim();
                if (!newName.isEmpty()) {
                    namaText.setText("Nama: " + newName);
                    editNama.setVisibility(View.GONE);
                    btnSimpan.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, "Nama berhasil diubah", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
