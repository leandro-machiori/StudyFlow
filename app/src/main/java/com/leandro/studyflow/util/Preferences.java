package com.leandro.studyflow.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String PREF_NAME = "studyflow_prefs";

    /**
     * Salva o email do usuário logado.
     *
     * @param ctx   O contexto da aplicação.
     * @param email O email do usuário a ser salvo.
     */
    public static void salvarUsuario(Context ctx, String email) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString("usuario", email).apply();
    }

    /**
     * Retorna o email do usuário logado, se existir.
     *
     * @param ctx O contexto da aplicação.
     * @return O email do usuário ou null se não houver usuário logado.
     */
    public static String getUsuario(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString("usuario", null);
    }

    /**
     * Limpa o estado de login.
     *
     * @param ctx O contexto da aplicação.
     */
    public static void clearUsuario(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().remove("usuario").apply();
    }
}