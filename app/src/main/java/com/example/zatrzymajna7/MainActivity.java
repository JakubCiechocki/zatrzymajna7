package com.example.zatrzymajna7;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView licznik;
    Button btnStart;
    Button btnStop;
    TextView txtKomunikat;
    TextView txtTrafienia;

    int aktualnaCyfra = 0;
    int trafienia = 0;

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            aktualnaCyfra = (aktualnaCyfra + 1) % 10;
            licznik.setText(String.valueOf(aktualnaCyfra));

            handler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        licznik = findViewById(R.id.licznik);
        txtKomunikat = findViewById(R.id.txtKomunikat);
        txtTrafienia = findViewById(R.id.txtTrafienia);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStop.setEnabled(false);

        btnStart.setOnClickListener(v -> {
            aktualnaCyfra = 0;
            licznik.setText("0");
            txtKomunikat.setText("");

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

            handler.postDelayed(runnable, 500);
        });

        btnStop.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);

            btnStart.setEnabled(true);
            btnStop.setEnabled(false);

            if (aktualnaCyfra == 7) {
                trafienia++;
                txtKomunikat.setText("Trafienie!");
                txtTrafienia.setText("Trafienia: " + trafienia);
            } else {
                txtKomunikat.setText(
                        "Pudło! Zatrzymano na " + aktualnaCyfra
                );
            }
        });
    }
}