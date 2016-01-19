package com.example.chiragshenoy.docsapp;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Post p;

    String url;
    String tpAppend;


    MainAdapter mainAdapter;
    Realm realm;
    ArrayList<RealmMessage> mDataSet = new ArrayList<>();

    EditText etMsg;
    SoundPool ourSounds;
    int beep;
    int soundmedium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        ourSounds = new SoundPool.Builder()
                .setMaxStreams(15)
                .setAudioAttributes(audioAttributes)
                .build();


        beep = ourSounds.load(this, R.raw.beep, 1);
        Button btSend = (Button) findViewById(R.id.btSend);
        etMsg = (EditText) findViewById(R.id.etSend);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);


        realm = Realm.getInstance(getApplicationContext());


        mainAdapter = new MainAdapter(getApplicationContext(), mDataSet);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(mainAdapter);

//        String url = "http://www.personalityforge.com/api/chat/?apiKey=6nt5d1nJHkqbkphe&User=Hi&chatBotID=63906&externalID=chirag1";

        url = "http://www.personalityforge.com/api/chat/?apiKey=6nt5d1nJHkqbkphe&message=";
        tpAppend = "&chatBotID=63906&externalID=chirag1";


        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = etMsg.getText().toString();
                Message m = new Message();
                m.setMessage(msg);
                m.setisMe(true);

                RealmMessage r = new RealmMessage();
                r.setisMe(true);
                r.setMessage(msg);
                realm.beginTransaction();
                realm.copyToRealm(r);
                realm.commitTransaction();

                mainAdapter.add(r, mainAdapter.getItemCount());

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url + msg.replace(" ", "") + tpAppend,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("Response", response);

                                // Result handling
                                Gson gson = new GsonBuilder().create();
                                p = gson.fromJson(response, Post.class);
                                Log.e("Response", p.toString().replace(" ", ""));
                                Message User = p.getMessage();
                                User.setisMe(false);

                                RealmMessage r = new RealmMessage();
                                r.setMessage(User.getMessage());
                                r.setisMe(false);
                                realm.beginTransaction();
                                realm.copyToRealm(r);
                                realm.commitTransaction();

                                mainAdapter.add(r, mainAdapter.getItemCount());
                                recyclerView.scrollToPosition(mDataSet.size() - 1);
                                ourSounds.play(beep, 0.9f, 0.9f, 1, 0, 1);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Error handling
                        Log.e("Error", "Something went wrong!");
                        error.printStackTrace();

                    }
                });

// Add the request to the queue
                Volley.newRequestQueue(MainActivity.this).add(stringRequest);
                etMsg.setText("");

                recyclerView.scrollToPosition(mDataSet.size() - 1);
            }

        });
    }

//
//    @OnClick(R.id.btSend)
//    void submit() {
//
//        String msg = etMsg.getText().toString();
//        Message m = new Message();
//        m.setMessage(msg);
//        m.setisMe(true);
//        realm.beginTransaction();
//        realm.copyToRealm(m);
//        realm.commitTransaction();
//
//        mainAdapter.add(m, mainAdapter.getItemCount());
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + msg.replace(" ", "") + tpAppend,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("Response", response);
//
//                        // Result handling
//                        Gson gson = new GsonBuilder().create();
//                        p = gson.fromJson(response, Post.class);
//                        Log.e("Response", p.toString().replace(" ", ""));
//                        Message User = p.getMessage();
//                        User.setisMe(false);
//                        realm.beginTransaction();
//                        realm.copyToRealm(User);
//                        realm.commitTransaction();
//
//                        mainAdapter.add(User, mainAdapter.getItemCount());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                // Error handling
//                Log.e("Error", "Something went wrong!");
//                error.printStackTrace();
//
//            }
//        });
//
//// Add the request to the queue
//        Volley.newRequestQueue(this).add(stringRequest);
//        etMsg.setText("");
//    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            RealmResults<RealmMessage> r = realm.where(RealmMessage.class)
                    .findAll();
            for (RealmMessage m : r) {
                mDataSet.add(m);
            }

        } catch (Exception e) {

        }
    }
}
