package br.unitins.sigam2.model;

/**
 * Created by 1553910 on 21/06/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Faltas {

    @SerializedName("total_aulas")
    @Expose
    private String totalAulas;
    @SerializedName("total_aulas_ministradas")
    @Expose
    private String totalAulasMinistradas;
    @SerializedName("total_frequencia")
    @Expose
    private String totalFrequencia;
    @SerializedName("percentual_frequencia")
    @Expose
    private String percentualFrequencia;

    /**
     * @return The totalAulas
     */
    public String getTotalAulas() {
        return totalAulas;
    }

    /**
     * @param totalAulas The total_aulas
     */
    public void setTotalAulas(String totalAulas) {
        this.totalAulas = totalAulas;
    }

    /**
     * @return The totalAulasMinistradas
     */
    public String getTotalAulasMinistradas() {
        return totalAulasMinistradas;
    }

    /**
     * @param totalAulasMinistradas The total_aulas_ministradas
     */
    public void setTotalAulasMinistradas(String totalAulasMinistradas) {
        this.totalAulasMinistradas = totalAulasMinistradas;
    }

    /**
     * @return The totalFrequencia
     */
    public String getTotalFrequencia() {
        return totalFrequencia;
    }

    /**
     * @param totalFrequencia The total_frequencia
     */
    public void setTotalFrequencia(String totalFrequencia) {
        this.totalFrequencia = totalFrequencia;
    }

    /**
     * @return The percentualFrequencia
     */
    public String getPercentualFrequencia() {
        return percentualFrequencia;
    }

    /**
     * @param percentualFrequencia The percentual_frequencia
     */
    public void setPercentualFrequencia(String percentualFrequencia) {
        this.percentualFrequencia = percentualFrequencia;
    }

}
