package dam.pmdm.llistes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Inici extends Activity {
	final String[] dades = new  String[]{"Dilluns","Dimarts","Dimecres","Dijous"};
	private Spinner sp_dies;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.llista);

	//definim els elements de la llista.
		ArrayList<Element> dadesLlista = new ArrayList<Element>();
		dadesLlista.add(new Element(R.drawable.pumpkin_amazed, "Dilluns","Primer dia de la setmana"));
		dadesLlista.add(new Element(R.drawable.pumpkin_angry, "Dimarts","Segon dia de la setmana"));
		dadesLlista.add(new Element(R.drawable.pumpkin_in_love, "Dimecres","Tercer dia de la setmana"));
		dadesLlista.add(new Element(R.drawable.pumpkin_happy, "Dijous","Quart dia de la setmana"));

		//Obtenim l'objecte Spinner de l'xml i li establim com a adaptador, el nostre (AdaptadorLlista)
		sp_dies = (Spinner) findViewById(R.id.sp_diesSetmana);
		sp_dies.setAdapter(new AdaptadorLlista(this, R.layout.element, dadesLlista){

			// Implementem el mètode abstracte onEntrada per a enllaçar les dades amb el disseny que
			// hem fet per a cada element de l'spinner
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {
		            TextView text_sup_entrada = (TextView) view.findViewById(R.id.text_superior); 
		            if (text_sup_entrada != null) 
		            	text_sup_entrada.setText(((Element) entrada).getTextSuperior()); 

		            TextView text_inf_entrada = (TextView) view.findViewById(R.id.text_inferior); 
		            if (text_inf_entrada != null)
		            	text_inf_entrada.setText(((Element) entrada).getTextInferior()); 

		            ImageView imatge_entrada = (ImageView) view.findViewById(R.id.imatge); 
		            if (imatge_entrada != null)
		            	imatge_entrada.setImageResource(((Element) entrada).getIdImatge());
		        }
			}
		});
		
	}
		
		

	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_inici, menu);
		return true;
	}

}
