package com.teknorial.retrofit2tutorial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.teknorial.retrofit2tutorial.models.Models;
import com.teknorial.retrofit2tutorial.rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//ROOT_URL dari web service
public static final String ROOT_URL = "http://api.teknorial.com/";

//definisi tampilan
private  TextView txt_id;
private  TextView txt_nama;
private  TextView txt_email;
private  TextView txt_alamat;
private  TextView txt_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         txt_id = (TextView) findViewById(R.id.txt_id);
         txt_nama = (TextView) findViewById(R.id.txt_nama);
         txt_email = (TextView) findViewById(R.id.txt_email);
         txt_alamat =(TextView) findViewById(R.id.txt_alamat);
         txt_status = (TextView) findViewById(R.id.txt_status);

        //Memanggil method untuk mengambil data
        getData();

    }
    private void getData(){
        //ketika aplikasi sedang mengambil data kita akan melihat progress dialog muncul
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data","Please wait..",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())//GsonConverter untuk parsing json
                .build();
        RestApi service = retrofit.create(RestApi.class);
        Call<Models> call = service.getDataAdmin();
        call.enqueue(new Callback<Models>() { //Asyncronous Request
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                try {
                    loading.dismiss(); //progress dialog dihentikan

                    //dapatkan hasil parsing dari method response.body()
                    String id = response.body().getAdmin().getId().toString();
                    String nama = response.body().getAdmin().getNama();
                    String email = response.body().getAdmin().getEmail();
                    String alamat = response.body().getAdmin().getAlamat();
                    String status = response.body().getAdmin().getStatus();

                    loading.dismiss();

                    txt_id.setText("ID : "+ id);
                    txt_nama.setText("Nama : "+nama);
                    txt_email.setText("Email : "+email);
                    txt_alamat.setText("Alamat : "+alamat);
                    txt_status.setText("Status : "+status );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Models> call, Throwable t) {

            }

            }
            );


    }
}
