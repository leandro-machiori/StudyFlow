package com.leandro.studyflow.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.MateriaController;
import com.leandro.studyflow.model.Materia;

public class EditMateriaActivity extends AppCompatActivity {

    private EditText edtNome, edtDescricao;
    private Button btnSalvar, btnExcluir, btnBack; // Adicionando a variável para o botão de voltar
    private MateriaController materiaController;
    private int materiaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_materia);

        edtNome = findViewById(R.id.edtNomeMateria);
        edtDescricao = findViewById(R.id.edtDescricaoMateria);
        btnSalvar = findViewById(R.id.btnSalvarMateria);
        btnExcluir = findViewById(R.id.btnExcluirMateria);
        btnBack = findViewById(R.id.btnBack); // Inicializando o botão de voltar

        materiaController = new MateriaController(this);

        materiaId = getIntent().getIntExtra("ID_MATERIA", -1);

        if (materiaId != -1) {
            carregarMateria();
        } else {
            Toast.makeText(this, "Erro: Matéria não encontrada.", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnSalvar.setOnClickListener(v -> salvarEdicao());
        btnExcluir.setOnClickListener(v -> confirmarExclusao());

        // Adicionando a lógica para o botão de voltar
        btnBack.setOnClickListener(v -> {
            finish(); // Fecha a Activity atual e retorna para a anterior
        });
    }

    private void carregarMateria() {
        Materia materia = materiaController.buscarMateriaPorId(materiaId);
        if (materia != null) {
            edtNome.setText(materia.getNome());
            edtDescricao.setText(materia.getDescricao());
        } else {
            Toast.makeText(this, "Erro ao carregar matéria.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void salvarEdicao() {
        String nome = edtNome.getText().toString().trim();
        String descricao = edtDescricao.getText().toString().trim();

        if (nome.isEmpty()) {
            Toast.makeText(this, "O nome da matéria não pode estar vazio.", Toast.LENGTH_SHORT).show();
            return;
        }

        Materia materia = new Materia(materiaId, nome, descricao);
        int resultado = materiaController.atualizarMateria(materia);

        if (resultado > 0) {
            Toast.makeText(this, "Matéria atualizada!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar matéria.", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmarExclusao() {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Matéria")
                .setMessage("Tem certeza que deseja excluir esta matéria?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    materiaController.excluirMateria(materiaId);
                    Toast.makeText(this, "Matéria excluída com sucesso.", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}
