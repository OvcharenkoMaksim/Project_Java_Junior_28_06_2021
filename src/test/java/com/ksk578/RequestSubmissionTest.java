package com.ksk578;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class RequestSubmissionTest {
    //тестовый метод для проверки работы класса по выводу ответа от БД пользователю

    LinkedHashMap<Integer,Responseline> mapResponce = new LinkedHashMap <> ();

    //подготовка карты с объектом "хранящим" ответ от БД
    @Before
    public void getMapResponceTest () {
        Responseline responseline = new Responseline ( "creationDate", "systName", "document",
                "globStatus", "systNameGlobStatus", "svr", "organization",
                "globStat", "lokStat", "globVersion", "lockVersion",
                "databaseID", "globalID", "orgID", "docID");
        mapResponce.put(81, responseline);
    }

    //тестирование метода вывода данных пользователю
    @Test
    public void presentingRequestScreenTest () throws SQLException, IOException, ClassNotFoundException {
        //создание заглушек
        Connect connectMock = Mockito.mock(Connect.class);
        FieldsView fieldsViewMock = Mockito.mock(FieldsView.class);

        // тестовые данные stend = "TSE-TEST" и значение 1 или 2 для переменной long a
        String stend = "Проверка";
        long a = 2;

        //управление поведением заглушек
        Mockito.when(connectMock.databaseSelection()).thenReturn(stend);
        Mockito.when(connectMock.getMapResponce ()).thenReturn(mapResponce);
        Mockito.when(fieldsViewMock.view ()).thenReturn(a);

        //тестирование методов класса RequestSubmission
        RequestSubmission requestSubmission = new RequestSubmission();

        requestSubmission.setConnect(connectMock);
        requestSubmission.setFieldsView(fieldsViewMock);
        requestSubmission.presentingRequestScreen();
        //оценка успешности работы метода по выводу в консоль шаблона таблицы с ответом от БД
    }
}