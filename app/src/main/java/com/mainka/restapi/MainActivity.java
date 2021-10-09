package com.mainka.restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mainka.restapi.Entity.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static rvAdapter adapter;
    private static ArrayList<Model> modelArrayList;
    String url = "https://api.spacexdata.com/v4/crew";
    ProgressBar progressBar;

    Button refresh, delete;
    static User_Database user_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh=findViewById(R.id.refresh);
        delete=findViewById(R.id.delete);
        recyclerView = findViewById(R.id.Recycler);
        modelArrayList = new ArrayList<>();

        progressBar=findViewById(R.id.progressbar);
        user_database=Room.databaseBuilder(getApplicationContext(),
                User_Database.class,"db_name").allowMainThreadQueries().build();

        getData();
        buildRecyclerView();


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getData();
                buildRecyclerView();
            }
        });
       delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete();
                Toast.makeText(MainActivity.this, "All data deleted", Toast.LENGTH_SHORT).show();


            }
        });
    }
    private  ArrayList<Model> getData() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recyclerView.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);

                        String Name = responseObj.getString("name");
                        String Agency = responseObj.getString("agency");
                        String Wiki = responseObj.getString("wikipedia");
                        String Image = responseObj.getString("image");
                        String Status= responseObj.getString("status");
                        modelArrayList.add(new Model(Name,Agency,Image,Wiki,Status));

                        user_database.userDao().insertAll(modelArrayList);
                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
        return modelArrayList;
    }

    private void buildRecyclerView() {

        adapter = new rvAdapter(modelArrayList, MainActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    static void delete(){
        user_database.userDao().delete(modelArrayList);
        modelArrayList.clear();
        adapter.notifyDataSetChanged();
    }
}
