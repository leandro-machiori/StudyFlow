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
import com.leandro.studyflow.utils.ValidationUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etSenha;
    private UsuarioController usuarioController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioController = new UsuarioController(this);
        etEmail = findViewById(R.id.etEmailLogin);
        etSenha = findViewById(R.id.etSenhaLogin);
        Button btnEntrar = findViewById(R.id.btnEntrar);
        TextView tvIrParaCadastro = findViewById(R.id.tvIrParaCadastro);
        btnEntrar.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();
            if (!ValidationUtils.isEmailValido(email)) {
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show();
            } else if (ValidationUtils.isCampoVazio(senha)) {
                Toast.makeText(this, "Informe a senha", Toast.LENGTH_SHORT).show();
            } else if (usuarioController.validarLogin(email, senha)) {
                Toast.makeText(this, "Login realizado!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainMenuActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            }
        });
        tvIrParaCadastro.setOnClickListener(v -> {
            startActivity(new Intent(this, CadastroUsuarioActivity.class));
        });
    }
}