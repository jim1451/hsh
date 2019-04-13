package com.silence.commonframe.model;

public class LoginModel {

    /**
     * object : {"id":1,"username":"aaa","password":"123456","phone":"13888888888","email":null,"created":null,"updated":null,"token":"1:1546497761970"}
     * message : ok
     */

    private ObjectBean object;
    private String message;

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ObjectBean {
        /**
         * id : 1
         * username : aaa
         * password : 123456
         * phone : 13888888888
         * email : null
         * created : null
         * updated : null
         * token : 1:1546497761970
         */

        private int id;
        private String username;
        private String password;
        private String phone;
        private Object email;
        private Object created;
        private Object updated;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getCreated() {
            return created;
        }

        public void setCreated(Object created) {
            this.created = created;
        }

        public Object getUpdated() {
            return updated;
        }

        public void setUpdated(Object updated) {
            this.updated = updated;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
