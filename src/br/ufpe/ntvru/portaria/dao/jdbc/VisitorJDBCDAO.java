package br.ufpe.ntvru.portaria.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufpe.ntvru.portaria.model.Visitor;

public class VisitorJDBCDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean insert(Visitor visitor) {
        String sql = "INSERT INTO p_visitor(name, cpf, phone) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, visitor.getName());
            stmt.setString(2, visitor.getCpf());
            stmt.setString(3, visitor.getPhone());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VisitorJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Visitor visitor) {
        String sql = "UPDATE p_visitor SET name=?, cpf=?, phone=? WHERE id_visitor=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, visitor.getName());
            stmt.setString(2, visitor.getCpf());
            stmt.setString(3, visitor.getPhone());
            stmt.setInt(4, visitor.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VisitorJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remove(Visitor visitor) {
        String sql = "DELETE FROM p_visitor WHERE id_visitor=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, visitor.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VisitorJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Visitor> list() {
        String sql = "SELECT id_visitor,name,cpf,phone FROM p_visitor where status='A'";
        List<Visitor> result = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Visitor visitor= new Visitor();
                visitor.setId(rs.getInt("id_visitor"));
                visitor.setName(rs.getString("name"));
                visitor.setCpf(rs.getString("cpf"));
                visitor.setPhone(rs.getString("phone"));
                result.add(visitor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Visitor buscar(Visitor visitor) {
        String sql = "SELECT name,cpf,phone FROM visitor WHERE id_visitor=?";
        Visitor rVisitor = new Visitor();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, visitor.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                visitor.setName(resultado.getString("name"));
                visitor.setCpf(resultado.getString("cpf"));
                visitor.setPhone(resultado.getString("phone"));
                rVisitor = visitor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rVisitor;
    }
}
