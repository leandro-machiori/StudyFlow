package com.leandro.studyflow.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leandro.studyflow.R;
import com.leandro.studyflow.controller.UsuarioController;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtEmailForgot;
    private Button btnRecuperar;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtEmailForgot = findViewById(R.id.edtEmailForgot);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        usuarioController = new UsuarioController(this);

        btnRecuperar.setOnClickListener(v -> {
            String email = edtEmailForgot.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, digite seu e-mail.", Toast.LENGTH_SHORT).show();
            } else if (usuarioController.getUsuarioPorEmail(email) != null) {
                // Se o email existe, simula o envio de token e vai para a tela de reset
                Toast.makeText(this, "E-mail de recuperação enviado.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ResetPasswordActivity.class);
                intent.putExtra("EMAIL_RECUPERACAO", email);
                startActivity(intent);
            } else {
                Toast.makeText(this, "E-mail não encontrado.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}