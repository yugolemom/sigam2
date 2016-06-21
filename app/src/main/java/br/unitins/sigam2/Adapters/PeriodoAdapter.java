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
import br.unitins.sigam2.model.Periodo;

/**
 * Created by hugo on 20/06/16.
 */
public class PeriodoAdapter extends ArrayAdapter<Periodo>{

    Context vrContexto;


    public PeriodoAdapter(Context context, ArrayList<Periodo> vetorDados) {
        super(context, 0, vetorDados);
        vrContexto = context;
    }

    public View getView(int indice, View celulaReciclada, ViewGroup layoutPai){

        Periodo periodo  = this.getItem(indice);

        if(celulaReciclada == null) {
            Log.i("info", "Celula Criada!");
            celulaReciclada = LayoutInflater.from(getContext()).inflate(R.layout.celula_periodo, layoutPai, false);
        }

        else {
            Log.i("info", "Celula Reciclada!");
        }

        TextView idPeriodoText = (TextView)celulaReciclada.findViewById(R.id.idPeriodo);

        TextView periodoText = (TextView) celulaReciclada.findViewById(R.id.avaliacaoNome);

        periodoText.setText(periodo.getPeriodo());
        idPeriodoText.setText(periodo.getId().toString());

        return celulaReciclada;



    }

}
