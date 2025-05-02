package com.pratik.payGateBlend.constants;

public class UrlConstants {

    public static final String ENCRYPT_DOUBLE_VERIFICATION_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/v1/grips/encrypt-dv-data";

    public static final String DECRYPT_DOUBLE_VERIFICATION_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/v1/grips/decrypt-dv-data";

    public static final String ENCRYPT_GRN_SEARCH_REQUEST_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/v1/grips/encrypt-deptdvcheck-data";

    public static final String DECRYPT_GRN_SEARCH_RESPONSE_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/decrypt-dept-dv-check-data";

    public static final String ENCRYPT_PAYMENT_PACKET_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/external/enc";

    public static final String PAYMENT_PACKET_TESTING_API_1_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/external/epay";

    public static final String CHALLAN_DOWNLOAD_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/GeneratePdf/dowload-chalan-by-grn";

    public static final String DEFACEMENT_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/v1/GrnDefacement/grn-defacement";

    public static final String DOUBLE_VERIFICATION_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/v1/grips/double-verification";

    public static final String GRN_SEARCH_URL =
                                "https://train-ifms.wb.gov.in/gripsapi/api/v1/grips/dept-dv-check-grn";

    public static final String RESPONSE_URL =
                                "http://localhost:8090/payment/response";


}
