package com.ant.jobgod.jobgod.config;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class API {
    public static class URL{
        private static final String BaseUrl = "";

        //Account
        public static final String IsRegistered = "";
        public static final String Register = "";
        public static final String Login = "";
        public static final String ModifyPassword = "";

        //Person
        public static final String GetPersonBrief = "";
        public static final String UpdateGetPersonBrief = "";

        //LBS
        public static final String UpdateLocation = "";
    }

    public static class KEY{
        public static final String STATUS = "status";
        public static final String INFO = "info";
        public static final String DATA = "data";
    }

    public static class CODE{
        public static final int SUCCEED = 200;
        public static final int Failure = 0;
        public static final int PERMISSION_DENIED = 400;
    }
}
