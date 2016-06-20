package br.unitins.sigam2.services;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hugo on 15/06/16.
 */
public class GsonServices extends AsyncTask<Void, Void, List<Object>> {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Object nota;
    private List<Object> notas;
    private String url;
    private String json;
    private String saida;

    public GsonServices() {
    }

    public GsonServices(Object nota, List<Object> notas) {
        this.nota = nota;
        this.notas = notas;
    }

    public Object getNota() {
        return nota;
    }

    public void setNota(Object nota) {
        this.nota = nota;
    }

    public List<Object> getNotas() {
        return notas;
    }

    public void setNotas(List<Object> notas) {
        this.notas = notas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
    /*
    public List<Object> getNetwork(String... params) {

        ArrayList<Object> dados = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        try {
            Request request = new Request.Builder()
                    .url(params[0])
                    .build();

            Response response = client.newCall(request).execute();


            if (response.code() == 200) {

                try {
                    String json = response.body().string();
                    dados = gson.fromJson(json, new TypeToken<ArrayList<Object>>() {
                    }.getType());

                    return dados;

                } catch (Exception ex) {
                    Log.e("GetNetwork", "Falha ao Buscar o arquivo JSON!: " + ex);
                    //mensagemdeFalha();
                }
            } else {
                Log.e("GetNetwork", "Servidor respondeu com codigo: " + response.code());
                //mensagemdeFalha();
            }
        } catch (Exception ex) {
            Log.e("GetNetwork", "A requisicao falhou!: " + ex);
            //mensagemdeFalha();
        }

        return dados;


    }*/


    @Override
    protected List<Object> doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();
        List<Object> o = new ArrayList<>();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            //o.add(response.body().string());
            setSaida(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    @Override
    protected void onPostExecute(List<Object> list) {
        super.onPostExecute(list);

        //notas.addAll(list);
    }


}
