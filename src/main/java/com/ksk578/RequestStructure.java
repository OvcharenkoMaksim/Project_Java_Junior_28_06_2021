package com.ksk578;

import java.io.IOException;
import java.util.ArrayList;

public class RequestStructure {
    // цель класса - создать итоговый запрос к БД

    protected String select;
    Introducer intr;

    //сеттер получения ссылки на объект, отвечающий за выбор параметра отбора записей в БД и его значения
    public void setIntr (Introducer intr) {
        this.intr = intr;
    }

    //формирование итогового запроса к БД
    protected String quest () throws IOException {
        String sel1 = "select w.createdate \"Дата создания\", d.SYSTEMNAME \"Сист имя\", d.NAME \"Документ\",  " +
                "g.DESCRIPTION \"Глоб статус\", g.systemname \"Сист имя глоб статуса\", o.SYSTEMNAME \"СВР\", " +
                "o.NAME \"Организация\", w.docstateid \"Глоб стат\", r.localdocstateid \"Лок стат\", " +
                "w.docstate_version \"Глоб верс\",  r.docstate_version \"Лок верс\", r.ROUTECONTEXTID \"Идентификатор БД\", " +
                "w.GLOBALDOCID \"Глобальный идентификатор\", r.ORGID, w.DOCID from ROUTECONTEXT r, ORG o, DOC w, DOCTYPE d, " +
                "DOCSTATE g where r.ORGID = o.ORGID and r.DOCID = w.DOCID and d.DOCTYPEID = w.DOCTYPEID and w.DOCSTATEID = " +
                "g.DOCSTATEID and  ";
        String sel2;
        String sel3;
        String sel4 = " order by r.ROUTECONTEXTID";

        sel2 = intr.inputer ();
        ArrayList <String> list = new ArrayList <> (intr.getList ());
        sel3 = list.get(0);
        if (list.size () > 1) {
            for (int i = 1; i < list.size (); i++) {
                String selprom = list.get (i);
                sel3 = sel3 + "' ,'" + selprom;
            }
        }
        sel3 = sel3 + "')";
        select = sel1 + sel2 + sel3 + sel4 ;
        return select;
    }
}
