package br.unitins.sigam2.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.unitins.sigam2.R;


public class Main extends AppCompatActivity{

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar*/

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        //toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    public void ProximaPage(View view){
        Intent intent = new Intent(Main.this,CursoActivity.class);
        startActivity(intent);
    }


}
