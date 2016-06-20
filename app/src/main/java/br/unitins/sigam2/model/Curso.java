package br.unitins.sigam2.model;

/**
 * Created by 1553910 on 20/06/16.
 */

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Curso {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("curso")
    @Expose
    private String curso;
    @SerializedName("ingresso")
    @Expose
    private String ingresso;

    /**
     * No args constructor for use in serialization
     *
     */
    public Curso() {
    }

    /**
     *
     * @param id
     * @param ingresso
     * @param curso
     * @param matricula
     */
    public Curso(Integer id, String matricula, String curso, String ingresso) {
        this.id = id;
        this.matricula = matricula;
        this.curso = curso;
        this.ingresso = ingresso;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     *
     * @param matricula
     * The matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     *
     * @return
     * The curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     *
     * @param curso
     * The curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     *
     * @return
     * The ingresso
     */
    public String getIngresso() {
        return ingresso;
    }

    /**
     *
     * @param ingresso
     * The ingresso
     */
    public void setIngresso(String ingresso) {
        this.ingresso = ingresso;
    }

}