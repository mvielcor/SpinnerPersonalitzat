package pmdm.mviel.navigationdrawler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mviel on 20/7/15.
 */



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // Constants úniques que identifiquen amb quin view del Listview estem treballant.
    // Si el tipus de view és 0, estarem refernint-nos al header (capçalera)
    private static final int TYPE_HEADER = 0;
    // Si el tipus de view és 1, estarem refernint-nos a un ítem de la llista (ListView)
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array per a guardar el textos de les opciones definits en MainActivity.java
    private int mIcons[];       // Int Array per a guardar els identificador de les imatges definides en MainActivity.java

    private String nom;        //String Resource for header View nom
    private int profile;        //int Resource for header view profile picture
    private String curs;       //String Resource for header view email


    // Ens creem una classe interna que hereta de la classe RecyclerView on definirem els camps de cada ítem
    // que anem a re-utilitzar dins el ListView, i incrementar així l'eficiència de la nostra llista

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        //Elements de la llista (ListView)
        TextView textView;
        ImageView imageView;
        //Elements de la capçalera (header)
        ImageView profile;
        TextView nom;
        TextView curs;

        // Constructor de la classe ViewHolder que rep com a paràmetres un View i el tipus de view que és
        public ViewHolder(View itemView,int ViewType) {
            super(itemView);

            // Seleccionem el view adequat segons rebem la capçalera o un ítem de la llista
            if(ViewType == TYPE_ITEM) {  // Hem rebut un ítem de la llista, per tant el buscarem a l'XML fila_nav_drawer
                textView = (TextView) itemView.findViewById(R.id.text_de_fila); // Creem l'objecte TextView a partir del textView del XML fila_nav_drawer.xml
                imageView = (ImageView) itemView.findViewById(R.id.icona_de_fila);// Creem l'objecte ImageView a partir del ImageView del XML fila_nav_drawer.xml
                Holderid = 1;                                               // establim holder id amb el valor 1 per indicar que l'objecte que
                                                                            // estem gestionant és de tipus fila
            }
            else{ // Hem rebut un view que és la CAPÇALERA de la llista.


                nom = (TextView) itemView.findViewById(R.id.usuari);         // Creating Text View object from capcalera_nav_drawer.xml for usuari
                curs = (TextView) itemView.findViewById(R.id.curs);       // Creating Text View object from capcalera_nav_drawer.xml for curs
                profile = (ImageView) itemView.findViewById(R.id.profile_image);// Creating Image view object from capcalera_nav_drawer.xml for profile pic
                Holderid = 0;                                                 // establim holder id amb el valor 1 per indicar que l'objecte que
                                                                              // estem gestionant és de tipus capçalera
            }
        }


    }



    MyAdapter(String Titles[],int Icons[],String Nom,String Curs, int Profile){ // Constructor MyAdapter
       //dades de cada fila
        mNavTitles = Titles;
        mIcons = Icons;
        //dades de la capçalera
        nom = Nom;
        curs = Curs;
        profile = Profile;  //identificador de la imatge de perfil
    }



    //El mètode onCreateViewHolder és cridat quan es crea l'objecte ViewHolder
    //El que farem serà unflar el layout fila_nav.xml quan el tipus de view siga Type_ITEM o
    // unflar capcalera_nav_drawer.xml si el tipus de view és TYPE_HEADER
    //tornarem Un objecte ViewHolder segons el que hagem unflat

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_nav_drawer,parent,false); //unflem fila_nav.xml

            //Li afegim un Listener per a que l'item responga als nostres clicks
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (this!=null){
                        this.onClick(v);
                    }
                }
            });
            ViewHolder vhItem = new ViewHolder(v,viewType); //Creem el ViewHolder i li passem the objecte que hem unflat

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.capcalera_nav_drawer,parent,false); //unflem capcalera_nav_drawer.xml

            ViewHolder vhHeader = new ViewHolder(v,viewType); //Creem el ViewHolder i li passem the objecte que hem unflat

            return vhHeader;


        }
        return null;

    }

    // Aquest mètode, s'executarà cada vegada que necessitem mostrar un ítem.
    //
    // La variable position, ens indica la posició que ocuparà l'item dins la llista
    // L'atribut Holderid, ens indicarà si l'item és la capçalera (Holderid==0), o un element de la llista (Holderid==1)
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
        }
        else{

            holder.profile.setImageResource(profile);
            holder.nom.setText(nom);
            holder.curs.setText(curs);
        }
    }

    // Aquest mètode, ens torna el nombre d'items en la lista
    @Override
    public int getItemCount() {
        return mNavTitles.length+1; // Li afegim 1, ja que hem d'incloure també la capçalera
    }


    // Comprova el tipus de view que estem analitzant
    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return TYPE_HEADER;
        }else {
            return TYPE_ITEM;
        }
    }

}