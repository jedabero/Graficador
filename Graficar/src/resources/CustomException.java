/**
 * 
 */
package resources;

/**
 * @author Jedabero
 *
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1544282612458154520L;
	
	/**
	 * @param m
	 */
	private CustomException(String m){
		super(m);
	}
	
	/**
	 * Crea una excepci�n para cuando el grado es menor que 0.
	 * @return una excepci�n para cuando el grado es menor que 0
	 */
	public static CustomException gradoMenorQue0(){
		return new CustomException("Grado no puede ser menor que 0");
	}
	
	/**
	 * Crea una excepci�n para cuando el coeficiente es 0.
	 * @return una excepci�n para cuando el coeficiente es 0
	 */
	public static CustomException coefAeq0(){
		return new CustomException("Coeficiente A no puede ser cero (0)");
	}
	
	/**
	 * Crea una excepci�n para cuando el tipo de funci�n es incorrecto.
	 * Esto es porque para esos tipos se necesitan datos diferentes.
	 * @return una excepci�n para cuando el tipo de funci�n es incorrecto
	 */
	public static CustomException tipoIncorrecto(){
		return new CustomException("Tipo de funci�n incorrecto; no hay " +
				"suficientes datos.");
	}
	
	/**
	 * @return una excepci�n cuando TODO
	 */
	public static CustomException arrayIncompleto(){
		return new CustomException("Al array le faltan datos para completar la" +
				"costruccion de la Funci�n correctamente.");
	}
	
}
