/**
 * 
 */
package funciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;

import resources.Constantes.FuncionTrig;
import resources.Constantes.TipoFuncion;
import resources.CustomException;

/**
 * La clase {@code Funcion} define una funci�n expl�cita de la forma
 * {@code y = F(x)}. 
 * Todas las propiedades de esta funci�n dependen de las propiedades de los
 * {@code t�rminos}. Por lo tanto es posible obtener una funci�n polin�mica de
 * grado n, o una simple funci�n trigonom�trica, exponencial o logar�tmica, o
 * una funci�n con t�rminos combinados.
 * @author Jedabero
 *
 */
public class Funcion{
	
	private ArrayList<Termino> terminos;
	private TipoFuncion tipo;
	
	private String generic;
	private String specific;
	private String toString;
	
	/**
	 * @return el TipoFuncion
	 */
	public TipoFuncion getTipo() {
		return tipo;
	}

	/**
	 * @param tipo TipoFuncion 
	 */
	public void setTipo(TipoFuncion tipo) {
		this.tipo = tipo;
	}

	/**
	 * Regresa la representaci�n general del t�rmino.
	 * @return la representaci�n general
	 */
	public String getGeneric() {
		return generic;
	}

	/**
	 * Regresa la representaci�n espec�fica del t�rmino.
	 * @return la representaci�n espec�fica
	 */
	public String getSpecific() {
		return specific;
	}

	/**
	 * @return lista de t�rminos
	 */
	public ArrayList<Termino> getTerminos() {
		return terminos;
	}

	/**
	 * @param terminos la lista de t�rminos
	 */
	public void setTerminos(ArrayList<Termino> terminos) {
		this.terminos = terminos;
	}
	
	/**
	 * Eval�a y regresa el valor de la funci�n.
	 * @param x el valor de la variable independiente
	 * @return el valor evaluado
	 */
	public BigDecimal valorImagen(BigDecimal x){
		ListIterator<Termino> iterator;
		Termino term;
		BigDecimal y = new BigDecimal(0);
		for (iterator = getTerminos().listIterator(); iterator.hasNext();) {
			term = iterator.next();
			y = y.add(term.valorImagen(x));
		}
		return y;
	}
	
	/** Inicializa la representaci�n espec�fica y general del t�rmino. */
	public void initGenEsp(){
		ListIterator<Termino> iterator;
		String g = "";
		String s = g;
		toString = g;
		
		for (iterator = getTerminos().listIterator(); iterator.hasNext();) {
			Termino term = iterator.next();
			boolean positiveA = term.getA().signum()==1;
			boolean indexIs0 = iterator.previousIndex()==0;
			g += (indexIs0?"":(positiveA?" + ":" - "))+"- "+ term.getGeneric();
			s += (indexIs0?"":(positiveA?" + ":" ")) + term.getSpecific();
			toString += (indexIs0?"":" + ") + term;
		}
		
		this.generic = g;
		this.specific = s;
	}
	
	public String toString(){
		return toString;
	}
	
	/**
	 * @param lt la lista de t�rminos que crea la funci�n
	 * 
	 */
	public Funcion(ArrayList<Termino> lt){
		this.setTerminos(lt);
		initGenEsp();
	}
	
	/**
	 * @param t el t�rmino que crea la funci�n singular
	 * 
	 */
	public Funcion(Termino t){
		ArrayList<Termino> alT = new ArrayList<Termino>();
		alT.add(t);
		this.setTerminos(alT);
		initGenEsp();
	}
	
	/**
	 * Crea un funci�n polin�mica de grado {@code grado} con todos los t�rminos
	 * 
	 * @param n el {@code grado} de la funci�n
	 * @param coefs el array con los coeficientes
	 * @return una funci�n polin�mica de grado {@code n} con todos los
	 * t�rminos
	 * @throws CustomException 
	 */
	public static Funcion polinomio(int n, BigDecimal[] coefs)
			throws CustomException{
		if(coefs.length<=n) throw CustomException.arrayIncompleto();
		ArrayList<Termino> alT = new ArrayList<Termino>();
		alT.add(Termino.constante(coefs[0]));
		for(int i=1;i<=n;i++){
			alT.add(Termino.polinomio(i, coefs[i]));
		}
		return new Funcion(alT);
	}
	
	/**
	 * Crea un funci�n trigonom�trica de tipo {@code ft}
	 * @param ft 
	 * @param coefA 
	 * @param coefB 
	 * @return una funci�n trigonom�trica de tipo {@code ft}
	 */
	public static Funcion trigonometrica(FuncionTrig ft, BigDecimal coefA,
			BigDecimal coefB){
		ArrayList<Termino> alT = new ArrayList<Termino>();
		alT.add(Termino.trigonometrico(ft, coefA, coefB));
		return new Funcion(alT);
	}
	
}
