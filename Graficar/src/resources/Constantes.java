/**
 * 
 */
package resources;

/**
 * @version %I%
 * @author <a href="https://twitter.com/Jedabero" target="_blank">Jedabero</a>
 *
 */
public interface Constantes {
	
	
	
	/**
	 * Grupo de funciones
	 * @author Jedabero
	 *
	 */
	public enum TipoFuncion{
		/** Constante para determinar la funci�n como Polin�mica. */
		POLINOMICA,
		/** Constante para determinar la funci�n como Trigonom�trica. */
		TRIGONOMETRICA,
		/** Constante para determinar la funci�n como Exponencial. */
		EXPONENCIAL,
		/** Constante para determinar la funci�n como Logar�tmica. */
		LOGARITMICA,
		/** Constante para determinar la funci�n como Racional. */
		RACIONAL
	}
	
	/**
	 * Grupo de funciones trigonom�tricas.
	 * @author Jedabero
	 *
	 */
	public enum FuncionTrig{
		/** Constante para determinar Seno. */
		SENO,
		/** Constante para determinar Coseno. */
		COSENO,
		/** Constante para determinar Tangente. */
		TANGENTE,
		/**  Constante para determinar Secante. */
		SECANTE,
		/** Constante para determinar Cosecante. */
		COSECANTE,
		/** Constante para determinar Cotangente. */
		COTANGENTE ;
		
	}
	
}
