package fpt.practice.moneymanagerment.utils;

import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

    private static final Logger logger = Logger.getLogger(NumberUtil.class);

    public String convertAmount(Double amount) {
        String convertAmount = "";
        Locale vnd = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(vnd);
        try {
            convertAmount = numberFormat.format(amount);
        } catch (Exception e) {
            logger.debug("Error convert amount: " + e.getMessage());
        }

        return convertAmount;
    }
}
