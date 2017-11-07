package com.pedidadehoje.validadorandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {

    private Toolbar      toolbar;
    private LinearLayout choose_box_cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        choose_box_cod = findViewById(R.id.choose_box_cod);
        toolbar = findViewById(R.id.action_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        choose_box_cod.setOnClickListener(click_choose_cod);
    }

    private View.OnClickListener click_choose_cod = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (ChooseActivity.this, ValidationActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sign_out) {
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
