package me.luguco.lobbysystem.mysql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andreas on 14.07.2017.
 */
public class MySqlConnection {

    Connection con;

    public void connect(){

        if(!isconnected()){

        }
    }
    public void disconnect(){

        if(isconnected()){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isconnected(){
        //return(con = ? true:false);
        return true;
    }
}
