package com.leandro.studyflow.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.CronogramaController;
import com.leandro.studyflow.model.Cronograma;

public class CronogramaActivity extends AppCompatActivity {
    private EditText etMateriaId, etData, etHorario;
    private CronogramaController cronogramaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etMateriaId = findViewById(R.id.etMateriaId);
        etData = findViewById(R.id.etDataEstudo);
        etHorario = findViewById(R.id.etHorarioEstudo);
        Button btnSalvar = findViewById(R.id.btnSalvarCronograma);
        cronogramaController = new CronogramaController(this);
        btnSalvar.setOnClickListener(v -> {
            String data = etData.getText().toString();
            String horario = etHorario.getText().toString();
            int materiaId;
            try {
                materiaId = Integer.parseInt(etMateriaId.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "ID da matéria inválido", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.isEmpty() || horario.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }
            Cronograma cronograma = new Cronograma(0, materiaId, data, horario);
            cronogramaController.salvarCronograma(cronograma);
            Toast.makeText(this, "Cronograma salvo", Toast.LENGTH_SHORT).show();
        });
    }
}