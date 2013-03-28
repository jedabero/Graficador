/**
 * 
 */
package resources;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * @author Jedabero
 *
 */
public final class Add {
	
	/**
	 * A�ade componentes al panel con un {@code GridBagLayout}.
	 * @param gbl	El componente con el GridBagLayout.
	 * @param jc	El componente a ser a�adido.
	 * @param x		La posici�n en columnas del componente. La primera es 0.
	 * @param y 	La posici�n en filas del componente. La primera es 0.
	 * @param w		El n�mero de columnas que ocupa el componente.
	 * @param h 	El n�mero de filas que ocupa el componente,
	 * @param wx	La proporci�n de ancho del componente.
	 * @param wy	La proporci�n de alto del componente.
	 * @param f 	Valor que determina como redimensionar el componente.
	 * @param toolTip Texto a mostrar en el ToolTip del componente.
	 */
	public static void componente(Container gbl, JComponent jc,
			int x, int y, int w, int h, double wx, double wy, int f,
			String toolTip){
		jc.setToolTipText(toolTip);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x; gbc.gridy = y;
		gbc.gridwidth = w; gbc.gridheight = h;
		gbc.weightx = wx; gbc.weighty = wy;
		gbc.fill = f;
		gbl.add(jc, gbc);
	}
	
	/**
	 * Agrega un item de men� al men� especificado.
	 * @param menu	menu al que se va a agregar el item.
	 * @param texto	texto que llevar� el item.
	 * @param ttt 	ToolTipText
	 * @param al	listener que se ejecutar� al seleccionar el item.
	 * @param mn	letra mnemot�cnica
	 */
	public static void menuItem(JMenu menu, String texto, String ttt,
			ActionListener al, char mn){
		JMenuItem temp = new JMenuItem(texto);
		temp.setToolTipText(ttt);
		temp.setMnemonic(mn);
		temp.addActionListener(al);
		menu.add(temp);
	}
	
	/**
	 * Agrega un item de men� al men� especificado con un conjunto de submen�s.
	 * @param menu		menu al que se va a agregar el item.
	 * @param texto		texto que llevar� el item.
	 * @param ttt		ToolTipText
	 * @param al 		listener que se ejecutar� al seleccionar el item.
	 * @param mn		letra mnemot�cnica
	 * @param subItems	array con los nombres de cada item
	 * @param states	array con los estados de cada item
	 * @param types		array con los tipo de cada item
	 */
	public static void subMenu(JMenu menu, String texto, String ttt,
			ActionListener al, char mn, String[] subItems, boolean[] states,
			char[] types){
		JMenu temp = new JMenu(texto);
		temp.setToolTipText(ttt);
		temp.setMnemonic(mn);
		ButtonGroup grupo = new ButtonGroup();
		String nextName = "0";
		for(int i=0;i<subItems.length;i++){
			String name = subItems[i];
			char mnem = name.charAt(0); 
			if(mnem==nextName.charAt(0)) mnem = name.charAt(1);
			switch(types[i]){
			case 'R':
				JRadioButtonMenuItem jrbmi = new JRadioButtonMenuItem(name);
				jrbmi.setMnemonic(mnem);
				jrbmi.setSelected(states[i]);
				jrbmi.addActionListener(al);
				grupo.add(jrbmi);
				temp.add(jrbmi);
				break;
			case 'C':
				JCheckBoxMenuItem jcbmi = new JCheckBoxMenuItem(name);
				jcbmi.setMnemonic(mnem);
				jcbmi.setSelected(states[i]);
				jcbmi.addActionListener(al);
				temp.add(jcbmi);
				break;
			default:
				JMenuItem jmi = new JMenuItem(name);
				jmi.setMnemonic(mnem);
				jmi.addActionListener(al);
				temp.add(jmi);
				break;
			}
			
			nextName = name;
		}
		menu.add(temp);
	}
	
	/**
	 * A�ade componentes a un {@code DesktopPane}, con una ubicaci�n y tama�os
	 * espec�ficos. 
	 * @param jdp	el DesktopPane al que se a�adir�n componentes.
	 * @param jc	el componente a ser a�adido.
	 * @param x		ubicaci�n en pixeles de derecha a izquierda.
	 * @param y 	ubicaci�n en pixeles de arriba a abajo.
	 * @param w 	ancho que ocupar� el componente.
	 * @param h 	alto que ocupar� el componente.
	 */
	public static void aDeskPane(JDesktopPane jdp, JComponent jc,
			int x, int y, int w, int h){
		jc.setBounds(x, y, w, h);
		jdp.add(jc);
	}
	
}