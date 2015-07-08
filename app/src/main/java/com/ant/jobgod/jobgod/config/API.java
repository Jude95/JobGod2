package com.ant.jobgod.jobgod.config;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class API {
    public static class URL{
        private static final String BaseUrl = "http://103.238.226.48:81/index.php/";

        //Account
        public static final String IsRegistered = BaseUrl + "home/index/checkTel";
        public static final String Register = BaseUrl + "home/user/register";
        public static final String Login = BaseUrl + "xx";
        public static final String ModifyPassword = BaseUrl + "xx";

        //Person
        public static final String GetPersonBrief = "";
        public static final String UpdateGetPersonBrief = "";

        //LBS
        public static final String UpdateLocation = "";

        //public
        public static final String GetBanner = "https://apiview.com/test/327/Public/getBanner";
        public static final String GetTopicJobList = "https://apiview.com/test/327/Public/getTopicJobList";
        public static final String GetTopicList =  "https://apiview.com/test/327/Public/getTopicList";
        public static final String GetHotJobList = "https://apiview.com/test/327/Public/getHotJobList";
        public static final String GetJobList = "https://apiview.com/test/327/Public/getJobList";
        public static final String GetTrades = "https://apiview.com/test/327/Public/getTrades";
        public static final String GetJobDetail = "https://apiview.com/test/327/Public/getJobDetail";
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
