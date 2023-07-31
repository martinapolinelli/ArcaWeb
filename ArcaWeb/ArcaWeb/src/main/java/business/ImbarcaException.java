package business;

public class ImbarcaException extends Exception {

	private static final long serialVersionUID = -4241979112768503134L;

	public ImbarcaException() {
		super("Errore nell'imbarco");
	}

	public ImbarcaException(String msg) {
		super(msg);
	}

}
