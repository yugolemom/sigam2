package br.unitins.sigam2.model;

/**
 * Created by 1553910 on 21/06/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Materia {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusid")
    @Expose
    private Integer statusid;
    @SerializedName("credito")
    @Expose
    private Integer credito;
    @SerializedName("carga_horaria")
    @Expose
    private Integer cargaHoraria;
    @SerializedName("docentes")
    @Expose
    private String docentes;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome The nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The statusid
     */
    public Integer getStatusid() {
        return statusid;
    }

    /**
     * @param statusid The statusid
     */
    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    /**
     * @return The credito
     */
    public Integer getCredito() {
        return credito;
    }

    /**
     * @param credito The credito
     */
    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    /**
     * @return The cargaHoraria
     */
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria The carga_horaria
     */
    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return The docentes
     */
    public String getDocentes() {
        return docentes;
    }

    /**
     * @param docentes The docentes
     */
    public void setDocentes(String docentes) {
        this.docentes = docentes;
    }

}
