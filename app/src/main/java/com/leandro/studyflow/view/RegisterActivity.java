package com.leandro.studyflow.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.UsuarioController;
import com.leandro.studyflow.model.Usuario;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtNome, edtEmail, edtSenha;
    private Button btnCadastrar, btnFazerLogin; // Adicionando a variável para o botão de fazer login
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnFazerLogin = findViewById(R.id.btnFazerLogin); // Inicializando o botão de fazer login

        usuarioController = new UsuarioController(this);

        btnCadastrar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(nome);
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(senha);

            long resultado = usuarioController.cadastrar(novoUsuario);

            if (resultado > 0) {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else if (resultado == -1) {
                Toast.makeText(this, "Email já cadastrado.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao realizar cadastro.", Toast.LENGTH_SHORT).show();
            }
        });

        // Adicionando a lógica para o botão de fazer login
        btnFazerLogin.setOnClickListener(v -> {
            finish(); // Fecha a Activity de registro e volta para a anterior (Login)
        });
    }
}
