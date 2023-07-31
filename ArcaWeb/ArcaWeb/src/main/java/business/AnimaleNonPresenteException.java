package business;

public class AnimaleNonPresenteException extends Exception {

	private static final long serialVersionUID = -4241979112768503134L;

	public AnimaleNonPresenteException() {
		super("Errore, animale non trovato");
	}

	public AnimaleNonPresenteException(String msg) {
		super(msg);
	}

}
