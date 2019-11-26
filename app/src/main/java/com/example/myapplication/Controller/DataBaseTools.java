package com.example.myapplication.Controller;

import android.database.*;

import com.example.myapplication.Model.TennisGame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseTools {

    public static void write(
            Object obj, PreparedStatement ps, int parameterIndex)
            throws SQLException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(baos);
        oout.writeObject(obj);
        oout.close();
        // This will NOT work in JDBC-ODBC bridge under JDK 1.2.2
        // as soon as the size of the byte array is bigger than 2000
        try {
            ps.setBytes(parameterIndex, baos.toByteArray());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public static TennisGame read(ResultSet rs, String column)
            throws SQLException, IOException, ClassNotFoundException {
        // This will NOT work in JDBC-ODBC bridge under JDK 1.2.2
        // as a SQL NULL data value is not handled correctly.
        byte[] buf = new byte[0];
        try {
            buf = rs.getBytes(column);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if (buf != null) {
            ObjectInputStream objectIn = new ObjectInputStream(
                    new ByteArrayInputStream(buf));
            Object Game = objectIn.readObject();
            TennisGame TenGame = (TennisGame) Game;
            return TenGame;
        }
        return null;
    }

}
