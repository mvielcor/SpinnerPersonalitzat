package pmdm.mviel.navigationdrawermaterialdesign;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;




public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle; // per fer la icona de navegació (icona hamburguesa)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_amb_navigation_view);  //carreguem el layout del Navigation Drawer



        drawerLayout = (DrawerLayout) findViewById(R.id.elDrawerLayout);
        toolbar = (Toolbar)findViewById(R.id.laToolBar);  // Obtenim la toolbar
        setSupportActionBar(toolbar);   // Establim la toolbar

        NavigationView navigationView = (NavigationView) findViewById(R.id.elNavigationView);
        //Li afegim un Listener al Navigation View
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // update highlighted item in the navigation menu
                menuItem.setChecked(true);
                int mNavItemId = menuItem.getItemId();

                // allow some time after closing the drawer before performing real navigation
                // so the user can see what is happening
                drawerLayout.closeDrawer(GravityCompat.START);
                /*drawerActionHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigate(mNavItemId);
                    }
                }, 250);*/

                return true;
            }
        });

        // Creem la icona d'hamburguesa
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer,
                R.string.closeDrawer);
        // Establim el drawerLayout per a qe reaccione quan cliquem l'icona hamburguesa
        drawerLayout.setDrawerListener(mDrawerToggle);
        //Sincronitzem l'estat del navigationDrawer
        mDrawerToggle.syncState();


    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.support.v7.appcompat.R.id.home) {
            return mDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
