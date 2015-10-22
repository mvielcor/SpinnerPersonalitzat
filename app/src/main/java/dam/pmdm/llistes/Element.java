package dam.pmdm.llistes;

//Classe que conté totes les dades necessàries per a gestionar la informació d'un Element del Spinner
public class Element {
	private int idImatge;  //guarda una referència a una imatge
	private String textSuperior;  // Títol
	private String textInferior;   // Subtítol

	public Element(int idIm, String txt1, String txt2) {
		this.idImatge = idIm;
		this.textSuperior = txt1;
		this.textInferior = txt2;
	}

	public String getTextSuperior() {
		return textSuperior;
	}

	public String getTextInferior() {
		return textInferior;
	}

	public int getIdImatge() {
		return idImatge;
	}

	// També es poden afegir els setters!!

}
