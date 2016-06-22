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

import br.unitins.sigam2.Adapters.AvaliacaoAdapter;
import br.unitins.sigam2.R;
import br.unitins.sigam2.interfaces.AvaliacaoApiResponse;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.model.Avaliacao;
import br.unitins.sigam2.services.AvaliacaoServices;

public class AvaliacaoActivity extends AppCompatActivity implements AvaliacaoApiResponse, ErrorMessage {

    Integer idPeriodo = 0;
    Integer idMatricula = 0;
    Integer idDisciplina = 0;
    SessionManager manager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        manager = new SessionManager();


         /*Toolbar*/

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        toolbar.setTitle("Avaliações");
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

            idPeriodo = manager.getIntegerPreferences(AvaliacaoActivity.this, "idPeriodo");
            idMatricula = manager.getIntegerPreferences(AvaliacaoActivity.this, "idMatricula");
            idDisciplina = manager.getIntegerPreferences(AvaliacaoActivity.this, "idMateria");


        } else {

            idPeriodo = intent.getIntExtra("idPeriodo", 0);
            idMatricula = intent.getIntExtra("idMatricula", 0);
            idDisciplina = intent.getIntExtra("idMateria", 0);

        }



        AvaliacaoServices services = new AvaliacaoServices(this);

        String[] params = {"periodo", idPeriodo.toString(),
                "matricula", idMatricula.toString(),
                "disciplina", idDisciplina.toString()};

        Log.i("info", idMatricula + " clicado");

        services.setUrl("https://sigam.ifto.edu.br/avaliacoes");

        services.execute(params);
    }

    @Override
    public void postSaida(final ArrayList<Avaliacao> respostaAsync) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ListView meuListView = (ListView) findViewById(R.id.listAvalicao);


                AvaliacaoAdapter adptador = new AvaliacaoAdapter(getApplicationContext(), respostaAsync);

                meuListView.setAdapter(adptador);

                meuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Avaliacao a = respostaAsync.get(position);

                        Log.i("info", a.getId() + " clicado");
                        //ProximaPage(m.getId());
                    }
                });

                if (respostaAsync.size() > 0)
                    Toast.makeText(AvaliacaoActivity.this, "Informações Carregadas!", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public void postErrorMenssage(final String menssagemError) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AvaliacaoActivity.this, menssagemError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Voltar() {
        Intent intent = new Intent(AvaliacaoActivity.this, MateriasActivity.class);

        startActivity(intent);

        this.finish();
    }
}
