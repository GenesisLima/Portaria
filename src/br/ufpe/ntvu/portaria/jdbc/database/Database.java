
package br.ufpe.ntvu.portaria.jdbc.database;

import java.sql.Connection;


public interface Database {
    
    public Connection connect();
    public void closeConnection(Connection conn);
}
