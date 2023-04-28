package entidades;

public interface iVuelo {
	
	// Operaciones de los vuelos.
	
	/**
	 *  Con base a los número de pasajeros que conforma el vuelo
	 *  y el número de pasajeros, determina el monto total del
	 *  vuelo en cuestión.
	 * @return valorFinalVuelo
	 */
	public Double valorFinalVuelo();
	
	/**
	 *  Determina el número de días repstantes para que se realice
	 *  el vuelo.
	 * @return diasDeDiferencia
	 */
	public Integer diasDiferencia();
	
	/**
	 * Determina si el vuelo es rentable o no, en función del tipo
	 * de pasaje y de su valor final.
	 */
	public void segmentacion();

}
