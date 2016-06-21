package br.unitins.sigam2.model;

/**
 * Created by 1553910 on 21/06/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Avaliacao {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ordem")
    @Expose
    private Integer ordem;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("nota")
    @Expose
    private String nota;

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
     * @return The ordem
     */
    public Integer getOrdem() {
        return ordem;
    }

    /**
     * @param ordem The ordem
     */
    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
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
     * @return The nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota The nota
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

}
