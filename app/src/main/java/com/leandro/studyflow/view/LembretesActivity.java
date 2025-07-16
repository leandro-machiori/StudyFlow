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
import com.leandro.studyflow.controller.LembreteController;
import com.leandro.studyflow.model.Lembrete;

public class LembretesActivity extends AppCompatActivity {
    private EditText etTitulo, etMensagem, etDataHora;
    private LembreteController lembreteController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembretes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etTitulo = findViewById(R.id.etTituloLembrete);
        etMensagem = findViewById(R.id.etMensagemLembrete);
        etDataHora = findViewById(R.id.etDataHoraLembrete);
        Button btnSalvar = findViewById(R.id.btnSalvarLembrete);
        lembreteController = new LembreteController(this);
        btnSalvar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString();
            String mensagem = etMensagem.getText().toString();
            String dataHora = etDataHora.getText().toString();
            if (titulo.isEmpty() || mensagem.isEmpty() || dataHora.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }
            Lembrete lembrete = new Lembrete(0, titulo, mensagem, dataHora);
            lembreteController.salvarLembrete(lembrete);
            Toast.makeText(this, "Lembrete salvo", Toast.LENGTH_SHORT).show();
        });
    }
}