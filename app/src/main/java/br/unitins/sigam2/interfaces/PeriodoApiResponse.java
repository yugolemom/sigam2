package br.unitins.sigam2.interfaces;

import java.util.ArrayList;

import br.unitins.sigam2.model.Periodo;

/**
 * Created by hugo on 20/06/16.
 */
public interface PeriodoApiResponse {
    void postSaida(ArrayList<Periodo> respostaAsync);
}
