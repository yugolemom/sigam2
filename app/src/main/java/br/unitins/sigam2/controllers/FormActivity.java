package br.unitins.sigam2.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.unitins.sigam2.R;
import br.unitins.sigam2.interfaces.ErrorMessage;
import br.unitins.sigam2.interfaces.LoginApiResponse;
import br.unitins.sigam2.model.Login;
import br.unitins.sigam2.services.LoginServices;

public class FormActivity extends AppCompatActivity implements LoginApiResponse, ErrorMessage {

    SessionManager manager;
    private Toolbar toolbar;
    private EditText inputLogin, inputPass;
    private TextInputLayout inputLayoutLogin, inputLayoutPassword;
    private Button btnSalvar;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        manager = new SessionManager();

        /*toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Form");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        */

        inputLayoutLogin = (TextInputLayout) findViewById(R.id.input_layout_login);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLogin = (EditText) findViewById(R.id.input_login);
        inputPass = (EditText) findViewById(R.id.input_password);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        inputLogin.addTextChangedListener(new escutaTexto(inputLogin));
        inputPass.addTextChangedListener(new escutaTexto(inputPass));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }


    private void submitForm() {


        String saida;

        if (!validarNomes(inputLogin, inputLayoutPassword)) {
            return;
        }

        if (!validarNomes(inputPass, inputLayoutPassword)) {
            return;
        }
        String login = inputLogin.getText().toString();
        String password = inputPass.getText().toString();

        //String md5Password = md5(password);
        LoginServices services = new LoginServices(FormActivity.this);

        String[] params = {"user", login,
                "pass", password};

        services.setUrl("https://sigam.ifto.edu.br/login");

        services.execute(params);



    }

    private boolean validarNomes(EditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(getString(R.string.error_field_required));
            editText.requestFocus();
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ProximaPage(String numero) {
        Intent intent = new Intent(FormActivity.this, CursoActivity.class);

        intent.putExtra("number", numero);
        startActivity(intent);

        this.finish();
    }

    @Override
    public void postErrorMenssage(final String menssagemError) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FormActivity.this, menssagemError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void postSaida(Login respostaAsync) {

        if (respostaAsync.getCodigo() != null) {

            Toast.makeText(getApplicationContext(), "Bem-Vindo ao Sistema!", Toast.LENGTH_LONG).show();
            number = respostaAsync.getNumber();

            manager.setPreferences(FormActivity.this, "number", number);

            ProximaPage(number);

        }

    }

    private class escutaTexto implements TextWatcher {

        private View view;

        public escutaTexto(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.input_layout_login:
                    validarNomes(inputLogin, inputLayoutLogin);
                    break;
                case R.id.input_layout_password:
                    validarNomes(inputPass, inputLayoutPassword);
                    break;
            }

        }

    }


}
