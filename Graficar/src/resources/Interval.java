/**
 * 
 */
package resources;

import java.math.BigDecimal;

/**
 * @author Jedabero
 * @since 0.4
 */
public class Interval {
	
	BigDecimal max;
	BigDecimal min;
	
	/**
	 * Crea un intervalo cerrado de longitud finita
	 * @param min el m�nimo valor del intervalo
	 * @param max el m�ximo valor del intervalo
	 */
	public Interval(BigDecimal min, BigDecimal max){
		this.max = max;
		this.min = min;
	}
	
	/**
	 * 
	 * @return distancia entre el m�ximo y el m�nimo
	 */
	public BigDecimal length(){
		return max.subtract(min);
	}
	
	/**
	 * @return el m�nimo valor del intervalo
	 */
	public BigDecimal min(){
		return min;
	}
	/**
	 * @param m
	 */
	public void setMin(BigDecimal m){
		this.min = m;
	}
	
	/**
	 * @return el m�ximo valor del intervalo
	 */
	public BigDecimal max(){
		return max;
	}
	/**
	 * @param m
	 */
	public void setMax(BigDecimal m){
		this.max = m;
	}
	
	public String toString(){
		return "("+min+", "+max+")";
	}
	
}
