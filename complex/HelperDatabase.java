/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab5complexsystem;

import java.sql.*;

/**
 *
 * @author biar
 */
public class HelperDatabase {
    
    /**
     * Connect to a sample database
     *
     * @param fileName the database file name. It must be a file with .db extension 
    */
    public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:/home/biar/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Connect to a sample database
     */
    public static void connect(String filename) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/home/biar/" + filename;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void createIdentityTable(String filename) {
        // SQLite connection string
        String url = "jdbc:sqlite:/home/biar/" + filename;
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE Identity (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	username text NOT NULL UNIQUE,\n"
                + "	password text,\n"
                + "     token integer,\n"
                + "     timestamp integer\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createActiveUsersTable(String filename) {
        // SQLite connection string
        String url = "jdbc:sqlite:/home/biar/" + filename;
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE ActiveUsers (\n"
                + "     token integer PRIMARY KEY,\n"
                + "     timestamp integer\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createOrderingTable(String filename) {
        // SQLite connection string
        String url = "jdbc:sqlite:/home/biar/" + filename;
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE Ordering (\n"
                + "	numOrder integer PRIMARY KEY,\n"
                + "	productName text NOT NULL UNIQUE,\n"
                + "     quantity integer,\n"
                + "     clientId integer,\n"
                + "     price real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createCatalogTable(String filename) {
        // SQLite connection string
        String url = "jdbc:sqlite:/home/biar/" + filename;
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE Catalog (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	productName text NOT NULL UNIQUE,\n"
                + "     numItems integer,\n"
                + "     price real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createBasketTable(String filename) {
        // SQLite connection string
        String url = "jdbc:sqlite:/home/biar/" + filename;
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE Basket (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	productName text NOT NULL UNIQUE,\n"
                + "     quantity integer,\n"
                + "     price real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        createNewDatabase("test.db");
        connect("test.db");
        createIdentityTable("test.db");
        createActiveUsersTable("test.db");
        createCatalogTable("test.db");
        createOrderingTable("test.db");
        createBasketTable("test.db");
    }
    
}
