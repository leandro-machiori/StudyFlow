package com.leandro.studyflow.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.UsuarioController;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText edtNewPassword, edtConfirmNewPassword;
    private Button btnSaveNewPassword;
    private UsuarioController usuarioController;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = findViewById(R.id.edtConfirmNewPassword);
        btnSaveNewPassword = findViewById(R.id.btnSaveNewPassword);
        usuarioController = new UsuarioController(this);

        // Recebe o email da tela anterior
        userEmail = getIntent().getStringExtra("EMAIL_RECUPERACAO");

        btnSaveNewPassword.setOnClickListener(v -> saveNewPassword());
    }

    private void saveNewPassword() {
        String newPassword = edtNewPassword.getText().toString().trim();
        String confirmPassword = edtConfirmNewPassword.getText().toString().trim();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha a nova senha e a confirmação.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = usuarioController.atualizarSenha(userEmail, newPassword);

        if (success) {
            Toast.makeText(this, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show();
            // Volta para a tela de login
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar a senha.", Toast.LENGTH_SHORT).show();
        }
    }
}