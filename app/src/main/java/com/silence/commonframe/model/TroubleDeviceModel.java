package com.silence.commonframe.model;

import java.util.List;

public class TroubleDeviceModel {

    /**
     * code : 0
     * msg : success
     * data : [{"id":"11","gmtCreate":"2019-03-14 09:30:00","messageId":null,"troubleType":"1","deployment":"yuuhghhh","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","regionName":"余杭区","deviceId":"00000000007","deviceLocation":"6788999"},{"id":"10","gmtCreate":"2019-03-14 09:30:00","messageId":null,"troubleType":"1","deployment":"yuuhghhh","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","regionName":"余杭区","deviceId":"00000000006","deviceLocation":"yuuhghhh"}]
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
         * id : 11
         * gmtCreate : 2019-03-14 09:30:00
         * messageId : null
         * troubleType : 1
         * deployment : yuuhghhh
         * location : 浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心
         * regionName : 余杭区
         * deviceId : 00000000007
         * deviceLocation : 6788999
         */

        private String id;
        private String gmtCreate;
        private Object messageId;
        private String troubleType;
        private String deployment;
        private String location;
        private String regionName;
        private String deviceId;
        private String deviceLocation;

        public String getRecheckId() {
            return recheckId;
        }

        public void setRecheckId(String recheckId) {
            this.recheckId = recheckId;
        }

        private String recheckId="111";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getMessageId() {
            return messageId;
        }

        public void setMessageId(Object messageId) {
            this.messageId = messageId;
        }

        public String getTroubleType() {
            return troubleType;
        }

        public void setTroubleType(String troubleType) {
            this.troubleType = troubleType;
        }

        public String getDeployment() {
            return deployment;
        }

        public void setDeployment(String deployment) {
            this.deployment = deployment;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceLocation() {
            return deviceLocation;
        }

        public void setDeviceLocation(String deviceLocation) {
            this.deviceLocation = deviceLocation;
        }
    }
}
