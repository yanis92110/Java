package game;

/**
 * Cette classe <i>IndiceHorsLimitesException</i> est une exception personnalisée qui étend
 * <code>RuntimeException</code>. 
 * Elle est utilisée pour signaler une erreur lorsque un indice dépasse les limites autorisées dans le jeu.
 * 
 * @author yanis
 * @version alpha
 */
public class IndiceHorsLimitesException extends RuntimeException {
    
    /**
     * Identifiant unique de la version de la classe pour la sérialisation (Pour eviter un warning).
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructeur de l'exception qui initialise un message d'erreur personnalisé.
     * 
     * @param message Le message d'erreur qui explique la cause de l'exception.
     */
    public IndiceHorsLimitesException(String message) {
        super(message);
    }
}
