package br.unitins.sigam2.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import br.unitins.sigam2.interfaces.CursoApiResponse;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.interfaces.PeriodoApiResponse;
import br.unitins.sigam2.model.Periodo;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hugo on 20/06/16.
 */
public class PeriodoServices extends AsyncTask<String,Void, ArrayList<Periodo>> {

    private Context mContext;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String url;

    ProgressDialog vrProgresso = null;
    public PeriodoApiResponse periodoApiResponse = null;
    public ErrorMessage errorMessage = null;

    public PeriodoServices(Context context) {
        this.mContext = context;
        this.periodoApiResponse = (PeriodoApiResponse) context;
        this.errorMessage =  (ErrorMessage) context;
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
    protected ArrayList<Periodo> doInBackground(String... params) {



        OkHttpClient client = new OkHttpClient();
        ArrayList<Periodo> periodos = new ArrayList<>();
        Gson gson = new Gson();

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add(params[0] ,params[1])
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();

            if(response.code() == 200){

                try{
                    String json = response.body().string();
                    periodos = gson.fromJson(json, new TypeToken<ArrayList<Periodo>>(){}.getType());

                }catch (Exception ex){
                    Log.e("GetDados", "Falha ao Buscar o arquivo JSON!: " + ex);
                    errorMessage.postErrorMenssage("Falha ao Buscar o arquivo JSON!");
                }
            }else{
                Log.e("GetDados", "Servidor respondeu com codigo: " + response.code());
                errorMessage.postErrorMenssage("Servidor respondeu com codigo: " + response.code());
            }
        } catch (Exception ex){
            Log.e("GetDados", "A requisicao falhou!: " + ex);
            errorMessage.postErrorMenssage("A requisicao falhou!");
        }
        return periodos;

    }

    @Override
    protected void onPostExecute(ArrayList<Periodo> periodos) {
        //super.onPostExecute(list);
        vrProgresso.dismiss();

        if(periodoApiResponse != null){
            periodoApiResponse.postSaida(periodos);
        }else{
            Log.e("PeriodoApiResponse", "Problema para Gerar Array List");
        }

    }

}
