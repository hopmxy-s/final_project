package org.example.common.util;

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
}
