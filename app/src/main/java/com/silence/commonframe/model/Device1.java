package com.silence.commonframe.model;

import java.util.List;

public class Device1 {

    /**
     * object : [{"id":9,"deviceid":"0030452AA09472AA","dLongtitude":"","lonType":null,"dLatitude":"","latType":null,"dLocation":"11111111111111","inputtime":null,"addtime":1548380072000,"devicetypeid":2,"siteid":182,"userid":41,"type_exp":"物联网网关Tx3252","state":0,"deployment":null,"location":null}]
     * message : ok
     */




    private String message;
    private List<ObjectBean> object;

    public boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }



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
         * id : 9
         * deviceid : 0030452AA09472AA
         * dLongtitude :
         * lonType : null
         * dLatitude :
         * latType : null
         * dLocation : 11111111111111
         * inputtime : null
         * addtime : 1548380072000
         * devicetypeid : 2
         * siteid : 182
         * userid : 41
         * type_exp : 物联网网关Tx3252
         * state : 0
         * deployment : null
         * location : null
         */

        private int id;
        private String deviceid;
        private String dLongtitude;
        private Object lonType;
        private String dLatitude;
        private Object latType;
        private String dLocation;
        private Object inputtime;
        private long addtime;
        private int devicetypeid;
        private int siteid;
        private int userid;
        private String type_exp;
        private int state;
        private Object deployment;
        private Object location;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getDLongtitude() {
            return dLongtitude;
        }

        public void setDLongtitude(String dLongtitude) {
            this.dLongtitude = dLongtitude;
        }

        public Object getLonType() {
            return lonType;
        }

        public void setLonType(Object lonType) {
            this.lonType = lonType;
        }

        public String getDLatitude() {
            return dLatitude;
        }

        public void setDLatitude(String dLatitude) {
            this.dLatitude = dLatitude;
        }

        public Object getLatType() {
            return latType;
        }

        public void setLatType(Object latType) {
            this.latType = latType;
        }

        public String getDLocation() {
            return dLocation;
        }

        public void setDLocation(String dLocation) {
            this.dLocation = dLocation;
        }

        public Object getInputtime() {
            return inputtime;
        }

        public void setInputtime(Object inputtime) {
            this.inputtime = inputtime;
        }

        public long getAddtime() {
            return addtime;
        }

        public void setAddtime(long addtime) {
            this.addtime = addtime;
        }

        public int getDevicetypeid() {
            return devicetypeid;
        }

        public void setDevicetypeid(int devicetypeid) {
            this.devicetypeid = devicetypeid;
        }

        public int getSiteid() {
            return siteid;
        }

        public void setSiteid(int siteid) {
            this.siteid = siteid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getType_exp() {
            return type_exp;
        }

        public void setType_exp(String type_exp) {
            this.type_exp = type_exp;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getDeployment() {
            return deployment;
        }

        public void setDeployment(Object deployment) {
            this.deployment = deployment;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }
    }
}
