package com.leandro.studyflow.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.MateriaController;
import com.leandro.studyflow.model.Materia;
import com.leandro.studyflow.util.Preferences;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMaterias;
    private Button btnAddMateria;
    private Button btnLogout;
    private MateriaController materiaController;
    private List<Materia> listaMaterias;
    private ArrayAdapter<Materia> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMaterias = findViewById(R.id.listMaterias);
        btnAddMateria = findViewById(R.id.btnAddMateria);
        btnLogout = findViewById(R.id.btnLogout); // Novo botão de sair

        materiaController = new MateriaController(this);

        // Carrega as matérias
        carregarMaterias();

        // Lógica do botão de adicionar matéria
        btnAddMateria.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddMateriaActivity.class);
            startActivity(intent);
        });

        // Lógica do novo botão de sair
        btnLogout.setOnClickListener(v -> {
            // Limpa o estado de login e volta para a tela de login
            Preferences.clearUsuario(this);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Lógica para o clique em um item da lista (para editar)
        listViewMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Materia materiaSelecionada = listaMaterias.get(position);
                Intent intent = new Intent(MainActivity.this, EditMateriaActivity.class);
                intent.putExtra("ID_MATERIA", materiaSelecionada.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recarrega a lista toda vez que a atividade é retomada (após adicionar/editar/excluir)
        carregarMaterias();
    }

    private void carregarMaterias() {
        listaMaterias = materiaController.listarMaterias();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMaterias);
        listViewMaterias.setAdapter(adapter);
    }
}
