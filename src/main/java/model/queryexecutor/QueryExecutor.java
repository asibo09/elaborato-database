package model.queryexecutor;

import controller.Controller;
import controller.Controller.QueryName;
import model.factory.impl.FactoryQueryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class QueryExecutor {

    private final Connection connection;
    private final Controller controller;
    private final FactoryQueryImpl factoryQuery;

    public QueryExecutor(final Connection connection) {

    }

    public Map<String, List<String>> executeQuery(final QueryName qName) {
        final ResultSet rs = this.factoryQuery.createAddReportQuery().execute();
        ManipulationResult mr = mr.getManupulatedResult();
        return java.util.Collections.emptyMap();
    }
}
