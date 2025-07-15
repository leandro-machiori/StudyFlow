package com.leandro.studyflow.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.UsuarioController;
import com.leandro.studyflow.model.Usuario;
import com.leandro.studyflow.utils.ValidationUtils;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private EditText etNome, etEmail, etSenha;
    private UsuarioController usuarioController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioController = new UsuarioController(this);
        etNome = findViewById(R.id.etNomeCadastro);
        etEmail = findViewById(R.id.etEmailCadastro);
        etSenha = findViewById(R.id.etSenhaCadastro);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        TextView tvVoltarLogin = findViewById(R.id.tvVoltarLogin);
        btnCadastrar.setOnClickListener(v -> {
            String nome = etNome.getText().toString();
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();
            if (ValidationUtils.isCampoVazio(nome)) {
                Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show();
            } else if (!ValidationUtils.isEmailValido(email)) {
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show();
            } else if (!ValidationUtils.isSenhaValida(senha)) {
                Toast.makeText(this, "Senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
            } else {
                Usuario usuario = new Usuario(0, nome, email, senha);
                boolean cadastrado = usuarioController.cadastrarUsuario(usuario);
                if (cadastrado) {
                    Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvVoltarLogin.setOnClickListener(v -> {
            finish();
        });
    }
}