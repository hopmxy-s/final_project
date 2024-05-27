package org.example.common.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CommonUtil {
    //    process pageNo
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if (pageNo == null || pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    //    handle pageSize
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        if (pageSize == null || pageSize < 1) {
            pSize = 1;
        }
        return pSize;
    }

    // phone number encryption
    public static String encryPhone(String phone) {
        String result = "***********";
        if (phone != null && phone.trim().length()  == 11) {
            result = phone.substring(0, 3) + "******" + phone.substring(9, 11);

        }
        return result;
    }

    // phone number format
    public static boolean checkPhone(String phone) {
        boolean flag = false;
        if (phone != null && phone.length() == 11) {
            flag = Pattern.matches("^1[1-9]\\d{9}$", phone);
        }
        return flag;
    }

    public static boolean ge(BigDecimal n1, BigDecimal n2){
        if( n1 == null || n2 == null){
            throw new RuntimeException("BigDecimal is null");
        }
        return  n1.compareTo(n2) >= 0;
    }
}
