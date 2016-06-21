package br.unitins.sigam2.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.unitins.sigam2.R;
import br.unitins.sigam2.services.GsonServices;

public class Main extends AppCompatActivity {

    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GsonServices services = new GsonServices();


        textView = (TextView) findViewById(R.id.textView1);


        String json = pegaJSON("samuel.silva", "ifto258");
        services.setJson(json);
        services.setUrl("https://sigam.ifto.edu.br/login");

        services.execute();

        String s = services.getSaida();
        textView.setText(s);


    }

    public String pegaJSON(String username, String password) {
        return "{'name':'" + username + "'pass'" + password + "}";
    }

}
