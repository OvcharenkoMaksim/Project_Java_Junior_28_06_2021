package com.ksk578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class ValidatorArgumentFath {
    //цель класса - получить значения параметра отбора записей в БД УФОС, выполнить их валидаци., удалить возможные дубли.
    // Метод populatingArgumentList требует переопределения в наследниках, т.к. разные применяются
    //принципы валидации значений параметра отбора данных в БД, метод eliminationOfDuplicates не переопределяется
    // в наследниках, наследуется в оригинальном виде

    //базовая, рабочая реализация метода по  вводу аргумента в запрос к БД,
    // метод требует переопределения в наследниках, приводится базовая часть реализации
    protected ArrayList <String> populatingArgumentList () throws IOException {
        ArrayList <String> list = new ArrayList <> ();
        System.out.println ("Введите аргумент");
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        String argument = reader.readLine ();
        list.add (argument);
        if (list.size () == 0) {
            System.out.println ("Аргумент не введен \nРабота программы завершена досрочно\n");
            System.exit (1);
        }
        return list;
    }

    //удаление возможных дублей, метод наследуется полностью
    protected ArrayList <String> eliminationOfDuplicates (ArrayList<String> list) {
        ArrayList <String> list1;
        Set <String> set = new LinkedHashSet <> (list);
        list1 = new ArrayList <> (set);
        return list1;
    }
}
