package com.ksk578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FieldsView {
    //цель класса - определить вид выводимого результата, в частности количество полей в ответе от БД, выбрав один из
    // двух предложенных вариантов
    long fied;
    String s;

    //выбор выводимых колонок в ответе на запрос к БД
    protected long view () throws IOException {
        System.out.println ("\nВыберите количество выводимых полей в запросе:\nКраткая форма: 8 полей (введите - 1): " +
                "\nДата создания | Сист имя | Документ | Глоб статус | СВР | Организация | Идентификатор БД | " +
                "Глобальный идентификатор\n \nПолная форма: 15 полей (введите - 2):\nДата создания | Сист имя | " +
                "Документ | Глоб статус | Сист имя глоб статуса | СВР | Организация | Глоб стат | Лок стат | Глоб верс " +
                "| Лок верс | Идентификатор БД | Глобальный идентификатор | ORGID | DOCID");
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        //ограничиваем возможность ошибочных ответов тремя попытками и валидируем вводимые значения
        for (int a = 0; a < 3; a++){
            s = reader.readLine ();
            if (s.length () >= 18 || s.length () == 0) {
                break;
            }
            fied = Long.parseLong (s);
            if (fied != 1 && fied != 2) {
                System.out.println ("Введите правильный код выбора полей таблицы");
            }
            else {
                break;
            }
        }
        if (s.length () >= 18 || s.length () == 0 || (fied != 1 && fied !=2) ) {
            System.out.println ("\nВы ввели не корректный аргумент \nРабота программы завершена досрочно");
            System.exit (1);
        }
        return fied;
    }


}
