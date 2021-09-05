package fpt.practice.moneymanagerment.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class);
    public static String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";

    public String convertDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MM_YYYY_HH_MM);
        String formatDate = "";
        try {
            formatDate = simpleDateFormat.format(date);
        }catch (Exception e){
            logger.debug("Error Convert date: " + e.getMessage());
        }
        return formatDate;
    }

    public Date getCurrentDate(){
        return new Date();
    }
}
