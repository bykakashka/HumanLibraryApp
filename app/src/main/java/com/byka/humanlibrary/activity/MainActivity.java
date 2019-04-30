package com.byka.humanlibrary.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.constants.Constants;
import com.byka.humanlibrary.constants.RestConstants;
import com.byka.humanlibrary.fragments.SettingsFragment;
import com.byka.humanlibrary.fragments.event.EventListFragment;
import com.byka.humanlibrary.fragments.login.UserInfoFragment;
import com.byka.humanlibrary.fragments.login.SignInFragment;
import com.byka.humanlibrary.listener.NavigationItemSelectListener;

import org.jetbrains.annotations.NotNull;

import static com.byka.humanlibrary.constants.Constants.StorageConstants.NICKNAME;
import static com.byka.humanlibrary.constants.Constants.StorageConstants.TOKEN;


public class MainActivity extends AppCompatActivity {
    private TextView nickname;

    private View loggedUserInfoView;
    private View loginHereView;

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

        initNavigationView();

        restoreUserInfo();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_placeholder, new EventListFragment()).addToBackStack("tag");
        ft.commit();
    }

    private void initNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectListener(this, navigationView));

        final View headerView = navigationView.getHeaderView(0);

        nickname = headerView.findViewById(R.id.nav_header_username);

        loginHereView = headerView.findViewById(R.id.login_here);
        loggedUserInfoView = headerView.findViewById(R.id.logged_user_info);

        loginHereView.setOnClickListener(fragmentClickListener(new SignInFragment()));
        loggedUserInfoView.setOnClickListener(fragmentClickListener(new UserInfoFragment()));
    }

    @NotNull
    private View.OnClickListener fragmentClickListener(final Fragment fragment) {
        return v -> {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawers();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_placeholder, fragment).addToBackStack("tag");
            ft.commit();
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreUserInfo();
    }

    private void restoreUserInfo() {
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if (isUserLogged()) {
            loginHereView.setVisibility(View.GONE);

            String savedNickname = sharedPref.getString(NICKNAME, null);
            nickname.setText(savedNickname);

            String savedToken = sharedPref.getString(TOKEN, null);
            RestConstants.AUTH_TOKEN = savedToken.trim();

            loggedUserInfoView.setVisibility(View.VISIBLE);
        } else {
            loggedUserInfoView.setVisibility(View.GONE);
            loginHereView.setVisibility(View.VISIBLE);
        }
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
            ft.replace(R.id.content_placeholder, new SettingsFragment()).addToBackStack("tag");
            ft.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isUserLogged() {
        return PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.StorageConstants.IS_LOGGED, false);
    }
}
