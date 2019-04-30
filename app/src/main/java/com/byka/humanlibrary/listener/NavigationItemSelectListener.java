package com.byka.humanlibrary.listener;

import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.constants.Constants;
import com.byka.humanlibrary.fragments.event.EventListFragment;
import com.byka.humanlibrary.fragments.info.ContactsSupportFragment;
import com.byka.humanlibrary.fragments.info.FirstTimeFragment;
import com.byka.humanlibrary.fragments.info.JoinOrgFragment;
import com.byka.humanlibrary.fragments.info.JoinVolunteerFragment;
import com.byka.humanlibrary.fragments.info.RulesFragment;
import com.byka.humanlibrary.fragments.login.UserInfoFragment;
import com.byka.humanlibrary.fragments.login.SignInFragment;
import com.byka.humanlibrary.fragments.news.NewsFragment;

import org.jetbrains.annotations.NotNull;

public class NavigationItemSelectListener implements NavigationView.OnNavigationItemSelectedListener {
    private AppCompatActivity activity;
    private NavigationView navigationView;

    public NavigationItemSelectListener(AppCompatActivity activity, NavigationView navigationView) {
        this.activity = activity;
        this.navigationView = navigationView;
    }

    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        FragmentTransaction ft;

        switch (item.getItemId()) {
            case R.id.nav_news:
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new NewsFragment()).addToBackStack("tag");
                ft.commit();
                break;
            case R.id.nav_events:
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new EventListFragment()).addToBackStack("tag");
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
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new RulesFragment()).addToBackStack("tag");
                ft.commit();
                break;
            case R.id.nav_firstTime:
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new FirstTimeFragment()).addToBackStack("tag");
                ft.commit();
                break;
            case R.id.nav_joinVolunteer:
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new JoinVolunteerFragment()).addToBackStack("tag");
                ft.commit();
                break;
            case R.id.nav_joinOrg:
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new JoinOrgFragment()).addToBackStack("tag");
                ft.commit();
                break;
            case R.id.nav_support:
                ft = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_placeholder, new ContactsSupportFragment()).addToBackStack("tag");
                ft.commit();
                break;
        }

        activity.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        item.setChecked(true);
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void invertVisibility(int id) {
        this.navigationView.getMenu().findItem(id).setVisible(!this.navigationView.getMenu().findItem(id).isVisible());
    }

    private boolean isUserLogged() {
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(Constants.StorageConstants.IS_LOGGED, false);
    }
}
