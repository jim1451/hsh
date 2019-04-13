package com.silence.commonframe.model;

import java.util.List;

public class FireAlarmModel {

    /**
     * code : 0
     * data : [{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:42:32","id":"92dc3c77f9754e15bf38e75d06f4d97d","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"a2e9b555651f4010ad75f55b55071c30","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:41:34","id":"561d892671604f5ca29750c375e22f9b","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"42cd6f8a9aa64d30b7e782807304a2eb","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:41:13","id":"9268d7caed544a7ea18c95915ba339a8","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"499d180233ec413aad987469cfdd2ec0","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:40:45","id":"64567fa61b284b9c86e58a5c8926bf21","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"78c7deadb64b44e8aa7ef3e49cca0f71","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:39:47","id":"656271083fd74cc196dd4c5b65c344f6","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"4173c7da972049b78a97622b71886e28","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:39:25","id":"95c98ff5d6cf428f9d05a8d7d8f9afca","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"ebca9bd11a0942d7ab0835d7b262ab2e","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:38:57","id":"df37be80c3bd429bae9542dd799bfa88","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"d0328b4ae1e64c7db17033b0d05920e2","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-04-01 15:35:43","id":"8cf7bfcf262c49aba7f8418b0091ae5d","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"0733deb1fdc846b98f9d43d9be78e7cb","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-03-28 16:34:45","id":"86a9e7852f2c48528446499e36a99bab","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"190378c67cf4490faaa17ed643dbed0f","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-03-28 16:34:40","id":"8f17c20052e646508d2636d0c840f506","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"b83987cc16c3409e83edfee6bf15ec96","regionName":"余杭区","troubleType":"1"},{"deployment":"测试1","deviceId":"0000000000000051","deviceLocation":"测试1","deviceName":"独立式光电感烟探测报警器","gmtCreate":"2019-03-28 16:34:18","id":"570b2c5e2dec4b89bfbcd5e16aeab8ce","ifRead":"0","isValid":"1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","messageId":"177c82b2b588477294fee29bd4f89cff","regionName":"余杭区","troubleType":"1"}]
     * msg : success
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
         * deployment : 测试1
         * deviceId : 0000000000000051
         * deviceLocation : 测试1
         * deviceName : 独立式光电感烟探测报警器
         * gmtCreate : 2019-04-01 15:42:32
         * id : 92dc3c77f9754e15bf38e75d06f4d97d
         * ifRead : 0
         * isValid : 1
         * location : 浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心
         * messageId : a2e9b555651f4010ad75f55b55071c30
         * regionName : 余杭区
         * troubleType : 1
         */

        private String deployment;
        private String deviceId;
        private String deviceLocation;
        private String deviceName;
        private String gmtCreate;
        private String id;
        private String ifRead;
        private String isValid;
        private String location;
        private String messageId;
        private String regionName;
        private String troubleType;

        public String getDeployment() {
            return deployment;
        }

        public void setDeployment(String deployment) {
            this.deployment = deployment;
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

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIfRead() {
            return ifRead;
        }

        public void setIfRead(String ifRead) {
            this.ifRead = ifRead;
        }

        public String getIsValid() {
            return isValid;
        }

        public void setIsValid(String isValid) {
            this.isValid = isValid;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getTroubleType() {
            return troubleType;
        }

        public void setTroubleType(String troubleType) {
            this.troubleType = troubleType;
        }
    }
}
