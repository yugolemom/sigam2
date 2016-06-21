package br.unitins.sigam2.model;

/**
 * Created by hugo on 20/06/16.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Periodo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("periodo")
    @Expose
    private String periodo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Periodo() {
    }

    /**
     *
     * @param id
     * @param periodo
     */
    public Periodo(Integer id, String periodo) {
        this.id = id;
        this.periodo = periodo;
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
     * The periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     *
     * @param periodo
     * The periodo
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
