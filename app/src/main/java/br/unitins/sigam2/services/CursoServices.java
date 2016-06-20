package br.unitins.sigam2.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.mimecraft.FormEncoding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.sigam2.interfaces.CursoApiResponse;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.model.Curso;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hugo on 15/06/16.
 */
public class CursoServices extends AsyncTask<String, Void, ArrayList<Curso>> {

    private Context mContext;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String url;

    ProgressDialog vrProgresso = null;
    public CursoApiResponse cursoApiResponse = null;
    public ErrorMessage errorMessage = null;


    public CursoServices(Context context){
        this.mContext = context;
        this.cursoApiResponse = (CursoApiResponse) context;
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
    protected ArrayList<Curso>  doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();
        ArrayList<Curso> cursos = new ArrayList<>();
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
                    cursos = gson.fromJson(json, new TypeToken<ArrayList<Curso>>(){}.getType());

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

        return cursos;
    }

    @Override
    protected void onPostExecute(ArrayList<Curso> cursoList) {
        //super.onPostExecute(list);
        vrProgresso.dismiss();

        if(cursoApiResponse != null){
            cursoApiResponse.postSaida(cursoList);
        }else{
            Log.e("CursoApiResponse", "Problema para Gerar Array List");
        }

    }



}
