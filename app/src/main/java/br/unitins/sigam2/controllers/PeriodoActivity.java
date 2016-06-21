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

import br.unitins.sigam2.Adapters.PeriodoAdapter;
import br.unitins.sigam2.R;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.interfaces.PeriodoApiResponse;
import br.unitins.sigam2.model.Periodo;
import br.unitins.sigam2.services.PeriodoServices;

public class PeriodoActivity extends AppCompatActivity implements PeriodoApiResponse, ErrorMessage {

    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    static final String STATE_MATRICULA = "idMatricula";
    Integer idMatricula = 0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            //mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
            idMatricula = savedInstanceState.getInt(STATE_MATRICULA);
        } else {
            Intent intent = getIntent();
            idMatricula = intent.getIntExtra("idMatricula", 0);
        }

         /*Toolbar*/

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        toolbar.setTitle("Periodos");
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


        PeriodoServices services = new PeriodoServices(this);


        String[] params = {"id",idMatricula.toString()};
        services.setUrl("https://sigam.ifto.edu.br/periodos");

        services.execute(params);
    }

    @Override
    public void postErrorMenssage(final String menssagemError) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PeriodoActivity.this, menssagemError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void postSaida(final ArrayList<Periodo> respostaAsync) {
        runOnUiThread(new Runnable()  {
            @Override
            public void run() {


                ListView meuListView = (ListView)findViewById(R.id.listPeriodo);


                PeriodoAdapter adptador = new PeriodoAdapter(getApplicationContext(), respostaAsync);

                meuListView.setAdapter(adptador);

                meuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Periodo p = respostaAsync.get(position);

                        Log.i("info", p.getId() + " clicado");
                        ProximaPage(p.getId());
                    }
                });

                if (respostaAsync.size() > 0)
                    Toast.makeText(PeriodoActivity.this, "Informações Carregadas!", Toast.LENGTH_SHORT).show();



            }
        });
    }

    public void ProximaPage(Integer idPeriodo){
        Intent intent = new Intent(PeriodoActivity.this,MateriasActivity.class);

        intent.putExtra("idPeriodo", idPeriodo);
        intent.putExtra("idMatricula", idMatricula);


        startActivity(intent);

        this.finish();

    }

    public void Voltar() {
        Intent intent = new Intent(PeriodoActivity.this, CursoActivity.class);

        startActivity(intent);

        this.finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, 1);
        savedInstanceState.putInt(STATE_LEVEL, 2);
        savedInstanceState.putInt(STATE_MATRICULA, idMatricula);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        //mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);

        this.idMatricula = savedInstanceState.getInt(STATE_MATRICULA);

    }
}
