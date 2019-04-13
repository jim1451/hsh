package com.silence.commonframe.model;

import java.util.List;

public class SiteDevice {

    /**
     * object : [{"id":43,"deployment":"海胜海智联","regionalism":"浙江省 杭州市 余杭区","longtitude":"","latitude":"","location":"浙江省杭州市余杭区龙王塘路61号","userid":22,"lonType":"","latType":"","inputtime":1547606054000,"state":0},{"id":88,"deployment":"11111","regionalism":"浙江省 杭州市 余杭区","longtitude":"","latitude":"","location":"浙江省杭州市余杭区龙王塘路61号","userid":22,"lonType":"","latType":"","inputtime":1547720971000,"state":0},{"id":89,"deployment":"222222","regionalism":"浙江省 杭州市 余杭区","longtitude":"","latitude":"","location":"浙江省杭州市余杭区龙王塘路61号","userid":22,"lonType":"","latType":"","inputtime":1547720977000,"state":0}]
     * message : ok
     */

    private String message;
    private List<ObjectBean> object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * id : 43
         * deployment : 海胜海智联
         * regionalism : 浙江省 杭州市 余杭区
         * longtitude :
         * latitude :
         * location : 浙江省杭州市余杭区龙王塘路61号
         * userid : 22
         * lonType :
         * latType :
         * inputtime : 1547606054000
         * state : 0
         */

        private int id;
        private String deployment;
        private String regionalism;
        private String longtitude;
        private String latitude;
        private String location;
        private int userid;
        private String lonType;
        private String latType;
        private long inputtime;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeployment() {
            return deployment;
        }

        public void setDeployment(String deployment) {
            this.deployment = deployment;
        }

        public String getRegionalism() {
            return regionalism;
        }

        public void setRegionalism(String regionalism) {
            this.regionalism = regionalism;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getLonType() {
            return lonType;
        }

        public void setLonType(String lonType) {
            this.lonType = lonType;
        }

        public String getLatType() {
            return latType;
        }

        public void setLatType(String latType) {
            this.latType = latType;
        }

        public long getInputtime() {
            return inputtime;
        }

        public void setInputtime(long inputtime) {
            this.inputtime = inputtime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
