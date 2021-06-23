package com.example.testall;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.widget.ImageView;

import androidx.navigation.ui.AppBarConfiguration;


public class collassingactivity extends AppCompatActivity {
    private Menu menu;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailspage);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();



        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        CollapsingToolbarLayout cc= (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        ImageView imghead= (ImageView) findViewById(R.id.expandedImage);




        cc.setTitleEnabled(true);
        if(b!=null)
        {
            String j =(String) b.get("title");
            cc.setTitle(j);
            Glide.with(this).load(b.get("imageurl")).into(imghead);
        }

    }


}