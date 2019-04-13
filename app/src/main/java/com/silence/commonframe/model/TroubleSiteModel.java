package com.silence.commonframe.model;

import java.util.List;

public class TroubleSiteModel {

    /**
     * code : 0
     * msg : success
     * data : [{"id":"becf321556c811e9990c00163e0c3ae9","userId":"e368ab7c803a4cf896e7cf9750e17c10","siteId":"ca864134392b4c53b6f449e9aa607279","deployment":"测试1","location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","regionName":"余杭区","longtitude":"119.965239","latitude":"30.275302","troubleType":"1","troubleCount":2,"troubleGmtCreate":"2019-04-04 18:59:40","isValid":"1","userIdCreate":"e368ab7c803a4cf896e7cf9750e17c10","gmtCreate":"2019-04-04- 18:59:40","userIdUpdate":null,"gmtUpdate":null}]
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
         * id : becf321556c811e9990c00163e0c3ae9
         * userId : e368ab7c803a4cf896e7cf9750e17c10
         * siteId : ca864134392b4c53b6f449e9aa607279
         * deployment : 测试1
         * location : 浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心
         * regionName : 余杭区
         * longtitude : 119.965239
         * latitude : 30.275302
         * troubleType : 1
         * troubleCount : 2
         * troubleGmtCreate : 2019-04-04 18:59:40
         * isValid : 1
         * userIdCreate : e368ab7c803a4cf896e7cf9750e17c10
         * gmtCreate : 2019-04-04- 18:59:40
         * userIdUpdate : null
         * gmtUpdate : null
         */

        private String id;
        private String userId;
        private String siteId;
        private String deployment;
        private String location;
        private String regionName;
        private String longtitude;
        private String latitude;
        private String troubleType;
        private int troubleCount;
        private String troubleGmtCreate;
        private String isValid;
        private String userIdCreate;
        private String gmtCreate;
        private Object userIdUpdate;
        private Object gmtUpdate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
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

        public String getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(String longtitude) {
            this.longtitude = longtitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getTroubleType() {
            return troubleType;
        }

        public void setTroubleType(String troubleType) {
            this.troubleType = troubleType;
        }

        public int getTroubleCount() {
            return troubleCount;
        }

        public void setTroubleCount(int troubleCount) {
            this.troubleCount = troubleCount;
        }

        public String getTroubleGmtCreate() {
            return troubleGmtCreate;
        }

        public void setTroubleGmtCreate(String troubleGmtCreate) {
            this.troubleGmtCreate = troubleGmtCreate;
        }

        public String getIsValid() {
            return isValid;
        }

        public void setIsValid(String isValid) {
            this.isValid = isValid;
        }

        public String getUserIdCreate() {
            return userIdCreate;
        }

        public void setUserIdCreate(String userIdCreate) {
            this.userIdCreate = userIdCreate;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getUserIdUpdate() {
            return userIdUpdate;
        }

        public void setUserIdUpdate(Object userIdUpdate) {
            this.userIdUpdate = userIdUpdate;
        }

        public Object getGmtUpdate() {
            return gmtUpdate;
        }

        public void setGmtUpdate(Object gmtUpdate) {
            this.gmtUpdate = gmtUpdate;
        }
    }
}
