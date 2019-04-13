package com.silence.commonframe.model;

public class TuisongModel {


    /**
     * data : {"alarmThrdHigh":"0.0","alarmThrdLow":"0.0","batteryVoltage":"35","deviceId":"0000000000000051","deviceName":"测试1","deviceStatus":"0","envTemp":"188.0","eventTime":"20190404T044230Z","fireAlarm":"1","mcuTemp":"188.0","regionName":"余杭区","siteLocation":"浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心","siteName":"测试1"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * alarmThrdHigh : 0.0
         * alarmThrdLow : 0.0
         * batteryVoltage : 35
         * deviceId : 0000000000000051
         * deviceName : 测试1
         * deviceStatus : 0
         * envTemp : 188.0
         * eventTime : 20190404T044230Z
         * fireAlarm : 1
         * mcuTemp : 188.0
         * regionName : 余杭区
         * siteLocation : 浙江省杭州市余杭区中心路1302号靠近天津市建筑工程质量检测中心浙江分中心
         * siteName : 测试1
         */

        private String alarmThrdHigh;
        private String alarmThrdLow;
        private String batteryVoltage;
        private String deviceId;
        private String deviceName;
        private String deviceStatus;
        private String envTemp;
        private String eventTime;
        private String fireAlarm;
        private String mcuTemp;
        private String regionName;
        private String siteLocation;
        private String siteName;

        public String getAlarmThrdHigh() {
            return alarmThrdHigh;
        }

        public void setAlarmThrdHigh(String alarmThrdHigh) {
            this.alarmThrdHigh = alarmThrdHigh;
        }

        public String getAlarmThrdLow() {
            return alarmThrdLow;
        }

        public void setAlarmThrdLow(String alarmThrdLow) {
            this.alarmThrdLow = alarmThrdLow;
        }

        public String getBatteryVoltage() {
            return batteryVoltage;
        }

        public void setBatteryVoltage(String batteryVoltage) {
            this.batteryVoltage = batteryVoltage;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(String deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public String getEnvTemp() {
            return envTemp;
        }

        public void setEnvTemp(String envTemp) {
            this.envTemp = envTemp;
        }

        public String getEventTime() {
            return eventTime;
        }

        public void setEventTime(String eventTime) {
            this.eventTime = eventTime;
        }

        public String getFireAlarm() {
            return fireAlarm;
        }

        public void setFireAlarm(String fireAlarm) {
            this.fireAlarm = fireAlarm;
        }

        public String getMcuTemp() {
            return mcuTemp;
        }

        public void setMcuTemp(String mcuTemp) {
            this.mcuTemp = mcuTemp;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getSiteLocation() {
            return siteLocation;
        }

        public void setSiteLocation(String siteLocation) {
            this.siteLocation = siteLocation;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }
    }
}
