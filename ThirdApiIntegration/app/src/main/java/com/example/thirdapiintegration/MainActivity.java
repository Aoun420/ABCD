package com.example.thirdapiintegration;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String url="https://api.exchangeratesapi.io/latest";
    private static final String TAG = "abc";
    TextView tv_inrrup;
    EditText edt_inr,GetValue;
    Button btnconvert;
    double rate;
    double result;
    String item="USD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_inrrup=findViewById(R.id.tv);
        edt_inr=findViewById(R.id.edt);
        GetValue=findViewById(R.id.edit);
        btnconvert=findViewById(R.id.btn);
        btnconvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int edt_value=Integer.parseInt(edt_inr.getText().toString());

                Call<Example> example=BloggerApi.netcurrency().data();
                example.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        Example myexample=response.body();
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        if(item=="USD") {
                            rate = myexample.getRates().getUSD();
                            result = edt_value * rate;
                        }
                        else if (item=="NZD"){
                            rate = myexample.getRates().getNZD();
                            result = edt_value * rate;
                        }
                        else if (item=="CAD"){
                            rate = myexample.getRates().getCAD();
                            result = edt_value * rate;
                        }


                        tv_inrrup.setText(edt_value+"("+item+") = "+String.valueOf(result));
                    }
                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner) ;
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("USD");
        strings.add("CAD");
        strings.add("NZD");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,strings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item=strings.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Error Happened", Toast.LENGTH_SHORT).show();
            }
        });
        }
}