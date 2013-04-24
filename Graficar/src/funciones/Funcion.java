/**
 * 
 */
package funciones;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.ListIterator;

import metodosnumericos.Matriz;
import metodosnumericos.SistemaEcuacionesLineales;

import resources.CustomException;
import resources.O;
import resources.math.Constantes.FuncionTrig;
import resources.math.Constantes.TipoFuncion;
import resources.math.Interval;

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
	public TipoFuncion getTipoFuncion() {
		return tipo;
	}

	/**
	 * @param tipo TipoFuncion 
	 */
	public void setTipoFuncion(TipoFuncion tipo) {
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
		String g = "<html>";
		String s = g;
		toString = "";
		
		for (iterator = getTerminos().listIterator(); iterator.hasNext();) {
			Termino term = iterator.next();
			boolean positiveA = term.getA().signum()==1;
			boolean indexIs0 = iterator.previousIndex()==0;
			g += (indexIs0?"":(positiveA?" + ":" - "))+ term.getGeneric();
			s += (indexIs0?"":(positiveA?" + ":" ")) + term.getSpecific();
			toString += (indexIs0?"":" + ") + term;
		}
		
		this.generic = g+"</html>";
		this.specific = s+"</html>";
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
		this.setTipoFuncion(t.getTipoFuncion());
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

	/**
	 * Crea una funci�n a partir de un conjunto de puntos
	 * @param x puntos
	 * @param fx valor de la funci�n en los puntos x
	 * @return una funci�n polin�mica aproximada a los puntos dados
	 * @throws Exception 
	 */
	public static Funcion aproximacionPolinomialSimple(
			BigDecimal x[], BigDecimal fx[]) throws Exception {
		int numPuntos = x.length;
		if(numPuntos!=fx.length){
			throw CustomException.arrayIncompleto();
		}else{
			BigDecimal matriz[][] = new BigDecimal[numPuntos][numPuntos+1];
			
			for (int i = 0; i < numPuntos; i++) {
				for (int j = 0; j < numPuntos; j++) {
					matriz[i][j] = x[i].pow(j);
				}
				matriz[i][numPuntos] = fx[i];
			}
			
			SistemaEcuacionesLineales sel = new SistemaEcuacionesLineales(matriz);
			Matriz coef = sel.metodoCramer();
			BigDecimal coefs[] = new BigDecimal[numPuntos];
			for (int i = 0; i < coefs.length; i++) {
				coefs[i] = coef.getMatriz()[i][0].stripTrailingZeros();
				System.out.println(coefs[i]);
			}
			
			return Funcion.polinomio(numPuntos-1, coefs);
			
		}
	}
	
	/**
	 * @return la derivada de esta funci�n
	 */
	public Funcion derivada(){
		ArrayList<Termino> alT = new ArrayList<Termino>();
		for (ListIterator<Termino> iterator = getTerminos().listIterator();
				iterator.hasNext();) {
			alT.add(iterator.next().derivada()); 
		}
		return new Funcion(alT);
	}
	
	private int firstNonZeroCoef(){
		for (int i = 0; i < getTerminos().size(); i++) {
			if (getTerminos().get(i).getA().signum()!=0) {
				return i;
			}
		}
		return -1;
	}
	
	private Funcion gx(int fnzc){
		ArrayList<Termino> alT = new ArrayList<Termino>(getTerminos().size()-1);
		switch (fnzc) {
		case 0:		//Si A0 es != 0, ent g(x) = -A0/h(x), h(x)=:
			for (int i = 1; i < getTerminos().size(); i++) {
				Termino t = getTerminos().get(i);
				if (t.getGrado()<=1) {
					alT.add(Termino.constante(t.getA()));
				} else {
					alT.add(Termino.polinomio(t.getGrado()-1, t.getA()));
				}
			}
			return new Funcion(alT);
			
		case -1:	//Esto indica que todos los coeficientes son 0, lo cual nunca debe suceder
			O.pln(-1+" <- wat?");
			return null;
		default:	//Si Ai != 0, ent g(x) =:
			Termino x1 = getTerminos().get(1);
			for (int i = 0; i < getTerminos().size(); i++) {
				if(i!=1){
					Termino t = getTerminos().get(i);
					t.setA(t.getA().divide(x1.getA(), RoundingMode.HALF_UP).negate());
					alT.add(t);
				}
			}
			return new Funcion(alT);
			
		}
	}
	
	/**
	 * @param tol tolerancia del error
	 * @param maxIt m�ximo n�mero de iteraciones
	 * @param x0 punto inicial
	 * @return la ra�z m�s cercana a x0
	 * @throws Exception 
	 */
	public BigDecimal metodoPuntoFijo(BigDecimal tol, int maxIt, BigDecimal x0)
			throws Exception{
		//Obtener g(x)
		int fnzc = firstNonZeroCoef();	//Se localiza el la posici�n del primer coeficiente diferente de 0
		Funcion gx = gx(fnzc);
		
		boolean fin = false;	//Switch
		int k = 0;				//�ndice de la iteraci�n
		BigDecimal xr = BigDecimal.ZERO;
		while((!fin)&&(k<=maxIt)){
			BigDecimal e = xr.subtract(x0).abs();	//Error inicial
			switch (fnzc) {		//xr = g(x0)
			case 0:
				Termino t = getTerminos().get(0);
				xr = t.getA().negate().divide(gx.valorImagen(x0),
						20, RoundingMode.HALF_UP);
				break;
			case -1:
				O.pln(-1+" <- again wat?");
				break;
			default:
				xr = gx.valorImagen(x0);
				break;
			}
			
			if (e.compareTo(tol)<1) {	//Error igual o por debajo de la tolerancia?
				fin = true;
			}
			k++;	//Siguiente iteraci�n
			//Asignaci�n de variables para siguiente iteraci�n
			BigDecimal temp = x0;
			x0 = xr;
			xr = temp;
		}
		
		if (fin) {
			O.pln("x = "+x0);
			return x0;
		} else {
			throw new Exception("No converge dentro del valor m�ximo de iteraci�n");
		}
		
	}
	
	private boolean rootExistentialityCriterion(Interval ab){
		BigDecimal fa = valorImagen(ab.min());
		BigDecimal fb = valorImagen(ab.max());
		BigDecimal ce = fa.multiply(fb);
		switch (ce.signum()) {
		case -1:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * @param tol tolerancia del error
	 * @param maxIt m�ximo n�mero de iteraciones
	 * @param ab intervalo a evaluar
	 * @return la ra�z dentro del intervalo [a,b]
	 * @throws Exception
	 */
	public BigDecimal metodoBiseccion(BigDecimal tol, int maxIt, Interval ab)
			throws Exception {
		if (rootExistentialityCriterion(ab)) {
			boolean fin = false;	//Switch
			int k = 0;				//�ndice de la iteraci�n
			BigDecimal xa = BigDecimal.ZERO;
			while((!fin)&&(k<=maxIt)){
				BigDecimal xm = ab.centre();	//valor medio
				BigDecimal e = xa.subtract(xm).abs();	//Error inicial
				if (e.compareTo(tol)<1) {	//Error igual o por debajo de la tolerancia?
					fin = true;
				}else{
					BigDecimal fxm = valorImagen(xm);
					if (fxm.signum()>0) {
						ab.setMax(xm);
					} else {
						ab.setMin(xm);
					}
				}
				xa = xm;
				k++;
			}
			
			if (fin) {
				O.pln("x = "+xa);
				return xa;
			} else {
				throw new Exception("No converge dentro del valor m�ximo de iteraci�n");
			}
			
		} else {
			throw new Exception("No existe la ra�z dentro del intervalo.");
		}
	}
	
	/**
	 * @param tol tolerancia del error
	 * @param maxIt m�ximo n�mero de iteraciones
	 * @param x0 punto inicial
	 * @return la ra�z m�s cercana a x0
	 * @throws Exception
	 */
	public BigDecimal metodoNewtonRaphson(BigDecimal tol, int maxIt,
			BigDecimal x0) throws Exception {
		boolean fin = false;	//Switch
		int k = 0;				//�ndice de la iteraci�n
		while((!fin)&&(k<=maxIt)){
			BigDecimal fx = valorImagen(x0);
			BigDecimal fpx = derivada().valorImagen(x0);
			BigDecimal fx_fpx = fx.divide(fpx, 20, RoundingMode.HALF_UP);
			BigDecimal xr = x0.subtract(fx_fpx);
			BigDecimal e = x0.subtract(xr).abs();	//Error inicial
			if (e.compareTo(tol)<1) {	//Error igual o por debajo de la tolerancia?
				fin = true;
			}
			BigDecimal temp = x0;
			x0 = xr;
			xr = temp;
			k++;
		}
		
		if (fin) {
			O.pln("x = "+x0);
			return x0;
		} else {
			throw new Exception("No converge dentro del valor m�ximo de iteraci�n");
		}
	}
	
	/**
	 * @param tol tolerancia del error
	 * @param maxIt m�ximo n�mero de iteraciones
	 * @param x0 punto inicial
	 * @param x1 punto secundario
	 * @return la ra�z m�s cercana a x0 y x1
	 * @throws Exception
	 */
	public BigDecimal metodoSecante(BigDecimal tol, int maxIt, BigDecimal x0,
			BigDecimal x1) throws Exception {
		boolean fin = false;	//Switch
		int k = 0;				//�ndice de la iteraci�n
		while((!fin)&&(k<=maxIt)){
			BigDecimal fx0 = valorImagen(x0);
			BigDecimal fx1 = valorImagen(x1);
			BigDecimal x1_x0 = x1.subtract(x0);
			BigDecimal fx1_fx0 = fx1.subtract(fx0);
			BigDecimal fr = x1_x0.multiply(fx1).divide(fx1_fx0, 20, RoundingMode.HALF_UP);
			BigDecimal xr = x1.subtract(fr);
			BigDecimal e = x0.subtract(xr).abs();	//Error inicial
			if (e.compareTo(tol)<1) {	//Error igual o por debajo de la tolerancia?
				fin = true;
			}else{
				x0 = x1;
				x1 = xr;
				k++;
			}
			
		}
		
		if (fin) {
			O.pln("x = "+x0);
			return x0;
		} else {
			throw new Exception("No converge dentro del valor m�ximo de iteraci�n");
		}
	}
	
	/**
	 * @param tol tolerancia del error
	 * @param maxIt m�ximo n�mero de iteraciones
	 * @param ab intervalo a evaluar
	 * @return la ra�z dentro del intervalo [a,b]
	 * @throws Exception
	 */
	public BigDecimal metodoRegulaFalsi(BigDecimal tol, int maxIt, Interval ab)
			throws Exception {
		if (rootExistentialityCriterion(ab)) {
			boolean fin = false;	//Switch
			int k = 0;				//�ndice de la iteraci�n
			BigDecimal xa = BigDecimal.ZERO;
			while((!fin)&&(k<=maxIt)){
				BigDecimal fa = valorImagen(ab.min());
				BigDecimal fb = valorImagen(ab.max());
				BigDecimal fb_fa = fb.subtract(fa);
				BigDecimal fr = ab.length().multiply(fb).divide(fb_fa, 20, RoundingMode.HALF_UP);
				BigDecimal xr = ab.max().subtract(fr);
				BigDecimal e = xa.subtract(xr).abs();	//Error inicial
				if (e.compareTo(tol)<1) {	//Error igual o por debajo de la tolerancia?
					fin = true;
				}else{
					BigDecimal fxr = valorImagen(xr);
					if (fxr.signum()>1) {
						ab.setMax(xr);
					} else {
						ab.setMin(xr);
					}
					xa = xr;
					k++;
				}
				
			}
			
			if (fin) {
				O.pln("x = "+xa);
				return xa;
			} else {
				throw new Exception("No converge dentro del valor m�ximo de iteraci�n");
			}
			
		} else {
			throw new Exception("No existe la ra�z dentro del intervalo.");
		}
		
	}
	
}
