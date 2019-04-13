package com.silence.commonframe.model;

import java.util.List;

public class DeviceDataChangedModel {

    /**
     * code : 0
     * data : [{"batteryVoltage":3,"deviceId":"0000000000000051","deviceStatus":0,"fireAlarm":1,"id":"fea35ac3cba942f6a12eb289e391e68e","inputtime":"2019-04-04 11:36:41.0","mcuTemp":22,"signalStrength":17},{"batteryVoltage":3,"deviceId":"0000000000000051","deviceStatus":0,"fireAlarm":0,"id":"fe190660c9824a99ad6cac5f6b9bbdad","inputtime":"2019-03-26 13:30:51.0","mcuTemp":21,"signalStrength":14}]
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
         * batteryVoltage : 3
         * deviceId : 0000000000000051
         * deviceStatus : 0
         * fireAlarm : 1
         * id : fea35ac3cba942f6a12eb289e391e68e
         * inputtime : 2019-04-04 11:36:41.0
         * mcuTemp : 22
         * signalStrength : 17
         */

        private int batteryVoltage;
        private String deviceId;
        private int deviceStatus;
        private int fireAlarm;
        private String id;
        private String inputtime;
        private int mcuTemp;
        private int signalStrength;

        public int getBatteryVoltage() {
            return batteryVoltage;
        }

        public void setBatteryVoltage(int batteryVoltage) {
            this.batteryVoltage = batteryVoltage;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public int getFireAlarm() {
            return fireAlarm;
        }

        public void setFireAlarm(int fireAlarm) {
            this.fireAlarm = fireAlarm;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public int getMcuTemp() {
            return mcuTemp;
        }

        public void setMcuTemp(int mcuTemp) {
            this.mcuTemp = mcuTemp;
        }

        public int getSignalStrength() {
            return signalStrength;
        }

        public void setSignalStrength(int signalStrength) {
            this.signalStrength = signalStrength;
        }
    }
}
