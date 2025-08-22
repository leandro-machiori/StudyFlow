package com.leandro.studyflow.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.UsuarioController;
import com.leandro.studyflow.model.Usuario;
import com.leandro.studyflow.util.Preferences;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnLogin;
    private TextView tvCadastrar, tvEsqueceuSenha;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioController = new UsuarioController(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        tvCadastrar = findViewById(R.id.tvCadastrar);
        tvEsqueceuSenha = findViewById(R.id.tvEsqueceuSenha);

        // Verifica se já existe um usuário logado
        if (Preferences.getUsuario(this) != null) {
            iniciarMainActivity();
            return;
        }

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            } else {
                Usuario usuarioLogado = usuarioController.login(email, senha);
                if (usuarioLogado != null) {
                    Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                    // Salva o estado de login
                    Preferences.salvarUsuario(this, usuarioLogado.getEmail());
                    iniciarMainActivity();
                } else {
                    Toast.makeText(this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        tvEsqueceuSenha.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    private void iniciarMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finaliza LoginActivity para que o botão de voltar não retorne a ela
    }
}
