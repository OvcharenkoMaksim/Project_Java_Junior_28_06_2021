package com.ksk578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Connect {
    // цель класса - реализовать запрос и получить в итоге карту объектов с данными полей запроса. Из данной карты
    // сформировать выводимый пользователю ответ на запрос.

    private ConnectTest connectTest;
    private ConnectDemo connectDemo;
    private ConnectRls connectRls;
    String stend;
    LinkedHashMap <Integer, Responseline> mapResponce = new LinkedHashMap <> ();

    //сеттеры получения ссылок на бины объектов классов подключения к БД стендов
    public void setConnectTest(ConnectTest connectTest) {
        this.connectTest = connectTest;
    }

    public void setConnectDemo(ConnectDemo connectDemo) {
        this.connectDemo = connectDemo;
    }

    public void setConnectRls(ConnectRls connectRls) {
        this.connectRls = connectRls;
    }

    // выбор стенда, к БД которого делаем запрос
    protected String databaseSelection () throws IOException, SQLException, ClassNotFoundException {
        System.out.println ("Выберите стенд к БД которого выполняем подключение: \n TSE-TEST - 1 \n TSE-DEMO - 2 \n TSE-RLS - 3 ");
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        int standSelection = Integer.parseInt (reader.readLine());
        if (standSelection != 1 && standSelection != 2 && standSelection != 3) {
            int a = 0;
            while (standSelection != 1 && standSelection != 2 && standSelection != 3) {
                if (a < 1) {
                    System.out.println ("Введите правильный код выбора стенда");
                    standSelection = Integer.parseInt(reader.readLine());
                } else {
                    System.out.println ("Я вот сейчас как обижусь и ничего у вас не получится! \nВведите корректный код выбора:");
                    standSelection = Integer.parseInt (reader.readLine ());
                }
                a++;
            }
        }
            // запуск подключения к БД выбранного стенда
            if (standSelection == 1) {
                mapResponce.putAll (connectTest.connectBdUfos ());
                stend = connectTest.getStendName();

            } else if (standSelection == 2) {
                mapResponce.putAll (connectDemo.connectBdUfos ());
                stend = connectDemo.getStendName();

            } else if (standSelection == 3) {
                mapResponce.putAll (connectRls.connectBdUfos ());
                stend = connectRls.getStendName();
            }
        return stend;
    }

        protected LinkedHashMap <Integer, Responseline> getMapResponce () {
                return mapResponce;
        }
}

