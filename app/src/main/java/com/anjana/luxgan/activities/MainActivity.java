package com.anjana.luxgan.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.anjana.luxgan.R;
import com.anjana.luxgan.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
NavigationView navigationView;

Fragment homeFragment;
FirebaseAuth auth;

Toolbar toolbar;
Button showMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        showMap = findViewById(R.id.showMap);
//        showMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,MapsActivity.class);
//                startActivity(intent);
//
//            }
//        });

        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);


        homeFragment = new HomeFragment();
        loadFragment(homeFragment);



    }
    
    private void loadFragment (Fragment homefragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homefragment);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_logout) {

            auth.signOut();
            startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            finish();


        } else if (id == R.id.menu_cart) {
          startActivity(new Intent(MainActivity.this,CartActivity.class));
        } else if (id == R.id.menu_rapir) {
        startActivity(new Intent(MainActivity.this,ServiceActivity.class));

        }

        return true;






     }
    }

