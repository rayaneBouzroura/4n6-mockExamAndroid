package com.rayanebouzroura.exam2023secondshot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rayanebouzroura.exam2023secondshot.databinding.ActivityMainBinding;
import com.rayanebouzroura.exam2023secondshot.retrofit.Retrofit;
import com.rayanebouzroura.exam2023secondshot.retrofit.Service;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //TODO ACTIVITY API 1 : ENABLE BINDING
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Service s = Retrofit.get();
        
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve input
                String input = binding.editTextText.getText().toString();
                //do the api call
                s.getNm(input).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //THIS MEANS SERVER RESPONDED NOW WE TREAT IF RESPONSE IS GOOD OR NAH
                        //IF LA REPONSE IS NICE
                        //TODO : CHECK IF ERROR 404
                        if(response.code() == 404){
                            Toast.makeText(MainActivity.this, "erreur serveur , veillez confirmer avoir entrer un chiffre ", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.isSuccessful()){
                            //we toast the answer
                            //ANSWER IS IN VARIABLE
                            String reponseServeur = null;
                            //Transcribing the body to string needs to be wrapped in a trycatch
                            try {
                                reponseServeur = response.body().string();
                                Toast.makeText(MainActivity.this, reponseServeur, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            //IF RESPONSE NOT SUCCESSFUL RETRIEVED ERROR MSG
                            //STILL NEEDS TO BE WRAPPED IN TRYCATCVH
                            try {
                                String msgError = response.errorBody().string();
                                callSnack(msgError );
                            } catch (IOException e) {
                                callSnack(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }



    //TODO  : isolate snackbar logic in method to test :
    private void callSnack(String msg){
        //recup le contextview

        Snackbar.make(binding.getRoot(),msg,Snackbar.LENGTH_LONG).show();
    }
}