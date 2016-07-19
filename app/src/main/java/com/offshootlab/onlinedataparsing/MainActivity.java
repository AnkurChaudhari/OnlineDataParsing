package com.offshootlab.onlinedataparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recylerView;
    LinearLayoutManager layoutManager;
    RequestQueue requestQueue;
    String URL = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    List<Actor> list ;
    ActorAdaprter adaprter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        recylerView = (RecyclerView) findViewById(R.id.recyclerView);
        recylerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recylerView.setLayoutManager(layoutManager);

        adaprter = new ActorAdaprter(list);
        recylerView.setAdapter(adaprter);




        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray ja = response.getJSONArray("actors");

                    for(int i=0;i<ja.length();i++) {

                        JSONObject jo = ja.getJSONObject(i);

                        String name = jo.getString("name");
                        String country = jo.getString("country");

                        Actor actor = new Actor();
                        actor.setName(name);
                        actor.setCountry(country);

                        list.add(actor);
                    }
                    adaprter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jor);

    }
}
