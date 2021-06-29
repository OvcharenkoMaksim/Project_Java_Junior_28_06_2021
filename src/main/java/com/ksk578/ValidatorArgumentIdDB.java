package com.ksk578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ValidatorArgumentIdDB extends ValidatorArgumentFath {
    //цель класса - принять Идентификаторы БД в качестве прараметра запроса, выполнить валидацию вводимых значений и удалить
    // возможные дубли
    String idBD;

    // ввод идентификаторов БД, , переопределение метода и добавление валидации данных
    @Override
    protected ArrayList <String> populatingArgumentList () throws IOException {
        ArrayList <String> list = new ArrayList <> ();
        System.out.println ("Введите Идентификаторы БД в произвольном порядке:");
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        for (int i = 0; i < 5000; i++) {
            idBD = reader.readLine ();
            if (idBD.length () == 0) break;
            if (idBD.length () >= 19) {
                System.out.println ("Вы ввели заведомо не корректный идентификатор \n И в наказание лишены права вводить новые идентификаторы\n");
                break;
            }
            long idBDlong = Long.parseLong (idBD);
            if (idBD.length () <= 9 &&  idBD.length () >= 7 && idBDlong > 0) {
                list.add (idBD);
            } else {
                System.out.println ("Введите корректный Идентификатор БД");
            }
        }
        if (list.size () == 0 || idBD.length () >= 19) {
            System.out.println ("Идентификатор БД не введен \nРабота программы завершена досрочно");
            System.exit (1);
        }
        return list;
    }
}
