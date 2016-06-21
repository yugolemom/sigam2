package br.unitins.sigam2.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.unitins.sigam2.R;
import br.unitins.sigam2.interfaces.CursoApiResponse;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.model.Curso;
import br.unitins.sigam2.services.CursoServices;

public class CursoActivity extends AppCompatActivity implements CursoApiResponse, ErrorMessage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        CursoServices services = new CursoServices(this);
        ArrayList<Curso> list = new ArrayList<>();



        String json = pegaJSON("samuel.silva", "ifto258");
        String json2 = pegaJSON2("1196933138");

        String[] params = {"number","1196933138"};
        services.setUrl("https://sigam.ifto.edu.br/cursos");

        services.execute(params);



    }

    public String pegaJSON(String username, String password) {
        return "{'name':'" + username + "'pass:'" + password + "}";
    }

    public String pegaJSON2(String number) {
        return "{'number':'" + number +"}";
    }

    @Override
    public void postSaida(ArrayList<Curso> respostaAsync) {

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
}
