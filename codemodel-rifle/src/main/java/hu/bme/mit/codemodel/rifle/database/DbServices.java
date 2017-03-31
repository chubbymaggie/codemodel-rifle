package hu.bme.mit.codemodel.rifle.database;

import java.util.Map;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.graphdb.GraphDatabaseService;

import neo4j.driver.testkit.EmbeddedTestkitDriver;

/**
 * Provides database services like transaction handling and query executing.
 */
public class DbServices {
    protected final Driver driver;
    protected Transaction transaction;
    protected QueryBuilder queryBuilder;

    public DbServices(Driver driver) {
        this.driver = driver;
    }

    public Transaction beginTx() {
        Session session = driver.session();
        this.transaction = session.beginTransaction();
        return this.transaction;
    }

    public StatementResult execute(String statement) {
        return this.transaction.run(statement);
    }

    public StatementResult execute(String statementTemplate, Map<String, Object> statementParameters) {
        return this.transaction.run(statementTemplate, statementParameters);
    }

    public GraphDatabaseService getUnderlyingDatabaseService() {
        if (driver instanceof EmbeddedTestkitDriver) {
            return ((EmbeddedTestkitDriver) driver).getUnderlyingDatabaseService();
        } else {
            throw new IllegalStateException("Cannot get underyling database service.");
        }
    }
}
