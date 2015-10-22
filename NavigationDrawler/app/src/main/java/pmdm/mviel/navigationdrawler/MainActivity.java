package pmdm.mviel.navigationdrawler;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

        //Declarem els textos del ListView per a mostrar-los en un Navigation Drawer
        //Els textos i les icones a mostrar ho guardem en un array, per a construir el ListView des
        // d'un ArrayAdapter (ja que és la manera més ràpida de crear un ListView)

        String TITLES[] = {"Inici","Events","Mail","Botiga","Escola","Inici","Events","Mail","Botiga","Escola"};
        int ICONS[] = {R.mipmap.ic_home_black_24dp,R.mipmap.ic_event_black_24dp,R.mipmap.ic_mail_black_24dp,
                R.mipmap.ic_shopping_cart_black_24dp,R.mipmap.ic_school_black_24dp,R.mipmap.ic_home_black_24dp,
                R.mipmap.ic_event_black_24dp,R.mipmap.ic_mail_black_24dp,R.mipmap.ic_shopping_cart_black_24dp,
                R.mipmap.ic_school_black_24dp};

        // En una CONSTANT de tipus String guardem el nom de l'usuari i en altre String el curs,
        // que apareixeran en el header view i amb un enter referenciem la imatge del perfil que
        // mostrarem al header view

        final String NAME = "Minion";
        final String CURS = "2n DAM (PMDM)";
        final int PROFILE = R.drawable.minions;

        private Toolbar toolbar;                              // Declarem l'Objecte Toolbar

        RecyclerView mRecyclerView;                           // Declarem RecyclerView
        RecyclerView.Adapter mAdapter;                        // Declarem Adapter For Recycler View
        RecyclerView.LayoutManager mLayoutManager;            // Declarem Layout Manager as a linear layout manager
        DrawerLayout Drawer;                                  // Declarem DrawerLayout

        ActionBarDrawerToggle mDrawerToggle;                  // Declarem la icona d'hamburguesa




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

         /* Assinging the toolbar object ot the view
             and setting the the Action bar to our toolbar
         */
            toolbar = (Toolbar) findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);




            mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assignem l'Objecte RecyclerView al View de L'xml

            mRecyclerView.setHasFixedSize(true);                            // Indiquem que la llista d'objectes és de tamany fixe
            mAdapter = new MyAdapter(TITLES,ICONS,NAME,CURS,PROFILE);      // Enviem a l'Adaptador TOTA la informació necessària per a crear el ListView
            // Afegim un Listener per a que reaccione a la nostra selecció

            return;.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Mostrem un Toast amb el text i la posició que ocupa l'ítem selecionat dins del gridview
                    Toast.makeText(getApplicationContext()," (" + mRecyclerView.getChildPosition(v) + ")", Toast.LENGTH_SHORT).show();

                }
            });
            mRecyclerView.setAdapter(mAdapter);                              // Enllacem l'adaptador al RecyclerView

            mLayoutManager = new LinearLayoutManager(this);                 // Creem el layout Manager

            mRecyclerView.setLayoutManager(mLayoutManager);                 // Enllacem el layoutManager amb el RecyclerView


            Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Assignem l'Objecte DrawerLayout al View de L'xml

            //Creem l'icona de navagacií (icona hamburguesa)
            mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                    // open I am not going to put anything here)

                    //Per fer una prova, li direm que pinte l'ActionBar de color ROIG
                    toolbar.setBackgroundColor(Color.RED);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    // Code here will execute once drawer is closed

                    //Per fer una prova, li direm que pinte l'ActionBar del color que estava abans
                    toolbar.setBackgroundColor(getResources().getColor(R.color.ColorPrincipal));
                }



            };

            Drawer.setDrawerListener(mDrawerToggle); // Fem que l'icona hamburguesa reaccione als events mostrant/ocultant el navigationDrawer
            mDrawerToggle.syncState();               // Sincronitzem



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
            if (id==android.support.v7.appcompat.R.id.home){ //altra manera d'especificar l'icona hamburguesa
                return true;
            }
            return super.onOptionsItemSelected(item);
        }



}
