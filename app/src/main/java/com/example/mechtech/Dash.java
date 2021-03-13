package com.example.mechtech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dash extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    /*  FusedLocationProviderClient fusedLocationProviderClient;
      Button btnLocation;*/
    TextView textView1, textView2, textView3, textView4, textView5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //btnLocation = findViewById(R.id.button_location);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

            //Initialise and Assign Variables
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            //Set Home Selected
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new DashboardFragment()).commit();
            bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.home:
                    selectedFragment = new DashboardFragment();
                    break;
                case R.id.notification:
                    selectedFragment = new NotificationFragment();
                    break;
                case R.id.setting:
                    selectedFragment = new SettingFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new UserProfileFrament();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new DashboardFragment()).commit();
                break;
          /*  case R.id.nav_service_stations:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new ServiceStationFragment()).commit();
                break;*/
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new UserProfileFrament()).commit();
                break;
            case R.id.nav_rate_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new RatingFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Sorry!! The App is not in PlayStore Currently.", Toast.LENGTH_SHORT).show();
             /*   Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));*/
                break;
            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new FeedbackFragment()).commit();
                break;
            case R.id.nav_contact_us:

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:0729105112"));
                    startActivity(intent);
                } else {
                    //when permission denied
                    ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.CALL_PHONE}, 44);
                }
                break;
            case R.id.nav_sign_out:
                logout();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}