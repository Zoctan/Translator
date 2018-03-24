package api.exception;

public class DuplicationNameException extends Exception {

    private static final long serialVersionUID = 5743754674671902221L;

    public DuplicationNameException(final String e) {
        super(e, null);
    }
}
