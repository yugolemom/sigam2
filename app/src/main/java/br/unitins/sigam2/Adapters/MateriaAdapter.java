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
import br.unitins.sigam2.model.Materia;

/**
 * Created by 1553910 on 21/06/16.
 */
public class MateriaAdapter extends ArrayAdapter<Materia> {

    Context vrContexto;

    public MateriaAdapter(Context context, ArrayList<Materia> objects) {
        super(context, 0, objects);
        vrContexto = context;

    }

    @Override
    public View getView(int indice, View celulaReciclada, ViewGroup layoutPai) {
        Materia materia = this.getItem(indice);

        if (celulaReciclada == null) {
            Log.i("info", "Celula Criada!");
            celulaReciclada = LayoutInflater.from(getContext()).inflate(R.layout.celula_materias, layoutPai, false);
        } else {
            Log.i("info", "Celula Reciclada!");
        }

        TextView nomeDisciplina = (TextView) celulaReciclada.findViewById(R.id.textMateriaDisciplina);

        TextView nomeProfessor = (TextView) celulaReciclada.findViewById(R.id.textMateriaNomeProfessor);

        TextView status = (TextView) celulaReciclada.findViewById(R.id.textMateriaStatus);

        TextView creditos = (TextView) celulaReciclada.findViewById(R.id.textMateriaCreditos);

        TextView carga_horaria = (TextView) celulaReciclada.findViewById(R.id.textMateriaCargaHoraria);

        nomeDisciplina.setText(materia.getNome());
        nomeProfessor.setText(materia.getDocentes());
        status.setText(materia.getStatus());

        creditos.setText(materia.getCredito().toString());
        carga_horaria.setText(materia.getCargaHoraria().toString());


        return celulaReciclada;
    }
}
