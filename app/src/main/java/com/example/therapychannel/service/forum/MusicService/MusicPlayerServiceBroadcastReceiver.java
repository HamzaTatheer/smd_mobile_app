package com.example.therapychannel.service.forum.MusicService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MusicPlayerServiceBroadcastReceiver extends BroadcastReceiver {
    Service service;

    MusicPlayerServiceBroadcastReceiver(){
        Log.v("TherapyChannel","WOW");
    }

    MusicPlayerServiceBroadcastReceiver(Service service){
        super();
        this.service = service;
        Log.v("TherapyChannel","SUPER WOW");
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {

        String whichAction = intent.getAction();

        Log.v("TherapyChannel","ACTION");
        Log.v("TherapyChannel",whichAction);

        switch (whichAction)
        {
            case MusicPlayerService.ACTION_STOP_MUSIC:
                service.stopForeground(true);
                service.stopSelf();
                return;
        }

    }
}
