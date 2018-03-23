package api;

import java.util.Map;

public abstract class AbstractApi {

    public String request(final String query) {
        return null;
    }

    protected Map<String, String> buildParams(final String query) {
        return null;
    }
}
