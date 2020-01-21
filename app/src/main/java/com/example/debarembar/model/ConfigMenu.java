package com.example.debarembar.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.debarembar.R;


public class ConfigMenu extends AppCompatActivity {
    RelativeLayout embacar;
    LinearLayout mainmenu, maincontent;
    Button btnMenu;
    Animation fromtop, frombottom;
    ImageView avatar;
    TextView nomeUser, telefone, tituloSobre, version;
    Button btEdit, btListarBar, btConfig, btSobre, btSair;
    EditText edt_barra_pesquisa;
    ImageView imagemIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_menu);
        // Edit Text
        edt_barra_pesquisa = findViewById(R.id.edt_barra_pesquisa);

        //Button
        btEdit = findViewById(R.id.btEdit);
        btListarBar = findViewById(R.id.btListarBar);
        btConfig = findViewById(R.id.btConfig);
        btSobre = findViewById(R.id.btSobre);
        btSair = findViewById(R.id.btSair);

        //TextView
        nomeUser = findViewById(R.id.nomeUser);
        telefone = findViewById(R.id.telefone);
        tituloSobre = findViewById(R.id.tituloSobre);
        version = findViewById(R.id.version);

        //SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_preferences",MODE_PRIVATE);
        nomeUser.setText(preferences.getString("nome","User Name"));
        telefone.setText(preferences.getString("telefone",""));

        //ImageView
        avatar = findViewById(R.id.avatar);

        //Animações
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        maincontent = findViewById(R.id.linearLayout22);
        mainmenu = findViewById(R.id.mainmenu);
        embacar = findViewById(R.id.embacar);


        imagemIcon = findViewById(R.id.img_filtro);
        imagemIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirMenu();
            }
        });

        //Ao clicar fora da barra ela some
        embacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharMenu();
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSair();
            }
        });
    }
    private void abrirMenu() {
        maincontent.animate().translationX(0);
        mainmenu.animate().translationX(0);
        embacar.setX(0);
        embacar.bringToFront();
        mainmenu.bringToFront();
    }
    private void fecharMenu() {
        mainmenu.animate().translationX(-800);
        embacar.setX(1600);
    }
    private void btnSair(){

        SharedPreferences preferences = getSharedPreferences("user_preferences",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("usuario","");
        edit.apply();

        fecharMenu();

    }
}
