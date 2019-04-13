package com.silence.commonframe.model;

public class LoginFromPasswordModel {

    /**
     * code : 0
     * msg : success
     * data : {"username":"yhf","phone":"13027210708","company":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjpcImE4MjM3OGNjMTRlZDQ4YTVhNjY2ODJjZDUzYTEwYjBjXCIsXCJjbGllbnRJZFwiOlwiMTIzXCIsXCJ0eXBlXCI6XCIyXCJ9IiwiaWF0IjoxNTUyNzA0ODQ5LCJleHAiOjE1NTMzMDk2NDl9.LSvp6K8uDJMrxuyzqSKpQwCc2tTimFDEDe745ElXuD6H4a09kHSlEwJS31pj7g7dYZKJyfDAoJESn5sYqkusMA"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : yhf
         * phone : 13027210708
         * company : null
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjpcImE4MjM3OGNjMTRlZDQ4YTVhNjY2ODJjZDUzYTEwYjBjXCIsXCJjbGllbnRJZFwiOlwiMTIzXCIsXCJ0eXBlXCI6XCIyXCJ9IiwiaWF0IjoxNTUyNzA0ODQ5LCJleHAiOjE1NTMzMDk2NDl9.LSvp6K8uDJMrxuyzqSKpQwCc2tTimFDEDe745ElXuD6H4a09kHSlEwJS31pj7g7dYZKJyfDAoJESn5sYqkusMA
         */

        private String username;
        private String phone;
        private Object company;
        private String token;

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        private String loginId;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
