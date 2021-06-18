package com.example.rtvote.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.rtvote.R;
import com.example.rtvote.service.ApiClient;
import com.example.rtvote.service.ApiInterface;
import com.example.rtvote.service.Const;
import com.example.rtvote.service.PrefManager;
import com.example.rtvote.service.response.loginwarga.ResponseLoginWarga;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    EditText edNikEmail;
    Button login;
    RadioButton rbEmail,rbNik;
    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        setContentView(R.layout.activity_login);
        edNikEmail = findViewById(R.id.nik_email);
        login = findViewById(R.id.login);
        rbEmail = findViewById(R.id.rb_email);
        rbNik = findViewById(R.id.rb_nik);
        label = findViewById(R.id.label_email_nik);
        rbEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbEmail.isChecked()){
                    label.setVisibility(View.VISIBLE);
                    label.setText("Email :");
                    edNikEmail.setVisibility(View.VISIBLE);
                    edNikEmail.setHint("Masukan email anda");
                    edNikEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                }else{
                    label.setVisibility(View.GONE);
                    edNikEmail.setVisibility(View.GONE);
                    edNikEmail.setHint("");
                    rbEmail.setChecked(false);
                }
            }
        });
        rbNik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbNik.isChecked()){
                    label.setVisibility(View.VISIBLE);
                    label.setText("NIK :");
                    edNikEmail.setVisibility(View.VISIBLE);
                    edNikEmail.setHint("Masukan NIK anda");
                    edNikEmail.setInputType(InputType.TYPE_CLASS_NUMBER);
                }else{
                    label.setVisibility(View.GONE);
                    edNikEmail.setVisibility(View.GONE);
                    edNikEmail.setHint("");
                    rbNik.setChecked(false);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nikEmail = edNikEmail.getText().toString();
                responseLogin(nikEmail);
            }
        });
    }

    private void responseLogin(String nikEmail) {
        Call<ResponseLoginWarga> api = apiInterface.getLoginWarga(nikEmail);
        api.enqueue(new Callback<ResponseLoginWarga>() {
            @Override
            public void onResponse(Call<ResponseLoginWarga> call, Response<ResponseLoginWarga> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getData() != null && response.body().isSuccess()){
                        Log.i("DATA USER",response.body().getData().toString());
                        PrefManager prefManager = new PrefManager(getApplicationContext());
                        prefManager.setInt(Const.ID_USER, response.body().getData().getId());
                        prefManager.setString(Const.NIK, response.body().getData().getNik());
                        prefManager.setString(Const.NAMA, response.body().getData().getNama());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Peringatan")
                                .setContentText("User Tidak Terdaftar \n (Cek kembali Nik dan Nama)")
                                .show();
                    }
                }if (response.code()==500){
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Peringatan")
                            .setContentText("Server bermasalah")
                            .show();

                }
            }

            @Override
            public void onFailure(Call<ResponseLoginWarga> call, Throwable t) {
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Peringatan")
                        .setContentText("GAGAL Login!")
                        .show();
            }
        });
    }
}