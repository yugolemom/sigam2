package br.unitins.sigam2.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.interfaces.LoginApiResponse;
import br.unitins.sigam2.model.Login;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hugo on 21/06/16.
 */
public class LoginServices extends AsyncTask<String, Void, Login> {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public LoginApiResponse loginApiResponse = null;
    public ErrorMessage errorMessage = null;
    ProgressDialog vrProgresso = null;
    private Context mContext;
    private String url;

    public LoginServices(Context context) {
        this.mContext = context;
        this.loginApiResponse = (LoginApiResponse) context;
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
        vrProgresso.setMessage("Validando informações....!");
        vrProgresso.setTitle("Aguarde!");
        vrProgresso.show();
    }

    @Override
    protected Login doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Login l = new Login();
        Gson gson = new Gson();

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add(params[0], params[1])
                    .add(params[2], params[3])
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() == 200) {

                try {
                    String json = response.body().string();
                    l = gson.fromJson(json, Login.class);

                } catch (Exception ex) {
                    Log.e("GetDados", "Falha ao Buscar o arquivo JSON!: " + ex);
                    errorMessage.postErrorMenssage("Falha ao Buscar o arquivo JSON!");
                }
            } else if (response.code() == 401) {
                errorMessage.postErrorMenssage("Desculpe! Usuário ou Senha incorretos!");
            } else {
                Log.e("GetDados", "Servidor respondeu com codigo: " + response.code());
                errorMessage.postErrorMenssage("Servidor respondeu com codigo: " + response.code());
            }
        } catch (Exception ex) {
            Log.e("GetDados", "A requisicao falhou!: " + ex);
            errorMessage.postErrorMenssage("A requisicao falhou!");
        }
        return l;
    }

    @Override
    protected void onPostExecute(Login login) {
        //super.onPostExecute(list);
        vrProgresso.dismiss();

        if (loginApiResponse != null) {
            loginApiResponse.postSaida(login);
        } else {
            Log.e("LoginApiResponse", "Problema para Gerar Array List");
        }

    }
}
