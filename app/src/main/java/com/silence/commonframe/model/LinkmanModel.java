package com.silence.commonframe.model;

import java.util.List;

public class LinkmanModel {


    /**
     * code : 0
     * msg : success
     * data : [{"id":"c0ffd9dc780241fc94948cce0456ea08","name":"lili","phone":"13879756163"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : c0ffd9dc780241fc94948cce0456ea08
         * name : lili
         * phone : 13879756163
         */

        private String id;
        private String name;
        private String phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
