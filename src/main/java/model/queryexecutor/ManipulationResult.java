package model.queryexecutor;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class ManipulationResult {

    private final ResultSet rs;

    public ManipulationResult(final ResultSet rs) {
        this.rs = rs;
    }

    public Map<String,List<String>> manipulateResult() {}

    public Map<String, List<String>> getManupulatedResult() {
        this.manipulateResult();
        return null;
    }
}
