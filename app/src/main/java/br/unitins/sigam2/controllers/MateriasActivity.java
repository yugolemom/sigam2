package br.unitins.sigam2.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.unitins.sigam2.Adapters.MateriaAdapter;
import br.unitins.sigam2.R;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.interfaces.MateriaApiResponse;
import br.unitins.sigam2.model.Materia;
import br.unitins.sigam2.services.MateriaServices;

public class MateriasActivity extends AppCompatActivity implements MateriaApiResponse, ErrorMessage {

    Integer idPeriodo = 0;
    Integer idMatricula = 0;
    SessionManager manager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        manager = new SessionManager();

         /*Toolbar*/

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        toolbar.setTitle("Materias");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("info!", "Button Clicado!");
                //onBackPressed();
                Voltar();

            }
        });


        Intent intent = getIntent();


        if (intent.getIntExtra("idMatricula", 0) == 0) {

            idMatricula = manager.getIntegerPreferences(MateriasActivity.this, "idMatricula");
            idPeriodo = manager.getIntegerPreferences(MateriasActivity.this, "idPeriodo");
        } else {
            idPeriodo = intent.getIntExtra("idPeriodo", 0);
            idMatricula = intent.getIntExtra("idMatricula", 0);
        }

        MateriaServices services = new MateriaServices(this);




        String[] params = {"id", idPeriodo.toString(),
                "number", idMatricula.toString()};

        Log.i("info", idMatricula + " clicado");
        services.setUrl("https://sigam.ifto.edu.br/disciplinas");

        services.execute(params);
    }

    @Override
    public void postErrorMenssage(final String menssagemError) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MateriasActivity.this, menssagemError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void postSaida(final ArrayList<Materia> respostaAsync) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ListView meuListView = (ListView) findViewById(R.id.listMateria);


                MateriaAdapter adptador = new MateriaAdapter(getApplicationContext(), respostaAsync);

                meuListView.setAdapter(adptador);

                meuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Materia m = respostaAsync.get(position);

                        Log.i("info", m.getId() + " clicado");
                        ProximaPage(m.getId());
                    }
                });

                if (respostaAsync.size() > 0)
                    Toast.makeText(MateriasActivity.this, "Informações Carregadas!", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void ProximaPage(Integer idMateria) {

        manager.setIntegerPreferences(MateriasActivity.this, "idMateria", idMateria);

        Intent intent = new Intent(MateriasActivity.this, AvaliacaoActivity.class);

        intent.putExtra("idMateria", idMateria);
        intent.putExtra("idMatricula", this.idMatricula);
        intent.putExtra("idPeriodo", this.idPeriodo);


        startActivity(intent);

        this.finish();

    }

    public void Voltar() {
        Intent intent = new Intent(MateriasActivity.this, PeriodoActivity.class);

        startActivity(intent);

        this.finish();
    }
}
