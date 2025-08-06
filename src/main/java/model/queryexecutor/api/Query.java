package model.queryexecutor.api;

import java.sql.ResultSet;
import java.util.Optional;

public interface Query {

    /** This method is called by an object-query to execute the query which it stands fort
     * @return The resul of the query
     */
    public Optional<ResultSet> execute();
}
