package com.byka.humanlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.fragments.SettingsFragment;
import com.byka.humanlibrary.fragments.event.EventListFragment;
import com.byka.humanlibrary.fragments.info.ContactsSupportFragment;
import com.byka.humanlibrary.fragments.info.JoinOrgFragment;
import com.byka.humanlibrary.fragments.info.JoinVolunteerFragment;
import com.byka.humanlibrary.fragments.info.FirstTimeFragment;
import com.byka.humanlibrary.fragments.info.RulesFragment;
import com.byka.humanlibrary.fragments.news.NewsFragment;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_manage);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_placeholder, new EventListFragment()).addToBackStack( "tag" );
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_placeholder, new SettingsFragment()).addToBackStack( "tag" );
            ft.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        FragmentTransaction ft;

        switch (item.getItemId()) {
            case R.id.nav_news:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new NewsFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_events:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new EventListFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_group_guide:
                invertVisibility(R.id.nav_rules);
                invertVisibility(R.id.nav_firstTime);
                return false;
            case R.id.nav_group_contacts:
                invertVisibility(R.id.nav_joinVolunteer);
                invertVisibility(R.id.nav_joinOrg);
                invertVisibility(R.id.nav_support);
                return false;
            case R.id.nav_rules:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new RulesFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_firstTime:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new FirstTimeFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_joinVolunteer:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new JoinVolunteerFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_joinOrg:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new JoinOrgFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_support:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new ContactsSupportFragment()).addToBackStack( "tag" );
                ft.commit();
                break;
            case R.id.nav_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }

        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        item.setChecked(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    private void invertVisibility(int id) {
        this.navigationView.getMenu().findItem(id).setVisible(!this.navigationView.getMenu().findItem(id).isVisible());
    }
}
