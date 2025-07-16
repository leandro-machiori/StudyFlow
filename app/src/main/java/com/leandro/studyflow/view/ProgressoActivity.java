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
import com.leandro.studyflow.controller.ProgressoController;
import com.leandro.studyflow.model.Progresso;

public class ProgressoActivity extends AppCompatActivity {
    private EditText etMateriaId, etConteudo, etStatus;
    private ProgressoController progressoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etMateriaId = findViewById(R.id.etMateriaIdProgresso);
        etConteudo = findViewById(R.id.etConteudoProgresso);
        etStatus = findViewById(R.id.etStatusProgresso);
        Button btnSalvar = findViewById(R.id.btnSalvarProgresso);
        progressoController = new ProgressoController(this);
        btnSalvar.setOnClickListener(v -> {
            String conteudo = etConteudo.getText().toString();
            String status = etStatus.getText().toString();
            int materiaId;
            try {
                materiaId = Integer.parseInt(etMateriaId.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "ID da matéria inválido", Toast.LENGTH_SHORT).show();
                return;
            }
            if (conteudo.isEmpty() || status.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }
            Progresso progresso = new Progresso(0, materiaId, conteudo, status);
            progressoController.salvarProgresso(progresso);
            Toast.makeText(this, "Progresso salvo", Toast.LENGTH_SHORT).show();
        });
    }
}