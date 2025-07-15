package com.leandro.studyflow.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    // Formata data para padrão dd/MM/yyyy
    public static String formatarData(String dataOriginal) {
        try {
            SimpleDateFormat sdfOriginal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat sdfDestino = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date data = sdfOriginal.parse(dataOriginal);
            return sdfDestino.format(data);
        } catch (ParseException e) {
            return dataOriginal;
        }
    }
    // Retorna data atual (yyyy-MM-dd)
    public static String getDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
    // Retorna hora atual (HH:mm)
    public static String getHoraAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }
}