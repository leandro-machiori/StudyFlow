package com.leandro.studyflow.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.MateriaController;
import com.leandro.studyflow.model.Materia;
import com.leandro.studyflow.util.Notificacao;

public class AddMateriaActivity extends AppCompatActivity {

    private EditText edtNome, edtDescricao;
    private Button btnSalvar;
    private MateriaController materiaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materia);

        edtNome = findViewById(R.id.edtNomeMateria);
        edtDescricao = findViewById(R.id.edtDescricaoMateria);
        btnSalvar = findViewById(R.id.btnSalvarMateria);

        materiaController = new MateriaController(this);

        btnSalvar.setOnClickListener(v -> salvarMateria());
    }

    private void salvarMateria() {
        String nome = edtNome.getText().toString().trim();
        String descricao = edtDescricao.getText().toString().trim();

        if (nome.isEmpty()) {
            Toast.makeText(this, "Digite o nome da matéria", Toast.LENGTH_SHORT).show();
            return;
        }

        Materia materia = new Materia();
        materia.setNome(nome);
        materia.setDescricao(descricao);

        long id = materiaController.adicionarMateria(materia);

        if (id > 0) {
            Toast.makeText(this, "Matéria adicionada!", Toast.LENGTH_SHORT).show();
            // Envia uma notificação
            Notificacao.enviar(this, "StudyFlow", "Nova matéria adicionada: " + nome);
            finish(); // volta para a tela anterior
        } else {
            Toast.makeText(this, "Erro ao adicionar matéria", Toast.LENGTH_SHORT).show();
        }
    }
}
