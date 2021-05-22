package com.srikanth.cinemareview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);

        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
           new  HomeFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment= null;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment=new HomeFragment();
                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_explore:
                    Toast.makeText(HomeActivity.this, "Explore", Toast.LENGTH_SHORT).show();
                  selectedFragment=new ExploreFragment();
                    break;
                case R.id.nav_profile:
                    Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    selectedFragment=new ProfileFragment();
                    break;
            }
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                   selectedFragment).commit();
            return true;
        }
    };
}