/**
 * 
 */
package funciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;

import resources.Constantes.TipoFuncion;
import resources.CustomException;
import stream.O;

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
		Termino term;
		String g = "";
		String s = g;
		toString = g;
		for (iterator = getTerminos().listIterator(); iterator.hasNext();) {
			term = iterator.next();
			
			boolean positiveA = term.getA().signum()==1;
			boolean indexIs0 = iterator.previousIndex()==0;
			g += (indexIs0?"":(positiveA?" + ":" - ")) + term.getGeneric();
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
	 * @param grado
	 * @param coefs
	 * @return una funci�n polin�mica de grado {@code grado} con todos los
	 * t�rminos
	 * @throws CustomException 
	 */
	public static Funcion polinomio(int grado, BigDecimal[] coefs)
			throws CustomException{
		if(coefs.length<=grado) throw CustomException.arrayIncompleto();
		ArrayList<Termino> alT = new ArrayList<Termino>();
		for(int i=0;i<=grado;i++){
			alT.add(Termino.polinomio(i, coefs[i]));
		}
		return new Funcion(alT);
	}
	
	/** @param args */
	public static void main(String[] args) {
		BigDecimal[] coefs = {BigDecimal.ONE.negate(),BigDecimal.ONE.negate(),BigDecimal.ONE};
		javax.swing.JFrame jf = new javax.swing.JFrame();
		javax.swing.JLabel jl = new javax.swing.JLabel();
		Funcion f = null;
		
		try {
			f = polinomio(2, coefs);
		} catch (Exception e) {
			O.pln("err: "+e.getMessage());
		}
		
		jl.setText("<html>"+f.getSpecific()+"<p>"+f.getGeneric()+"<p>"+f+"</html>");
		O.pln(f.valorImagen(BigDecimal.TEN));
		jf.add(jl);
		jf.setVisible(true);
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}
	
}
