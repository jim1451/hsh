package com.silence.commonframe.model;

public class SiteInfo {
    /**
     * count : null
     * object : {"id":273,"deployment":"ceshi11111","regionalism":"余杭区","longtitude":null,"latitude":null,"location":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","userid":40,"lonType":null,"latType":null,"inputtime":1551421437246,"state":null}
     * message : ok
     */

    private Object count;
    private ObjectBean object;
    private String message;

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

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
         * id : 273
         * deployment : ceshi11111
         * regionalism : 余杭区
         * longtitude : null
         * latitude : null
         * location : 浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心
         * userid : 40
         * lonType : null
         * latType : null
         * inputtime : 1551421437246
         * state : null
         */

        private int id;
        private String deployment;
        private String regionalism;
        private Object longtitude;
        private Object latitude;
        private String location;
        private int userid;
        private Object lonType;
        private Object latType;
        private long inputtime;
        private Object state;

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

        public Object getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(Object longtitude) {
            this.longtitude = longtitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
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

        public Object getLonType() {
            return lonType;
        }

        public void setLonType(Object lonType) {
            this.lonType = lonType;
        }

        public Object getLatType() {
            return latType;
        }

        public void setLatType(Object latType) {
            this.latType = latType;
        }

        public long getInputtime() {
            return inputtime;
        }

        public void setInputtime(long inputtime) {
            this.inputtime = inputtime;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }
    }
}
