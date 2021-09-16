package fpt.practice.moneymanagerment.constant;

import java.time.format.DateTimeFormatter;

public class Constants {

    /**
     * Role user
     */
    public static final String ROLE_USER = "ROLE_USER";

    /**
     * Role admin
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Status Active
     */
    public static final String STATUS_ACTIVE = "ACTIVE";

    /**
     * Status Inactive
     */
    public static final String STATUS_INACTIVE = "INACTIVE";

    /**
     * CLAIM_ID
     */
    public static final String CLAIM_ID = "id";

    /**
     * Using ISOString for default date format
     */
    public static DateTimeFormatter ISO_8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /**
     * Using ISOString for default date format with gmt +7
     */
    public static DateTimeFormatter BEGINNING_INDOCHINA_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00.000+07");

    public static String INDOCHINA_ZONE = "Asia/Saigon";

    private Constants() {
    }

    public static final String ENCODING_UTF8 = "UTF-8";


    public class FileProperties {
        private FileProperties() {
        }

        public static final String PROPERTIES_APPLICATION = "application.properties";
        public static final String PROPERTIES_VALIDATION = "validation.properties";
    }
}
