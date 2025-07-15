package com.leandro.studyflow.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leandro.studyflow.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnMaterias = findViewById(R.id.btnMaterias);
        Button btnCronograma = findViewById(R.id.btnCronograma);
        Button btnLembretes = findViewById(R.id.btnLembretes);
        Button btnProgresso = findViewById(R.id.btnProgresso);

        btnMaterias.setOnClickListener(v -> startActivity(new Intent(this, CadastroMateriaActivity.class)));
        btnCronograma.setOnClickListener(v -> startActivity(new Intent(this, CronogramaActivity.class)));
        btnLembretes.setOnClickListener(v -> startActivity(new Intent(this, LembretesActivity.class)));
        btnProgresso.setOnClickListener(v -> startActivity(new Intent(this, ProgressoActivity.class)));
    }
}