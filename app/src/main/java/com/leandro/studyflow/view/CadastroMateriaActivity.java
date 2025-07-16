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
import com.leandro.studyflow.controller.MateriaController;
import com.leandro.studyflow.model.Materia;
import com.leandro.studyflow.utils.ValidationUtils;

public class CadastroMateriaActivity extends AppCompatActivity {
    private EditText etNome, etDescricao;
    private MateriaController materiaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_materia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNome = findViewById(R.id.etNomeMateria);
        etDescricao = findViewById(R.id.etDescricaoMateria);
        Button btnSalvar = findViewById(R.id.btnSalvarMateria);
        materiaController = new MateriaController(this);
        btnSalvar.setOnClickListener(v -> {
            String nome = etNome.getText().toString();
            String descricao = etDescricao.getText().toString();
            if (ValidationUtils.isCampoVazio(nome)) {
                Toast.makeText(this, "Informe o nome da matéria", Toast.LENGTH_SHORT).show();
            } else {
                Materia materia = new Materia(0, nome, descricao);
                materiaController.salvarMateria(materia);
                Toast.makeText(this, "Matéria salva com sucesso", Toast.LENGTH_SHORT).show();
                etNome.setText("");
                etDescricao.setText("");
            }
        });
    }
}
