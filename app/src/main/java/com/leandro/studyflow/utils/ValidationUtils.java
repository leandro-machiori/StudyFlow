package com.leandro.studyflow.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationUtils {

    // Verifica se um campo está vazio
    public static boolean isCampoVazio(String campo) {
        return TextUtils.isEmpty(campo) || campo.trim().isEmpty();
    }
    // Verifica se o e-mail é válido
    public static boolean isEmailValido(String email) {
        return !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    // Verifica se a senha é forte (exemplo simples: 6+ caracteres)
    public static boolean isSenhaValida(String senha) {
        return !isCampoVazio(senha) && senha.length() >= 6;
    }
}
