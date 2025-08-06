package model.queryexecutor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class ManipulationResult {

    private final Optional<ResultSet> rs;

    public ManipulationResult(final Optional<ResultSet> rs) {
        this.rs = rs;
    }

    public Map<String, List<String>> manipulateResult(final Optional<ResultSet> resultSet) throws SQLException {
        if(resultSet.isPresent()){
            resultSet.get().close();
        }
        return null;
    }

    public Map<String,List<String>> manipulateResult() {
        try{
            if(this.rs.isPresent()){
                final ResultSet resultSet = this.rs.get();
                Map<String, List<String>> resultMap = new LinkedHashMap<>();

                // Otteniamo i metadati del ResultSet per conoscere le colonne
                final ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Inizializziamo le liste nella mappa per ogni colonna
                for (int i = 1; i <= columnCount; i++) {
                    resultMap.put(metaData.getColumnLabel(i), new ArrayList<>());
                }

                // Iteriamo sulle righe del ResultSet
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        // Aggiungiamo il valore della colonna i alla lista corrispondente
                        final String columnName = metaData.getColumnLabel(i);
                        resultMap.get(columnName).add(resultSet.getString(i));
                    }
                }
                return resultMap;
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return java.util.Collections.emptyMap();
    }

    public Map<String, List<String>> getManupulatedResult() {
        return this.manipulateResult();
    }
}
