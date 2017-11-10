package com.pedidadehoje.validadorandroid.Activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pedidadehoje.validadorandroid.R;


public class ValidationActivity extends AppCompatActivity {


    private Toolbar          toolbar;
    private Button           btn_validar;
    private EditText         last_number;
    private EditText         first_number;
    private Button           btn_conclued;
    private LinearLayout     success_view;
    private ConstraintLayout box_validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);


        btn_validar     = findViewById(R.id.btn_validar);
        success_view    = findViewById(R.id.success_view);
        last_number     = findViewById(R.id.edt_last_number);
        first_number    = findViewById(R.id.edt_first_number);
        btn_conclued    = findViewById(R.id.btn_validate_again);
        box_validation  = findViewById(R.id.box_validation_root);
        toolbar         = findViewById(R.id.action_bar_validation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_conclued.setOnClickListener(click_finish);

        count_number();
    }

    //Retorna a activity anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Inicia o Toobar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    //Verifica o item do Toobar selecionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sign_out) {
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //Envia os numeros digitados
    public View.OnClickListener click_validation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation sliceIn = AnimationUtils.loadAnimation(ValidationActivity.this, R.anim.slide_in);
            success_view.startAnimation(sliceIn);

            success_view.setVisibility(View.VISIBLE);
            box_validation.setVisibility(View.GONE);

        }
    };

    //Retorna para tela de Validação
    public View.OnClickListener click_finish = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            last_number.setText("");
            first_number.setText("");
//            btn_validar.setEnabled(false);
//            btn_validar.setBackgroundColor(getResources().getColor(R.color.background));

            Animation sliceOut = AnimationUtils.loadAnimation(ValidationActivity.this, R.anim.slide_out);
            success_view.startAnimation(sliceOut);
            success_view.setVisibility(View.GONE);

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            box_validation.setVisibility(View.VISIBLE);
                        }
                    }, 300);
        }
    };

    //Verifica se os campos foram corretamente preenchidos
    public void count_number() {
        last_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(last_number.getText().toString().length() == 4 && first_number.getText().toString().length() == 4){
                    btn_validar.setBackgroundColor(getResources().getColor(R.color.background));
                    btn_validar .setOnClickListener(click_validation);
                }
            }
        });
        first_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
