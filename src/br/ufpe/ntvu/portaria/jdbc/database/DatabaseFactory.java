package br.ufpe.ntvu.portaria.jdbc.database;

public class DatabaseFactory {
    public static Database getDatabase(String nome){
        if(nome.equals("postgresql")){
            return new DatabasePostgreSQL();
        }else if(nome.equals("mysql")){
            //return new DatabaseMySQL();
        }
        return null;
    }
}
