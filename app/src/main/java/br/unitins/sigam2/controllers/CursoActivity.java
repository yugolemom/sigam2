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

import br.unitins.sigam2.Adapters.CursoAdapter;
import br.unitins.sigam2.R;
import br.unitins.sigam2.interfaces.CursoApiResponse;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.model.Curso;
import br.unitins.sigam2.services.CursoServices;

public class CursoActivity extends AppCompatActivity implements CursoApiResponse, ErrorMessage {

    SessionManager manager;
    private Toolbar toolbar;
    private String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        manager = new SessionManager();

        /*Toolbar*/

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        toolbar.setTitle("Cursos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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

        if (intent.getStringExtra("number") == null) {
            manager = new SessionManager();

            number = manager.getPreferences(CursoActivity.this, "number");
        } else {
            number = intent.getStringExtra("number");
        }

        CursoServices services = new CursoServices(this);

        //String json = pegaJSON("samuel.silva", "ifto258");
        //String json2 = pegaJSON2("1196933138");

        String[] params = {"number", number};

        Log.i("info", number + " clicado");
        services.setUrl("https://sigam.ifto.edu.br/cursos");

        services.execute(params);

    }

    public String pegaJSON(String username, String password) {
        return "{'name':'" + username + "'pass:'" + password + "}";
    }

    public String pegaJSON2(String number) {
        return "{'number':'" + number + "}";
    }

    @Override
    public void postSaida(final ArrayList<Curso> respostaAsync) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                ListView meuListView = (ListView) findViewById(R.id.listCurso);
                Curso c = new Curso();

                CursoAdapter adptador = new CursoAdapter(getApplicationContext(), respostaAsync);

                meuListView.setAdapter(adptador);

                meuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Curso c = respostaAsync.get(position);

                        Log.i("info", c.getId() + " clicado");


                        ProximaPage(c.getId());
                    }
                });

                if (respostaAsync.size() > 0)
                    Toast.makeText(CursoActivity.this, "Informações Carregadas!", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void postErrorMenssage(final String menssagemError) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CursoActivity.this, menssagemError, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ProximaPage(Integer idMatricula) {


        manager.setIntegerPreferences(CursoActivity.this, "idMatricula", idMatricula);

        Intent intent = new Intent(CursoActivity.this, PeriodoActivity.class);


        intent.putExtra("idMatricula", idMatricula);

        startActivity(intent);

        this.finish();
    }

    public void Voltar() {
        Intent intent = new Intent(CursoActivity.this, FormActivity.class);

        startActivity(intent);

        this.finish();
    }


}
