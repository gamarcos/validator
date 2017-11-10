package com.pedidadehoje.validadorandroid.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.pedidadehoje.validadorandroid.R;

public class ChooseActivity extends AppCompatActivity {

    private Toolbar          toolbar;
    private Button           btn_validar;
    private LinearLayout     success_view;
    private LinearLayout     choose_box_qr;
    private LinearLayout     choose_box_cod;
    private ConstraintLayout choose_view;

    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.pedidadehoje.validadorandroid.R.layout.activity_choose);

        toolbar         = findViewById(R.id.action_bar);
        success_view    = findViewById(R.id.success_view);
        choose_box_qr   = findViewById(R.id.choose_box_qr);
        choose_box_cod  = findViewById(R.id.choose_box_cod);
        choose_view     = findViewById(R.id.choose_box_root);
        btn_validar     = findViewById(R.id.btn_validate_again);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        btn_validar.setOnClickListener(click_finish);
        choose_box_qr.setOnClickListener(click_choose_qr);
        choose_box_cod.setOnClickListener(click_choose_cod);

        verifyPermissions();
    }


    //Carrega o Toobar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    //Verifia qual item do menu foi clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sign_out) {
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //Escolha da forma de valdação
    private View.OnClickListener click_choose_cod = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (ChooseActivity.this, ValidationActivity.class);
            startActivity(intent);
        }
    };

    //Verifica as permições de acesso a camera
    public void verifyPermissions() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
    }

    //Inicia a tela de Scanner
    private  View.OnClickListener click_choose_qr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ChooseActivity.this, ScanActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    //Recebe o resultado do Scanner QR Code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){
                final Barcode barcode = data.getParcelableExtra("barcode");

                Toast.makeText(this, barcode.displayValue, Toast.LENGTH_SHORT).show();

                Animation sliceIn = AnimationUtils.loadAnimation(ChooseActivity.this, R.anim.slide_in);
                success_view.startAnimation(sliceIn);

                success_view.setVisibility(View.VISIBLE);
                choose_view.setVisibility(View.GONE);
            }
        }
    }


    //Fecha a animação
    public View.OnClickListener click_finish = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation sliceOut = AnimationUtils.loadAnimation(ChooseActivity
                    .this, R.anim.slide_out);
            success_view.startAnimation(sliceOut);
            success_view.setVisibility(View.GONE);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            choose_view.setVisibility(View.VISIBLE);
                        }
                    }, 300);
        }
    };



}
