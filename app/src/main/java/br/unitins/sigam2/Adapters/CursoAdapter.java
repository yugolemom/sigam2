package br.unitins.sigam2.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.unitins.sigam2.R;
import br.unitins.sigam2.model.Curso;

/**
 * Created by hugo on 20/06/16.
 */
public class CursoAdapter extends ArrayAdapter<Curso> {

    Context vrContexto;


    public CursoAdapter(Context context, ArrayList<Curso> vetorDados) {
        super(context, 0, vetorDados);
        vrContexto = context;
    }

    public View getView(int indice, View celulaReciclada, ViewGroup layoutPai){

        Curso curso  = this.getItem(indice);

        if(celulaReciclada == null) {
            Log.i("info", "Celula Criada!");
            celulaReciclada = LayoutInflater.from(getContext()).inflate(R.layout.celula_curso, layoutPai, false);
        }

        else {
            Log.i("info", "Celula Reciclada!");
        }

        TextView matriculaText = (TextView)celulaReciclada.findViewById(R.id.nomePeriodo);
        TextView cursoText = (TextView)celulaReciclada.findViewById(R.id.nomeCurso);
        TextView ingressoText = (TextView)celulaReciclada.findViewById(R.id.ingressoCurso);


        matriculaText.setText(curso.getMatricula());
        cursoText.setText(curso.getCurso());
        ingressoText.setText(curso.getIngresso());

        return celulaReciclada;



    }
}
