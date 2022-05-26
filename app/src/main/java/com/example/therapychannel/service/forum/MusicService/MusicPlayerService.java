package com.example.therapychannel.service.forum.MusicService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.therapychannel.R;

public class MusicPlayerService extends Service {

    IBinder binder = new LocalBinder();
    NotificationManager notificationManager;
    public final static String NOTIFICATION_CHANNEL_ID = "channel1";
    public final static String ACTION_STOP_MUSIC = "STOP_MUSIC";
    public final static int NOTIFICATION_ID = 10;

    public class LocalBinder extends Binder {
        public MusicPlayerService getService(){
            return MusicPlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("TherapyChannel","Start Command");
        configureNotificationManager();
        return START_NOT_STICKY;
    }

    private void configureNotificationManager(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Creating a notification channel
            NotificationChannel channel=new NotificationChannel(NOTIFICATION_CHANNEL_ID, "hello", NotificationManager.IMPORTANCE_HIGH);
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            Log.v("TherapyChannel","Hello");
        }
        else{
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        assert notificationManager != null;

    }

    private PendingIntent makePauseIntent(){
        Intent intent = new Intent(this, MusicPlayerServiceBroadcastReceiver.class);
        intent.setAction(ACTION_STOP_MUSIC);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        return pendingIntent;
    }

    MediaPlayer player;
    public void playMusic(Context context){
        configureNotificationManager();
        context.registerReceiver(new MusicPlayerServiceBroadcastReceiver(this),new IntentFilter(ACTION_STOP_MUSIC));
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .addAction(R.drawable.ic_baseline_stop_circle_24, "stop",makePauseIntent())
                .setContentTitle("Music To Help Insomnia").build();

        player = MediaPlayer.create(context,R.raw.demo);
        player.setLooping(false);
        player.start();
        startForeground(NOTIFICATION_ID,notification);
    }

    public void stopMusic(){
        if(player.isPlaying())
            player.stop();
        stopForeground(true);
    }

}
