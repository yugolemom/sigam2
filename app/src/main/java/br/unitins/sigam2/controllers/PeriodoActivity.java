package br.unitins.sigam2.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    Integer idMatricula = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo);

        Intent intent = getIntent();

        PeriodoServices services = new PeriodoServices(this);


        idMatricula = intent.getIntExtra("idMatricula", 0);


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

                Toast.makeText(PeriodoActivity.this, "Informações Carregadas!", Toast.LENGTH_SHORT).show();



            }
        });
    }

    public void ProximaPage(Integer idPeriodo){
        Intent intent = new Intent(PeriodoActivity.this,MateriasActivity.class);

        intent.putExtra("idPeriodo", idPeriodo);
        intent.putExtra("idMateria", this.idMatricula);


        startActivity(intent);

        this.finish();

    }
}
