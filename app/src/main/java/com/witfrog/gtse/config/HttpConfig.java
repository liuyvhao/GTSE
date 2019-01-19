package com.witfrog.gtse.config;

public class HttpConfig {

    public static final int HTTP_TIMEOUT = 15 * 1000;

    public static String HTTP_BASE_URL = "http://www.frogcoin.io/";

    public class ErrorCode {
        /**
         * 成功
         */
        public static final int SUCCESS       = 0;
        /**
         * 失败
         */
        public static final int FAILURE       = 1;
        /**
         * 未知错误
         */
        public static final int UNKNOWN_ERROR = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR   = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORK_ERROR = 1002;
        /**
         * 证书错误
         */
        public static final int SSL_ERROR     = 1003;
    }

}
