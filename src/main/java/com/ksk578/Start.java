package com.ksk578;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

public class Start {
    // стартовый класс приложения

    // Приветствие и запуск программы
    public static void main ( String[] args ) throws IOException, SQLException, ClassNotFoundException {
        System.out.println ("Добрый день! \nВы делаете комбинированный запрос-select к БД УФОС, таблицам: Routecontext, " +
                "Org, Doc, Doctype, Docstate. \nЭто основной запрос при разборе ошибок, связанных с ПОИБом\n");

        //подъем контекста спринга, создание бинов, внедрение зависимостей и запуск приложения
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RequestSubmission requestSubmission = context.getBean("requestSubmissionBean", RequestSubmission.class);
        requestSubmission.presentingRequestScreen();
        context.close();

        //сообщение о успешном завершении программы
        System.out.println ( "\nРабота программы завершена успешно." );

    }

}
