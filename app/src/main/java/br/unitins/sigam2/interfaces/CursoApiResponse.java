package br.unitins.sigam2.interfaces;

import java.util.ArrayList;

import br.unitins.sigam2.model.Curso;

/**
 * Created by 1553910 on 20/06/16.
 */
public interface CursoApiResponse {
   void postSaida(ArrayList<Curso> respostaAsync);
}
