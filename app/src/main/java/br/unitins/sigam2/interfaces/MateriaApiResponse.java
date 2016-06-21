package br.unitins.sigam2.interfaces;

import java.util.ArrayList;

import br.unitins.sigam2.model.Materia;

/**
 * Created by 1553910 on 21/06/16.
 */
public interface MateriaApiResponse {
    void postSaida(ArrayList<Materia> respostaAsync);
}
