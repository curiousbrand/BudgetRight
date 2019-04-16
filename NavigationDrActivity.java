package com.example.bpear.budgetright;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class NavigationDrActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView catlink;

    private TextView allBills, allDebts, allGoals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationd);
/*


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.screen_area, new UserHomeFragment())
                .commit();



/*
      BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
              = new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {


              switch (item.getItemId()) {
                  case R.id.navigation_home:
                      getSupportFragmentManager().beginTransaction()
                              .add(R.id.fragment_container, new UserHomeFragment())
                              .addToBackStack(null)
                              .commit();
                      return true;
                  case R.id.navigation_bill:
                      getSupportFragmentManager().beginTransaction()
                              .add(R.id.fragment_container, new BillListFragment())
                              .addToBackStack(null)
                              .commit();
                      return true;
                  case R.id.navigation_settings:
                      startActivity(new Intent(NavigationDrActivity.this, SettingsActivity.class));
                      return true;

              }
              return false;
          }

      };
  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here. 

        int id = item.getItemId();


        if (id == R.id.nav_home) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, new UserHomeFragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_Bills) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, new BillListFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Bills");

        } else if (id == R.id.nav_debt) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, new DebtListFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Debt");
        } else if (id == R.id.nav_goals) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, new GoalListFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Goals");


        } else if (id == R.id.nav_calculator) {

            startActivity(new Intent(NavigationDrActivity.this, Calculator.class));

        } else if (id == R.id.nav_calendar) {

            startActivity(new Intent(NavigationDrActivity.this, CalanderView.class));

        } else if (id == R.id.nav_settings) {

            startActivity(new Intent(NavigationDrActivity.this, SettingsActivity.class));

        } else if (id == R.id.nav_signout) {

            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, LoginPage.class));

        } else if (id == R.id.nav_Credit) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, new CardFrontFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Credit Card");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

