package com.leandro.studyflow.util;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.leandro.studyflow.R;

public class Notificacao {
    @SuppressLint("NotificationPermission")
    public static void enviar(Context ctx, String titulo, String msg) {
        NotificationManager nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        String canalId = "studyflow_canal";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(canalId, "StudyFlow", NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(canal);
        }

        Notification n = new NotificationCompat.Builder(ctx, canalId)
                .setContentTitle(titulo)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Use um ícone adequado
                .build();

        nm.notify(1, n);
    }
}