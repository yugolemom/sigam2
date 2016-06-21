package br.unitins.sigam2.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import br.unitins.sigam2.interfaces.AvaliacaoApiResponse;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.model.Avaliacao;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 1553910 on 21/06/16.
 */
public class AvaliacaoServices extends AsyncTask<String, Void, ArrayList<Avaliacao>> {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public AvaliacaoApiResponse avaliacaoApiResponse = null;
    public ErrorMessage errorMessage = null;
    ProgressDialog vrProgresso = null;
    private Context mContext;
    private String url;

    public AvaliacaoServices(Context context) {
        this.mContext = context;
        this.avaliacaoApiResponse = (AvaliacaoApiResponse) context;
        this.errorMessage = (ErrorMessage) context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();

        vrProgresso = new ProgressDialog(mContext);
        vrProgresso.setCancelable(false);
        vrProgresso.setCanceledOnTouchOutside(false);
        vrProgresso.setMessage("Baixando dados....!");
        vrProgresso.setTitle("Aguarde!");
        vrProgresso.show();
    }


    @Override
    protected ArrayList<Avaliacao> doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();
        Gson gson = new Gson();

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add(params[0], params[1])
                    .add(params[2], params[3])
                    .add(params[4], params[5])
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() == 200) {

                try {
                    String json = response.body().string();
                    avaliacaos = gson.fromJson(json, new TypeToken<ArrayList<Avaliacao>>() {
                    }.getType());

                } catch (Exception ex) {
                    Log.e("GetDados", "Falha ao Buscar o arquivo JSON!: " + ex);
                    errorMessage.postErrorMenssage("Falha ao Buscar o arquivo JSON!");
                }
            } else {
                Log.e("GetDados", "Servidor respondeu com codigo: " + response.code());
                errorMessage.postErrorMenssage("Servidor respondeu com codigo: " + response.code());
            }
        } catch (Exception ex) {
            Log.e("GetDados", "A requisicao falhou!: " + ex);
            errorMessage.postErrorMenssage("A requisicao falhou!");
        }
        return avaliacaos;
    }

    @Override
    protected void onPostExecute(ArrayList<Avaliacao> avaliacaos) {
        //super.onPostExecute(list);
        vrProgresso.dismiss();

        if (avaliacaoApiResponse != null) {
            avaliacaoApiResponse.postSaida(avaliacaos);
        } else {
            Log.e("PeriodoApiResponse", "Problema para Gerar Array List");
        }
    }
}
