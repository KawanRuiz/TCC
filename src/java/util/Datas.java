/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kawan
 */
public class Datas {


    public Date formatarData(String dat) throws ParseException {
        Date data = new Date();
        try {

            if (dat != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date d = formato.parse(dat);
                java.sql.Date dataSql = new java.sql.Date(d.getTime());
                return dataSql;
            }

        } catch (Exception e) {
            Calendar c = Calendar.getInstance();

            java.sql.Date dataSql = new java.sql.Date(data.getTime());
            return dataSql;

        }

        return data;
    }
        public Date formatar(String dat) throws ParseException {
        Date data = new Date();
        try {

            if (dat != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date d = formato.parse(dat);
                java.sql.Date dataSql = new java.sql.Date(d.getTime());
                return dataSql;
            }

        } catch (Exception e) {
            Calendar c = Calendar.getInstance();

            java.sql.Date dataSql = new java.sql.Date(data.getTime());
            return dataSql;

        }

        return data;
    }
}
