package com.ksk578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Introducer {
    // цель класса - определить вид параметр для отбора записей  в БД, получить значения данного параметра,
    // выполнить проверки на корректность вводимых данных м удалить дубли.

    protected int ident;
    protected String selectionParameter;
    ArrayList <String> list;
    ValidatorArgumentGuid validatorArgumentGuid;
    ValidatorArgumentIdDB validatorArgumentIdDB;

    //сеттеры получения ссылок на бины объектов, выполняющих ввод и валидацию параметров отбора записей в БД
    public void setValidatorArgumentGuid (ValidatorArgumentGuid validatorArgumentGuid) {
        this.validatorArgumentGuid = validatorArgumentGuid;
    }

    public void setValidatorArgumentIdDB (ValidatorArgumentIdDB validatorArgumentIdDB) {
        this.validatorArgumentIdDB = validatorArgumentIdDB;
    }

    // выбор параметра запроса
    protected String inputer () throws IOException {
        //выбор параметра отбора записей в БД
        System.out.println ("\nВыберите параметр поиска:\nguid - введите 1,\tИдентификатор БД - введите 2");
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        ident = Integer.parseInt (reader.readLine ());
        if (ident != 1 && ident != 2) {
            int a = 0;
            while (ident != 1 && ident != 2) {
                if (a < 1) {
                    System.out.println ("Введите правильный код выбора параметра");
                    ident = Integer.parseInt (reader.readLine ());
                } else {
                    System.out.println ("Да сколько уже можно издеваться надо мной! \nВведите корректный код выбора:");
                    ident = Integer.parseInt (reader.readLine ());
                }
                a++;
            }
        }
        // получение значений выбранного параметра, валидация значений и удаление дублей.
        if (ident == 1) {
            selectionParameter = " w.GLOBALDOCID in ('";
            list = new ArrayList <> (validatorArgumentGuid.eliminationOfDuplicates (validatorArgumentGuid.populatingArgumentList ()));
        } else {
            selectionParameter = " r.ROUTECONTEXTID in ('";
            list = new ArrayList <> (validatorArgumentIdDB.eliminationOfDuplicates (validatorArgumentIdDB.populatingArgumentList ()));
        }
        return selectionParameter;
    }

    protected ArrayList <String> getList () {
        return list;
    }

}
