package br.unitins.sigam2.controllers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unitins.sigam2.R;
import br.unitins.sigam2.services.GsonServices;

public class ListObject extends AppCompatActivity {

    GsonServices gsonServices = new GsonServices();
    //Teste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_object);
    }

    private void mensagemdeFalha() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ListObject.this, "Falha ao Solicitar Informações!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class getObjectTask extends AsyncTask<Void, Void, List<Object>> {

        private ProgressDialog vrProgresso = null;
        private ListObject listObject = null;

        public getObjectTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();

            vrProgresso = new ProgressDialog(listObject);
            vrProgresso.setCancelable(false);
            vrProgresso.setCanceledOnTouchOutside(false);
            vrProgresso.setMessage("Baixando dados....!");
            vrProgresso.setTitle("Aguarde!");
            vrProgresso.show();


        }

        @Override
        protected void onPostExecute(List<Object> list) {
            //super.onPostExecute(list);

            vrProgresso.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(List<Object> list) {
            super.onCancelled(list);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected List<Object> doInBackground(Void... params) {
           /* List<Object> o = gsonServices.getNetwork();
            if (o.size() > 0)
                return o;

            mensagemdeFalha();
            return new ArrayList<>();*/

            return new ArrayList<>();

        }
    }
}
