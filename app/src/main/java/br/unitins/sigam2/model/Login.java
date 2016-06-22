package br.unitins.sigam2.model;

/**
 * Created by hugo on 21/06/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Login {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("autenticacao")
    @Expose
    private Boolean autenticacao;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("number")
    @Expose
    private String number;

    /**
     * @return The codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo The codigo
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return The autenticacao
     */
    public Boolean getAutenticacao() {
        return autenticacao;
    }

    /**
     * @param autenticacao The autenticacao
     */
    public void setAutenticacao(Boolean autenticacao) {
        this.autenticacao = autenticacao;
    }

    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return The number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number The number
     */
    public void setNumber(String number) {
        this.number = number;
    }

}