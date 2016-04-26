package ch.hsr.qfs.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.hsr.qfs.R;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;
import ch.hsr.qfs.service.AuthService;

public class MainActivity extends AppCompatActivity {

    private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerToggle;

    private AuthService authService = AuthService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createDrawer();

        changeFragment(new AuthLoginFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void createDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawerListViewItems = getResources().getStringArray(R.array.drawer_items_array);

        drawerListView = (ListView) findViewById(R.id.left_drawer);

        updateDrawerList();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void updateDrawerList() {
        if(authService.isAuthenticated()) {
            drawerListViewItems = getResources().getStringArray(R.array.drawer_auth_items_array);
            drawerListView.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_row_layout, drawerListViewItems));
        } else {
            drawerListViewItems = getResources().getStringArray(R.array.drawer_items_array);
            drawerListView.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_row_layout, drawerListViewItems));
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            if(authService.isAuthenticated()) {
                switch (((TextView)view).getText().toString()) {
                    case "Home":
                        changeFragment(new QuizHomeFragment());
                        break;
                    case "Quiz starten":
                        changeFragment(new QuizOpponentFragment());
                        break;
                    case "Abmelden":
                        authService.logout(new ApiHttpCallback<ApiHttpResponse<User>>() {
                            @Override
                            public void onCompletion(ApiHttpResponse<User> response) {
                                changeFragment(new AuthLoginFragment());
                                updateDrawerList();
                            }

                            @Override
                            public void onError(String message) {
                                Log.d("QFS - Error", message);
                            }
                        });
                        break;
                }
            } else {
                switch (((TextView)view).getText().toString()) {
                    case "Einloggen":
                        changeFragment(new AuthLoginFragment());
                        break;
                    case "Registrieren":
                        changeFragment(new AuthRegisterFragment());
                        break;
                }
            }

            drawerLayout.closeDrawer(drawerListView);
        }
    }

    public void changeFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack(null).commit();
    }

}
