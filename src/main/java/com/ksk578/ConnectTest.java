package com.ksk578;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ConnectTest implements ConnectStenable {
        //extends ConnectStendFath {
    // цель метода - вынести все данные о подключении в отдельный сервисный класс-наследник для того, что бы упростить
    // масштабирование подключений - для подключения к новой площадке необходимо лишь добавить новый класс-наследник и
    // прописать его вызов в классе Connect
    private final String url;
    private final String username;
    private final String password;
    RequestStructure requestStructure;
    String stend;

    // конструктор с присваиванием значений переменным для подключения к БД из ConnectStend.properties
    public ConnectTest(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    //сеттер получения ссылки на бин объекта класса со структурой запроса
    public void setRequestStructure (RequestStructure requestStructure) {
        this.requestStructure = requestStructure;
    }

    //проксируем вызов метода к родительскому классу, дополняя его требуемыми аргументами
    public LinkedHashMap <Integer, Responseline> connectBdUfos () throws SQLException, IOException {
        LinkedHashMap <Integer,Responseline> mapResponce = new LinkedHashMap <> (ConnectStenable.super.connectBdUfos(url, username, password, requestStructure));
        return mapResponce;
    }
     public String getStendName () {
        return stend = "TSE-TEST";
     }

}

