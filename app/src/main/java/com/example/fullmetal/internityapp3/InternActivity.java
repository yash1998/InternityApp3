package com.example.fullmetal.internityapp3;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class InternActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayoutIntern;
    private ActionBarDrawerToggle actionBarDrawerToggleIntern;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern);

        drawerLayoutIntern = findViewById(R.id.drawer_intern);
        actionBarDrawerToggleIntern = new ActionBarDrawerToggle(this, drawerLayoutIntern, R.string.open, R.string.close);
        drawerLayoutIntern.addDrawerListener(actionBarDrawerToggleIntern);
        actionBarDrawerToggleIntern.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navbar_intern);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container_intern, new FragmentInternSessions()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.navbar_intern_news:
                transaction.replace(R.id.container_intern, new FragmentInternSessions()).commit();
                break;
            case R.id.navbar_intern_attend:
                transaction.replace(R.id.container_intern, new FragmentInternAttendance()).commit();
                break;
            case R.id.navbar_intern_tasks:
                transaction.replace(R.id.container_intern, new FragmentInternTasks()).commit();
                break;
            case R.id.navbar_intern_about:
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navbar_intern_logut:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawerLayout2 = findViewById(R.id.drawer_intern);
        drawerLayout2.closeDrawer(GravityCompat.START);
        return true;
    }
}
