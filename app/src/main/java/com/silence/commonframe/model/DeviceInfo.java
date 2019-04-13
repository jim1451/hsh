package com.silence.commonframe.model;

public class DeviceInfo {

    /**
     * deviceId : 12339f7b-a719-4d50-9120-8320b44a475f
     * gatewayId : 12339f7b-a719-4d50-9120-8320b44a475f
     * notifyType : deviceDataChanged
     * service : {"data":{"adSimple":9,"alarmThrdHigh":0,"alarmThrdLow":0,"batteryLowVoltage":0,"batteryType":0,"batteryVoltage":36,"blackSmoke":0,"buttonTest":1,"cardId":"460113011383277","count":6,"deviceId":"0000000000000010","deviceStatus":0,"deviceType":2,"dust":0,"envTemp":220,"exhaustGas":0,"fireAlarm":0,"hardwareVersion":11,"invisibleSmoke":0,"latType":255,"latValue":0,"lngType":255,"lngValue":0,"mcuTemp":220,"moduleId":"861964040793411","period":1320,"reserve":"00000000000000000000000000000000","restarts":1,"signalStrength":21,"silence":0,"smokeConc":0,"softwareVersion":22,"stationCellId":278,"stationSNR":5,"tamper":1,"whiteSmoke":0},"eventTime":"20190227T094116Z","serviceId":"DeviceInfo","serviceType":"DeviceInfo"}
     */

    private String deviceId;
    private String gatewayId;
    private String notifyType;
    private ServiceBean service;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public ServiceBean getService() {
        return service;
    }

    public void setService(ServiceBean service) {
        this.service = service;
    }

    public static class ServiceBean {
        /**
         * data : {"adSimple":9,"alarmThrdHigh":0,"alarmThrdLow":0,"batteryLowVoltage":0,"batteryType":0,"batteryVoltage":36,"blackSmoke":0,"buttonTest":1,"cardId":"460113011383277","count":6,"deviceId":"0000000000000010","deviceStatus":0,"deviceType":2,"dust":0,"envTemp":220,"exhaustGas":0,"fireAlarm":0,"hardwareVersion":11,"invisibleSmoke":0,"latType":255,"latValue":0,"lngType":255,"lngValue":0,"mcuTemp":220,"moduleId":"861964040793411","period":1320,"reserve":"00000000000000000000000000000000","restarts":1,"signalStrength":21,"silence":0,"smokeConc":0,"softwareVersion":22,"stationCellId":278,"stationSNR":5,"tamper":1,"whiteSmoke":0}
         * eventTime : 20190227T094116Z
         * serviceId : DeviceInfo
         * serviceType : DeviceInfo
         */

        private DataBean data;
        private String eventTime;
        private String serviceId;
        private String serviceType;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getEventTime() {
            return eventTime;
        }

        public void setEventTime(String eventTime) {
            this.eventTime = eventTime;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public static class DataBean {
            /**
             * adSimple : 9
             * alarmThrdHigh : 0.0
             * alarmThrdLow : 0.0
             * batteryLowVoltage : 0
             * batteryType : 0
             * batteryVoltage : 36
             * blackSmoke : 0
             * buttonTest : 1
             * cardId : 460113011383277
             * count : 6
             * deviceId : 0000000000000010
             * deviceStatus : 0
             * deviceType : 2
             * dust : 0
             * envTemp : 220.0
             * exhaustGas : 0
             * fireAlarm : 0
             * hardwareVersion : 11
             * invisibleSmoke : 0
             * latType : 255
             * latValue : 0.0
             * lngType : 255
             * lngValue : 0.0
             * mcuTemp : 220.0
             * moduleId : 861964040793411
             * period : 1320
             * reserve : 00000000000000000000000000000000
             * restarts : 1
             * signalStrength : 21
             * silence : 0
             * smokeConc : 0.0
             * softwareVersion : 22
             * stationCellId : 278
             * stationSNR : 5
             * tamper : 1
             * whiteSmoke : 0
             */

            private int adSimple;
            private double alarmThrdHigh;
            private double alarmThrdLow;
            private int batteryLowVoltage;
            private int batteryType;
            private int batteryVoltage;
            private int blackSmoke;
            private int buttonTest;
            private String cardId;
            private int count;
            private String deviceId;
            private int deviceStatus;
            private int deviceType;
            private int dust;
            private double envTemp;
            private int exhaustGas;
            private int fireAlarm;
            private int hardwareVersion;
            private int invisibleSmoke;
            private int latType;
            private double latValue;
            private int lngType;
            private double lngValue;
            private double mcuTemp;
            private String moduleId;
            private int period;
            private String reserve;
            private int restarts;
            private int signalStrength;
            private int silence;
            private double smokeConc;
            private int softwareVersion;
            private int stationCellId;
            private int stationSNR;
            private int tamper;
            private int whiteSmoke;

            public int getAdSimple() {
                return adSimple;
            }

            public void setAdSimple(int adSimple) {
                this.adSimple = adSimple;
            }

            public double getAlarmThrdHigh() {
                return alarmThrdHigh;
            }

            public void setAlarmThrdHigh(double alarmThrdHigh) {
                this.alarmThrdHigh = alarmThrdHigh;
            }

            public double getAlarmThrdLow() {
                return alarmThrdLow;
            }

            public void setAlarmThrdLow(double alarmThrdLow) {
                this.alarmThrdLow = alarmThrdLow;
            }

            public int getBatteryLowVoltage() {
                return batteryLowVoltage;
            }

            public void setBatteryLowVoltage(int batteryLowVoltage) {
                this.batteryLowVoltage = batteryLowVoltage;
            }

            public int getBatteryType() {
                return batteryType;
            }

            public void setBatteryType(int batteryType) {
                this.batteryType = batteryType;
            }

            public int getBatteryVoltage() {
                return batteryVoltage;
            }

            public void setBatteryVoltage(int batteryVoltage) {
                this.batteryVoltage = batteryVoltage;
            }

            public int getBlackSmoke() {
                return blackSmoke;
            }

            public void setBlackSmoke(int blackSmoke) {
                this.blackSmoke = blackSmoke;
            }

            public int getButtonTest() {
                return buttonTest;
            }

            public void setButtonTest(int buttonTest) {
                this.buttonTest = buttonTest;
            }

            public String getCardId() {
                return cardId;
            }

            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
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

            public int getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(int deviceType) {
                this.deviceType = deviceType;
            }

            public int getDust() {
                return dust;
            }

            public void setDust(int dust) {
                this.dust = dust;
            }

            public double getEnvTemp() {
                return envTemp;
            }

            public void setEnvTemp(double envTemp) {
                this.envTemp = envTemp;
            }

            public int getExhaustGas() {
                return exhaustGas;
            }

            public void setExhaustGas(int exhaustGas) {
                this.exhaustGas = exhaustGas;
            }

            public int getFireAlarm() {
                return fireAlarm;
            }

            public void setFireAlarm(int fireAlarm) {
                this.fireAlarm = fireAlarm;
            }

            public int getHardwareVersion() {
                return hardwareVersion;
            }

            public void setHardwareVersion(int hardwareVersion) {
                this.hardwareVersion = hardwareVersion;
            }

            public int getInvisibleSmoke() {
                return invisibleSmoke;
            }

            public void setInvisibleSmoke(int invisibleSmoke) {
                this.invisibleSmoke = invisibleSmoke;
            }

            public int getLatType() {
                return latType;
            }

            public void setLatType(int latType) {
                this.latType = latType;
            }

            public double getLatValue() {
                return latValue;
            }

            public void setLatValue(double latValue) {
                this.latValue = latValue;
            }

            public int getLngType() {
                return lngType;
            }

            public void setLngType(int lngType) {
                this.lngType = lngType;
            }

            public double getLngValue() {
                return lngValue;
            }

            public void setLngValue(double lngValue) {
                this.lngValue = lngValue;
            }

            public double getMcuTemp() {
                return mcuTemp;
            }

            public void setMcuTemp(double mcuTemp) {
                this.mcuTemp = mcuTemp;
            }

            public String getModuleId() {
                return moduleId;
            }

            public void setModuleId(String moduleId) {
                this.moduleId = moduleId;
            }

            public int getPeriod() {
                return period;
            }

            public void setPeriod(int period) {
                this.period = period;
            }

            public String getReserve() {
                return reserve;
            }

            public void setReserve(String reserve) {
                this.reserve = reserve;
            }

            public int getRestarts() {
                return restarts;
            }

            public void setRestarts(int restarts) {
                this.restarts = restarts;
            }

            public int getSignalStrength() {
                return signalStrength;
            }

            public void setSignalStrength(int signalStrength) {
                this.signalStrength = signalStrength;
            }

            public int getSilence() {
                return silence;
            }

            public void setSilence(int silence) {
                this.silence = silence;
            }

            public double getSmokeConc() {
                return smokeConc;
            }

            public void setSmokeConc(double smokeConc) {
                this.smokeConc = smokeConc;
            }

            public int getSoftwareVersion() {
                return softwareVersion;
            }

            public void setSoftwareVersion(int softwareVersion) {
                this.softwareVersion = softwareVersion;
            }

            public int getStationCellId() {
                return stationCellId;
            }

            public void setStationCellId(int stationCellId) {
                this.stationCellId = stationCellId;
            }

            public int getStationSNR() {
                return stationSNR;
            }

            public void setStationSNR(int stationSNR) {
                this.stationSNR = stationSNR;
            }

            public int getTamper() {
                return tamper;
            }

            public void setTamper(int tamper) {
                this.tamper = tamper;
            }

            public int getWhiteSmoke() {
                return whiteSmoke;
            }

            public void setWhiteSmoke(int whiteSmoke) {
                this.whiteSmoke = whiteSmoke;
            }
        }
    }
}
