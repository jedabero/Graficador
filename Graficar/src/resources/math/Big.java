package resources.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import resources.O;


/**
 * 
 */

/**
 * @author <a href="https://twitter.com/Jedabero" target="_blank">Jedabero</a>
 *
 */
public final class Big {
	
	/**
	 * A constant holding the largest positive finite value of type double as a
	 * BigDecimal
	 */
	public static final BigDecimal DOUBLE_MAX = BigDecimal.valueOf(Double.MAX_VALUE);
	
	/**
	 * A constant holding the largest positive finite value of type double as a
	 * BigDecimal
	 */
	public static final BigDecimal INT_MAX = BigDecimal.valueOf(Integer.MAX_VALUE);
	/**
	 * The <code>double</code> value that is closer than any other to
     * <i>pi</i>, the ratio of the circumference of a circle to its
     * diameter.
	 */
	public static final BigDecimal PI =
			new BigDecimal(Double.toString(Math.PI));
	
	/**
	 * Half of {@link Big#PI}
	 */
	public static final BigDecimal PI_2 =
			PI.divide(new BigDecimal(Integer.toString(2)));
	
	/**
	 * Double of {@link Big#PI}
	 */
	public static final BigDecimal TAU =
			PI.multiply(new BigDecimal(Integer.toString(2)));
	
	/**
	 * Convierte el n�mero al entero m�s cercano, dependiendo si es un m�ximo o no.
	 * @param	bd	n�mero a convertir.
	 * @param	isMax	si es un m�ximo debe ser true, false para un m�nimo.
	 * @return	El n�mero convertido en m�ximo
	 */
	public static BigDecimal compareToInt(BigDecimal bd, boolean isMax){
		int bdToInt = bd.intValue();	//Se convierte a entero.
		//Se revierte a BigDecimal para comparaci�n.
		BigDecimal intBD = BigDecimal.valueOf(bdToInt);
		int cR = bd.compareTo(intBD);	//Se guarda el resultado de la comparaci�n.
		switch(cR){
		case 0:	//Se deja igual.
			break;
		default://Para los otros casos (-1 y 1), se hace el cambio a entero
				//dependiendo si es m�ximo o es m�nimo.
			if(isMax){
				bd = intBD.add(BigDecimal.ONE);
			}else if(!isMax){
				bd = intBD.subtract(BigDecimal.ONE);
			}
			break;
		}
		return bd;
	}//Fin de compareToInt
	
	/**
	 * Da un valor aleatorio entre -1 y 1
	 * @return	un entero que puede ser -1, 0 o 1
	 */
	public static int randomSign(){
		int toInt = (int)(2 - 4*Math.random());
		return toInt==0 ? 1 : toInt;
	}
	
	/**
	 * @param v
	 * @return suma de los elementos del vector
	 */
	public static BigDecimal suma(BigDecimal v[]) {
		BigDecimal suma = BigDecimal.ZERO;
		for (int i = 0; i < v.length; i++) {
			suma = suma.add(v[i]);
		}
		return suma;
	}
	
	/**
	 * Regresa el valor m�ximo de un array de BigDecimal
	 * @param	a el array a evaluar
	 * @return	el valor m�ximo dentro de {@code a}.
	 */
	public static BigDecimal max(BigDecimal a[]){
		BigDecimal max = a[0];
		for(BigDecimal i: a){
			if((max.compareTo(i))==-1) max = i;
		}
		return max;
	}
	
	/**
	 * Regresa el valor m�nimo de un array de BigDecimal
	 * @param	a el array a evaluar
	 * @return	el valor m�nimo dentro de {@code a}.
	 */
	public static BigDecimal min(BigDecimal a[]){
		BigDecimal min = a[0];
		for(BigDecimal i: a){
			if((min.compareTo(i))==1) min = i;
		}
		return min;
	}
	
	/**
	 * Regresa el valor m�ximo de un array de int
	 * @param	a el array a evaluar
	 * @return	el valor m�ximo dentro de {@code a}.
	 */
	public static int max(int a[]){
		int max = a[0];
		for(int i: a){
			if(max<=i) max = i;
		}
		return max;
	}
	
	/**
	 * Regresa el valor m�nimo de un array de int
	 * @param	a el array a evaluar
	 * @return	el valor m�nimo dentro de {@code a}.
	 */
	public static int min(int a[]){
		int min = a[0];
		for(int i: a){
			if(min>=i) min = i;
		}
		return min;
	}
	
	/**
	 * Calcula el seno trigonom�trico del �ngulo
	 * @see		Math#sin(double)
	 * @param	a el �ngulo en radianes
	 * @return	el seno de {@code a}
	 */
	public static BigDecimal sin(BigDecimal a) {
		BigDecimal reducedA = a.remainder(TAU);
		double redA = reducedA.doubleValue();
		BigDecimal sin = new BigDecimal(Double.toString(Math.sin(redA)));
		return sin.stripTrailingZeros();
	}
	
	/**
	 * Calcula el coseno trigonom�trico del �ngulo
	 * @see		Math#cos(double)
	 * @param	a el �ngulo en radianes
	 * @return	el coseno de {@code a}
	 */
	public static BigDecimal cos(BigDecimal a) {
		BigDecimal reducedA = a.remainder(TAU);
		double redA = reducedA.doubleValue();
		BigDecimal cos = new BigDecimal(Double.toString(Math.cos(redA)));
		return cos.stripTrailingZeros();
	}
	
	/**
	 * Calcula la tangente trigonom�trico del �ngulo
	 * @see		Math#tan(double)
	 * @param	a el �ngulo en radianes
	 * @return	la tangente de {@code a}
	 */
	public static BigDecimal tan(BigDecimal a) {
		BigDecimal reducedA = a.remainder(TAU);
		double redA = reducedA.doubleValue();
		BigDecimal tan = new BigDecimal(Double.toString(Math.tan(redA)));
		return tan.stripTrailingZeros();
	}
	
	/**
	 * Calcula la secante trigonom�trica del �ngulo
	 * @param	a el �ngulo en radianes
	 * @return	la secante de {@code a}
	 */
	public static BigDecimal sec(BigDecimal a) {
		
		try{
			return BigDecimal.ONE.divide(cos(a), 16, RoundingMode.HALF_UP).stripTrailingZeros();
		}catch(ArithmeticException ae){
			O.pln("sec("+a+") = 1"+ae.getMessage());
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Calcula la cosecante trigonom�trica del �ngulo
	 * @param	a el �ngulo en radianes
	 * @return	la cosecante de {@code a}
	 */
	public static BigDecimal csc(BigDecimal a) {
		try{
			return BigDecimal.ONE.divide(sin(a), 16, RoundingMode.HALF_UP).stripTrailingZeros();
		}catch(ArithmeticException ae){
			O.pln("csc("+a+") = 1"+ae.getMessage());
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Calcula la cotangente trigonom�trico del �ngulo
	 * @param	a el �ngulo en radianes
	 * @return	la cotangente de {@code a}
	 */
	public static BigDecimal cot(BigDecimal a) {
		try{
			return BigDecimal.ONE.divide(tan(a), 16, RoundingMode.HALF_UP);
		}catch(ArithmeticException ae){
			O.pln("cot("+a+") = 1"+ae.getMessage());
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Calcula el seno trigonom�trico del �ngulo
	 * @see		Math#sin(double)
	 * @param	a el �ngulo en radianes
	 * @return	el seno de {@code a}
	 */
	public static BigDecimal asin(BigDecimal a) {
		double aD = a.doubleValue();
		BigDecimal asin = new BigDecimal(Double.toString(Math.asin(aD)));
		return asin.stripTrailingZeros();
	}
	
	/**
	 * Calcula el coseno trigonom�trico del �ngulo
	 * @see		Math#cos(double)
	 * @param	a el �ngulo en radianes
	 * @return	el coseno de {@code a}
	 */
	public static BigDecimal acos(BigDecimal a) {
		double aD = a.doubleValue();
		BigDecimal acos = new BigDecimal(Double.toString(Math.acos(aD)));
		return acos.stripTrailingZeros();
	}
	
	/**
	 * Calcula la tangente trigonom�trico del �ngulo
	 * @see		Math#tan(double)
	 * @param	a el �ngulo en radianes
	 * @return	la tangente de {@code a}
	 */
	public static BigDecimal atan(BigDecimal a) {
		double aD = a.doubleValue();
		BigDecimal atan = new BigDecimal(Double.toString(Math.atan(aD)));
		return atan.stripTrailingZeros();
	}
	
	/**
	 * Calcula la secante trigonom�trica del �ngulo
	 * @param	a el �ngulo en radianes
	 * @return	la secante de {@code a}
	 */
	public static BigDecimal asec(BigDecimal a) {//TODO asec
		
		try{
			BigDecimal x = BigDecimal.ONE.divide(a, 16, RoundingMode.HALF_UP).stripTrailingZeros();
			return acos(x);
		}catch(ArithmeticException ae){
			O.pln("asec("+a+") = "+ae.getMessage());
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Calcula la cosecante trigonom�trica del �ngulo
	 * @param	a el �ngulo en radianes
	 * @return	la cosecante de {@code a}
	 */
	public static BigDecimal acsc(BigDecimal a) {//TODO acsc
		try{
			BigDecimal x = BigDecimal.ONE.divide(a, 16, RoundingMode.HALF_UP).stripTrailingZeros();
			return asin(x);
		}catch(ArithmeticException ae){
			O.pln("acsc("+a+") = "+ae.getMessage());
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Calcula la cotangente trigonom�trico del �ngulo
	 * @param	a el �ngulo en radianes
	 * @return	la cotangente de {@code a}
	 */
	public static BigDecimal acot(BigDecimal a) {//TODO acot
		try{
			BigDecimal x = BigDecimal.ONE.divide(a, 16, RoundingMode.HALF_UP);
			return atan(x);
		}catch(ArithmeticException ae){
			O.pln("acot("+a+") = "+ae.getMessage());
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * @see		Math#toRadians(double)
	 * @param	a el �ngulo en grados
	 * @return	el �ngulo {@code a} en radianes 
	 */
	public static BigDecimal toRadians(BigDecimal a){
		return BigDecimal.valueOf(Math.toRadians(a.doubleValue()));
	}
	
	/**
	 * @see		Math#toDegrees(double)
	 * @param	a el �ngulo en radianes
	 * @return	el �ngulo {@code a} en grados 
	 */
	public static BigDecimal toDegrees(BigDecimal a){
		return BigDecimal.valueOf(Math.toDegrees(a.doubleValue()));
	}
	
	/**
	 * Potenciaci�n la base elevada al exp.
	 * @param base la base
	 * @param exp el exponente
	 * @return base<sup>exp</sup>
	 */
	public static BigDecimal pow(BigDecimal base, BigDecimal exp){
		double b = base.doubleValue();
		double x = exp.doubleValue();
		if(Double.isInfinite(Math.pow(b, x))){
			BigDecimal n = logB(DOUBLE_MAX, base);
			BigDecimal x2 = exp.subtract(n);
			BigDecimal expX2 = pow(base, x2);
			return pow(base, n).multiply(expX2);
		}else{
			BigDecimal ex = new BigDecimal(Double.toString(Math.pow(b,x)));
			return (ex.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa el logaritmo de n en base base 
	 * @param base la base
	 * @param x el n�mero
	 * @return regresa log<sub>b</sub>x
	 */
	public static BigDecimal logB(BigDecimal x, BigDecimal base){
		BigDecimal num = ln(x);
		BigDecimal den = ln(base);
		BigDecimal res = num.divide(den, 10, RoundingMode.HALF_EVEN).stripTrailingZeros();
		return res;
	}
	
	/**
	 * Regresa el numero de Euler elevado a la potencia del valor del {@code BigDecimal}
	 * @see Math#exp(double)
	 * @param	x el exponente a elevar {@code e}.
	 * @return e<sup>x</sup>, en el que e es la base de los logaritmos naturales
	 */
	public static BigDecimal exp(BigDecimal x){
		if(Double.isInfinite(Math.exp(x.doubleValue()))){
			BigDecimal n = ln(DOUBLE_MAX);
			BigDecimal x2 = x.subtract(n);
			BigDecimal expX2 = exp(x2);
			return exp(n).multiply(expX2);
		}else{
			double aD = x.doubleValue();
			BigDecimal exp = new BigDecimal(Double.toString(Math.exp(aD)));
			return (exp.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa el logaritmo natural (base {@code e}) del valor del {@code BigDecimal}
	 * @see Math#log(double)
	 * @param	x un valor
	 * @return ln {@code x}, logaritmo natural de {@code x}
	 */
	public static BigDecimal ln(BigDecimal x){
		if(x.signum()<=0){
			O.pln("ln("+x+") = -Infinity");
			return BigDecimal.ZERO;
		}else if(Double.isInfinite(x.doubleValue())){
			BigDecimal n = x.divide(DOUBLE_MAX);
			return (ln(n).add(ln(DOUBLE_MAX)));
		}else{
			double l = Math.log(x.doubleValue());
			BigDecimal log = new BigDecimal(Double.toString(l));
			return (log.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa la ra�z cuadrada positiva de a
	 * @param a el n�mero
	 * @return la ra�z cuadrada positiva de a
	 * @throws Exception 
	 */
	public static BigDecimal sqrt(double a) throws Exception{
		if(Double.isInfinite(a)){
			throw new Exception("N�mero infinito");
		}else if(Double.isNaN(a)){
			throw new Exception("N�mero indeterminado");
		}else if(a<0){
			throw new Exception("N�mero negativo");
		}else{
			BigDecimal sqrt = new BigDecimal(Double.toString(Math.sqrt(a)));
			return (sqrt.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa la ra�z cuadrada positiva de a
	 * @param a el n�mero
	 * @return la ra�z cuadrada positiva de a
	 * @throws Exception 
	 */
	public static BigDecimal sqrt(BigDecimal a) throws Exception{
		if(a.signum()==-1){
			throw new Exception("N�mero negativo");
		}else if(Double.isInfinite(a.doubleValue())){
			BigDecimal n = a.divide(DOUBLE_MAX);
			return (sqrt(n).multiply(sqrt(DOUBLE_MAX)));
		}else{
			double aD = a.doubleValue();
			BigDecimal sqrt = new BigDecimal(Double.toString(Math.sqrt(aD)));
			return (sqrt.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa la ra�z cubica positiva de a
	 * @param a el n�mero
	 * @return la ra�z cubica positiva de a
	 * @throws Exception 
	 */
	public static BigDecimal cbrt(double a) throws Exception{
		if(Double.isInfinite(a)){
			throw new Exception("N�mero infinito");
		}else if(Double.isNaN(a)){
			throw new Exception("N�mero indeterminado");
		}else{
			BigDecimal cbrt = new BigDecimal(Double.toString(Math.cbrt(a)));
			return (cbrt.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa la ra�z cubica positiva de a
	 * @param a el n�mero
	 * @return la ra�z cubica positiva de a
	 * @throws Exception 
	 */
	public static BigDecimal cbrt(BigDecimal a) throws Exception{
		if(Double.isInfinite(a.doubleValue())){
			BigDecimal n = a.divide(DOUBLE_MAX);
			return (cbrt(n).multiply(cbrt(DOUBLE_MAX)));
		}else{
			double aD = a.doubleValue();
			BigDecimal cbrt = new BigDecimal(Double.toString(Math.cbrt(aD)));
			return (cbrt.stripTrailingZeros());
		}
	}
	
	/**
	 * Regresa el factorial del n�mero n
	 * @param n el n�mero
	 * @return n! el factorial de n en BigDecimal
	 * @throws Exception si n est� fuera del rango 0 < n < 1676
	 */
	public static BigDecimal BigFactorial(int n) throws Exception{
		BigDecimal a = BigDecimal.ONE;
		if(n<0){
			throw new Exception("No hay factorial para enteros negativos");
		}else if(n>1676){
			throw new Exception("Factorial de "+n+" es muy grande.");
		}else{
			for(int i=1;i<n+1;i++) a = a.multiply(new BigDecimal(""+i));
		}
		
		return a;
	}
	
	/**
	 * Regresa el factorial del n�mero n
	 * @param n el n�mero
	 * @return n! el factorial de n en entero
	 * @throws Exception si n est� fuera del rango 0 < n < 20
	 */
	public static long factorial(int n) throws Exception{
		long a = 1L;
		if(n<0){
			throw new Exception("No hay factorial para enteros negativos");
		}else if(n>20){
			throw new Exception("Factorial de "+n+" es muy grande: > " +
					Long.MAX_VALUE+". Utilice BigFactorial");
		}else{
			for(int i=1;i<n+1;i++) a *= i;
		}
		
		return a;
	}
	
	/**
	 * 
	 * @param n
	 * @param k
	 * @return nCk
	 * @throws Exception 
	 */
	public static BigDecimal combinatoria(int n, int k) throws Exception{
		if(n<k){
			throw new Exception(n+"<"+k);
		}else{
			BigDecimal fn = BigFactorial(n);
			BigDecimal fk = BigFactorial(k);
			int n_k = n - k;
			BigDecimal fn_k = BigFactorial(n_k);
			return fn.divide(fk.multiply(fn_k));
		}
	}
	
	/**
	 * @param v
	 * @param k
	 * @return TODO return
	 * @throws Exception
	 */
	public static BigDecimal[] listaCombinaciones(BigDecimal v[], int k) throws Exception{
		BigDecimal numEl = BigDecimal.ZERO;
		try {
			numEl = combinatoria(v.length, k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (numEl.compareTo(INT_MAX)==1) {
			throw new Exception("DEMASIADAS COMBINACIONES");
		}
		
		ArrayList<BigDecimal> temp = new ArrayList<BigDecimal>();
		
		int poss[] = new int[k];
		poss[0] = 0;
		if (k>1) {
			for (int i = 1; i < k; i++) {
				poss[i] = i;
			}
		}
		
		addCombinaciones(poss, k-1, k, v, temp);
		
		return (BigDecimal[]) temp.toArray(new BigDecimal[0]);
	}
	
	private static void addCombinaciones(int[] poss, int posACambiar, int k,
			BigDecimal[] v, ArrayList<BigDecimal> temp) {
		int numElem = v.length;
		
		BigDecimal prod = BigDecimal.ONE;
		for (int i = 0; i < poss.length; i++) {
			prod = prod.multiply(v[poss[i]]);
		}
		temp.add(prod);
		
		poss[posACambiar]++;
		
		if (poss[posACambiar]<numElem) { 
			addCombinaciones(poss, posACambiar, k, v, temp);
		} else {
			int prevPosACambiar = posACambiar - 1;
			
			if (prevPosACambiar>=0) {
				poss[prevPosACambiar]++;
				poss[posACambiar] = poss[posACambiar - 1] +1;
				
				if (poss[prevPosACambiar]<(numElem-1)) { 
					addCombinaciones(poss, posACambiar, k, v, temp);
				} else {
					boolean exit = false;
					if (prevPosACambiar!=0) {
						while((poss[prevPosACambiar]>=numElem-1)||(exit&&(prevPosACambiar>0))){
							prevPosACambiar--;
							poss[prevPosACambiar]++;
							for (int i = prevPosACambiar+1; i < k; i++) {
								poss[i] = poss[i-1] +1;
								exit = (poss[i]==numElem);
							}
						}
						if (!exit) {
							addCombinaciones(poss, posACambiar, k, v, temp);
						}
					}
				}
				
			}
			
		}
		
	}
	
	/**
	 * @param v
	 * @param k
	 * @return TODO return
	 * @throws Exception 
	 */
	public static BigDecimal sumaCombinaciones(BigDecimal v[], int k) throws Exception{
		BigDecimal t[] = listaCombinaciones(v, k);
		return suma(t);
	}
	
	
	/**
	 * @param n
	 * @param k
	 * @return nPk
	 * @throws Exception
	 */
	public static BigDecimal permutacion(int n, int k) throws Exception{
		if(n<k){
			throw new Exception(n+"<"+k);
		}else{
			BigDecimal fn = BigFactorial(n);
			int n_k = n - k;
			BigDecimal fn_k = BigFactorial(n_k);
			return fn.divide(fn_k);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		try {
			BigDecimal jua = new BigDecimal(Double.toString(8)).stripTrailingZeros();
			BigDecimal cho = new BigDecimal(Double.toString(2)).stripTrailingZeros();
			
			BigDecimal bdV[] = {jua, cho, BigDecimal.ONE, BigDecimal.TEN.negate()};
			
			funciones.Funcion f = funciones.Funcion.polinomioProductorio(bdV);
			
			//System.out.println("4C1: "+sumaCombinaciones(bdV, 1));
			//BigDecimal c[] = listaCombinaciones(bdV, 1);
			//for (int i = 0; i < c.length; i++) {
				//System.out.println(c[i]);
			//}
			
			System.out.println(f.getSpecific());
			/*
			O.pln("pow("+uno+", "+dos+") = "+pow(uno, dos));
			O.pln("log"+dos+" ("+uno+") = "+logB(uno, dos));
			O.pln("sqrt("+uno.multiply(dos)+") = "+sqrt(uno.multiply(dos)));
			O.pln("cbrt("+uno+") = "+cbrt(uno));
			O.pln("ln("+uno+") = "+ln(uno));
			O.pln("exp("+uno+") = "+exp(uno));
			
			O.pln(acsc(BigDecimal.ZERO));
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}



















