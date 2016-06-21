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
import br.unitins.sigam2.model.Avaliacao;

/**
 * Created by 1553910 on 21/06/16.
 */
public class AvaliacaoAdapter extends ArrayAdapter<Avaliacao> {

    Context vrContexto;


    public AvaliacaoAdapter(Context context, ArrayList<Avaliacao> vetorDados) {
        super(context, 0, vetorDados);
        vrContexto = context;
    }

    public View getView(int indice, View celulaReciclada, ViewGroup layoutPai) {

        Avaliacao avaliacao = this.getItem(indice);

        if (celulaReciclada == null) {
            Log.i("info", "Celula Criada!");
            celulaReciclada = LayoutInflater.from(getContext()).inflate(R.layout.celula_avaliacao, layoutPai, false);
        } else {
            Log.i("info", "Celula Reciclada!");
        }

        TextView avalicaoText = (TextView) celulaReciclada.findViewById(R.id.avaliacaoNome);
        TextView notaText = (TextView) celulaReciclada.findViewById(R.id.avaliacaoNota);

        avalicaoText.setText(avaliacao.getNome());
        notaText.setText(avaliacao.getNota());


        return celulaReciclada;


    }
}
