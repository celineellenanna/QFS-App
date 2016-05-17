package ch.hsr.qfs.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.domain.UserAnswer;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;
import ch.hsr.qfs.service.AuthService;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private FloatingActionButton fab;
    private Boolean fabState = false;

    private AuthService authService = AuthService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new QuizOpponentFragment());
            }
        });

        createDrawer();

        changeFragment(new AuthLoginFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void createDrawer() {
        navigationView = (NavigationView) findViewById(R.id.left_drawer);

        navigationView.setNavigationItemSelectedListener(new NavigationItemListener());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(fabState) {
                    fab.show();
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                fabState = fab.isShown();
                fab.hide();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        updateNavigationList();
    }

    private class NavigationItemListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            if(item.isChecked()) item.setChecked(false);
            else item.setChecked(true);

            drawerLayout.closeDrawers();

            switch (item.getItemId()) {
                case R.id.nav_login:
                    changeFragment(new AuthLoginFragment());
                    return true;
                case R.id.nav_register:
                    changeFragment(new AuthRegisterFragment());
                    return true;
                case R.id.nav_home:
                    changeFragment(new QuizHomeFragment());
                    return true;
                case R.id.nav_category:
                    changeFragment(new QuizCategoryFragment());
                    return true;
                case R.id.nav_statistic:
                    changeFragment(new QuizStatisticFragment());
                    return true;
                case R.id.nav_question:
                    changeFragment(new QuizQuestionFragment());
                    return true;
                case R.id.nav_logout:
                    authService.logout(new ApiHttpCallback<ApiHttpResponse<User>>() {
                        @Override
                        public void onCompletion(ApiHttpResponse<User> response) {
                            removeNavigationViewValues();
                            changeFragment(new AuthLoginFragment());
                            updateNavigationList();
                        }

                        @Override
                        public void onError(String message) {
                            Log.d("QFS - Error", message);
                        }
                    });
                    return true;
                default:
                    Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                    return true;

            }
        }
    }

    public void updateNavigationList() {
        if(authService.isAuthenticated()) {
            navigationView.getMenu().findItem(R.id.nav_home).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);

            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_register).setVisible(false);
        } else {
            navigationView.getMenu().findItem(R.id.nav_home).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);

            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_login).setChecked(true);
            navigationView.getMenu().findItem(R.id.nav_register).setVisible(true);
        }
    }

    public void changeToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void setNavigationViewValues() {
        TextView tvNavViewUsername = (TextView) findViewById(R.id.tvNavViewUsername);
        TextView tvNavViewEmail = (TextView) findViewById(R.id.tvNavViewEmail);
        tvNavViewUsername.setText(authService.getUser().getUsername());
        tvNavViewEmail.setText(authService.getUser().getEmail());
    }

    public void removeNavigationViewValues() {
        TextView tvNavViewUsername = (TextView) findViewById(R.id.tvNavViewUsername);
        TextView tvNavViewEmail = (TextView) findViewById(R.id.tvNavViewEmail);
        tvNavViewUsername.setText("");
        tvNavViewEmail.setText("");
    }


    public void changeFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack(null).commit();
    }

    public void hideFloatingActionButton(Boolean hide) {
        if(hide) {
            fab.hide();
        } else {
            fab.show();
        }
    }

    @Override
    public void onBackPressed() {
    }
}
