package com.example.login_volleyjson;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;






import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activity_beranda extends AppCompatActivity {
        private TextView txtJSON;
        private Button btnJSON;
        private RequestQueue mQueue;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_beranda);

            mQueue = Volley.newRequestQueue(this);
            txtJSON = findViewById(R.id.txtJSON);
            btnJSON = findViewById(R.id.btnJSON);

            btnJSON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uraiJson();
                }


            });
        }
        private void uraiJson() {
            String url = "http://192.168.5.188/utsdb/show.php";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject Mahasantri = jsonArray.getJSONObject(i);
                                    String id = Mahasantri.getString("id");
                                    String nama = Mahasantri.getString("nama");
                                    String kelas = Mahasantri.getString("kelas");
                                    txtJSON.append(
                                            id+", "+nama+", "+kelas+"\n\n"
                                    );
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(activity_beranda.this, "EROR" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(activity_beranda.this, ""+error, Toast.LENGTH_SHORT).show();

                }
            });
            mQueue.add(request);
        }
    }