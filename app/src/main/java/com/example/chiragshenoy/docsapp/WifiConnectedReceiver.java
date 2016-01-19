package com.example.chiragshenoy.docsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import io.realm.Realm;

/**
 * Created by Chirag Shenoy on 19-Jan-16.
 */
public class WifiConnectedReceiver extends BroadcastReceiver {
    public WifiConnectedReceiver() {
    }

    Realm realm;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Helper.isOnlineOnWifi(context) || Helper.isOnlineOnMobile(context)) {
            Intent uploadIntent = new Intent(context, UploadService.class);

            context.startService(uploadIntent);
            Toast.makeText(context, "Syncing with our servers :)", Toast.LENGTH_SHORT).show();

//            realm = Realm.getInstance(context);


            //Fetch all journeys that have isSynced = false
//            RealmResults<Journey> journeyRealmResults = realm.where(Journey.class)
//                    .equalTo("isSynced", false)
//                    .findAll();
//
//
//            //For each journey, get all road irregularties bounded by start and end time
//
//
//            //Save each irregularity to parse
//            for (Journey j : journeyRealmResults) {
//                ParseObject p = Journey.convertToParseObject(j);
//                p.saveInBackground();
//                Log.e("Adding to parse", "" + j.getStartLat());
//            }

            //Save each journey to parse


            //Change isSynced to true for the journeys

        }

    }
}
