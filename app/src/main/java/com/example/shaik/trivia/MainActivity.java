package com.example.shaik.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RadioGroup radioGroup;
    EditText tmath,tmonth, tdate, tyear;
    Button button;

    String root="https://numbersapi.p.mashape.com/";
    String end_math="/math?fragment=true&json=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=findViewById(R.id.radio);
        tmath=findViewById(R.id.tnum);
        tmonth=findViewById(R.id.tmonth);
        tdate=findViewById(R.id.tdate);
        tyear=findViewById(R.id.tyear);
        button=findViewById(R.id.button);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedid) {
                switch(checkedid)
                {
                    case R.id.rmath:
                         //this block will be ex
                        tmath.setVisibility(View.VISIBLE);
                         tmonth.setVisibility(View.GONE);
                         tyear.setVisibility(View.GONE);
                         tdate.setVisibility(View.GONE);

                        break;
                    case R.id.rmonth:

                        tmath.setVisibility(View.GONE);
                        tmonth.setVisibility(View.VISIBLE);
                        tyear.setVisibility(View.GONE);
                        tdate.setVisibility(View.VISIBLE);
                        break;

                    case R.id.ryear:

                        tmath.setVisibility(View.GONE);
                        tmonth.setVisibility(View.GONE);
                        tyear.setVisibility(View.VISIBLE);
                        tdate.setVisibility(View.GONE);
                        break;


                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radioGroup.getCheckedRadioButtonId()){

                    case R.id.rmath:
                        performreq(root+tmath.getText().toString()+end_math);
   
                        break;


                }
            }
        });

        textView=  findViewById(R.id.hello);




    }



    public void performreq(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                       //The string response contains the response you get back from the request it could be json, xml or just string





                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

        //Add headers here as headers.put("key","value");

                return headers;
            }
            @Override
            public Map<String,String> getParams(){
                    Map<String,String> mparams=new HashMap<>();
            //Add your parameters here as mparams.put("key","value"); you will most probably use this method when doing a post request
                    return mparams;
            }
                @Override
                public String getBodyContentType() {
                return "application/json";
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }



}
