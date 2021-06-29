package com.ksk578;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;

@FunctionalInterface
public interface ConnectStenable {

    public String getStendName ();

    default public LinkedHashMap<Integer, Responseline> connectBdUfos (String url, String username, String password, RequestStructure requestStructure)
        throws SQLException, IOException {
            LinkedHashMap <Integer,Responseline> mapResponce = new LinkedHashMap <> ();

            // загрузка драйвера и выполнение подключения к БД
            Connection con= DriverManager.getConnection (url, username, password);
            Statement stmt=con.createStatement ();

            //контроль подключения к БД
            if (stmt != null){
                System.out.println("Выполнено подключение к БД");
            }

            //получаем итоговый запрос к БД и выполняем его
            ResultSet res = stmt.executeQuery (requestStructure.quest ());

            // полученный ответ сохраняем в мапу
            Integer a = 1;
            while(res.next ()){
                Responseline responseline = new Responseline(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9), res.getString(10), res.getString(11),
                        res.getString(12), res.getString(13), res.getString(14),
                        res.getString(15));
                mapResponce.put(a,responseline);
                a++;
            }
            // закрываем соединение
            con.close ();
            return mapResponce;
    }
}
