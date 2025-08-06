package controller;

import controller.Controller.QueryName;

import java.util.List;
import java.util.Map;

public class BridgeCV {

    private final Controller controller;
    private QueryName queryName;

    public BridgeCV (final Controller controller) {
        this.controller = controller;
    }

    public void setQueryName(QueryName queryName) {
        this.queryName = queryName;
    }

    public Map<String, List<String>> executeQuery(final Map<String, String> parameters) {
        return this.controller.executeQuery(this.queryName, parameters);

    }
}
