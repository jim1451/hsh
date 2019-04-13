package com.silence.commonframe.bean;

import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class JsonBean {
    /**
     * count : null
     * object : [{"id":241,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":12,"stationcellid":0,"mcutemp":12.3,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550120306000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":240,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":13,"stationcellid":0,"mcutemp":12.3,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550119711000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":239,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":12,"stationcellid":0,"mcutemp":11.8,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550116937000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":238,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":13,"stationcellid":0,"mcutemp":11.8,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550062479000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":237,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":12,"stationcellid":0,"mcutemp":11.8,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550061918000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":236,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":13,"stationcellid":0,"mcutemp":12.8,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550048538000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":235,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":36,"signalstrength":12,"stationcellid":0,"mcutemp":10.9,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1550015500000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":234,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":36,"signalstrength":11,"stationcellid":0,"mcutemp":10.9,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1549986277000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":233,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":35,"signalstrength":13,"stationcellid":0,"mcutemp":10.8,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1549961752000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0},{"id":232,"deviceid":"0030452AA09472AA","lngvalue":0,"latvalue":0,"batteryvoltage":36,"signalstrength":14,"stationcellid":0,"mcutemp":10.9,"devicestatus":6,"smokeconc":28.6,"alarmthrdlow":10,"alarmthrdhigh":1,"troubletype":null,"troubletime":1549961448000,"handleperson":null,"handletime":null,"handleexplain":null,"d_location":"shgjr6ofgdkeertyghhg","location":"浙江省杭州市余杭区龙王塘路61号","siteid":182,"state":0}]
     * message : ok
     */

    private Object count;
    private String message;
    private List<ObjectBean> object;

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
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
         * id : 241
         * deviceid : 0030452AA09472AA
         * lngvalue : 0
         * latvalue : 0
         * batteryvoltage : 35
         * signalstrength : 12
         * stationcellid : 0
         * mcutemp : 12.3
         * devicestatus : 6
         * smokeconc : 28.6
         * alarmthrdlow : 10
         * alarmthrdhigh : 1
         * troubletype : null
         * troubletime : 1550120306000
         * handleperson : null
         * handletime : null
         * handleexplain : null
         * d_location : shgjr6ofgdkeertyghhg
         * location : 浙江省杭州市余杭区龙王塘路61号
         * siteid : 182
         * state : 0
         */

        private int id;
        private String deviceid;
        private int lngvalue;
        private int latvalue;
        private int batteryvoltage;
        private int signalstrength;
        private int stationcellid;
        private double mcutemp;
        private int devicestatus;
        private double smokeconc;
        private int alarmthrdlow;
        private int alarmthrdhigh;
        private Object troubletype;
        private long troubletime;
        private Object handleperson;
        private Object handletime;
        private Object handleexplain;
        private String d_location;
        private String location;
        private int siteid;
        private int state;

        private String devicename;
        private int fireAlarm;
        private String deployment;



        public String getDeployment() {
            return deployment;
        }

        public void setDeployment(String deployment) {
            this.deployment = deployment;
        }

        public int getFireAlarm() {
            return fireAlarm;
        }

        public void setFireAlarm(int fireAlarm) {
            this.fireAlarm = fireAlarm;
        }



        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

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

        public int getLngvalue() {
            return lngvalue;
        }

        public void setLngvalue(int lngvalue) {
            this.lngvalue = lngvalue;
        }

        public int getLatvalue() {
            return latvalue;
        }

        public void setLatvalue(int latvalue) {
            this.latvalue = latvalue;
        }

        public int getBatteryvoltage() {
            return batteryvoltage;
        }

        public void setBatteryvoltage(int batteryvoltage) {
            this.batteryvoltage = batteryvoltage;
        }

        public int getSignalstrength() {
            return signalstrength;
        }

        public void setSignalstrength(int signalstrength) {
            this.signalstrength = signalstrength;
        }

        public int getStationcellid() {
            return stationcellid;
        }

        public void setStationcellid(int stationcellid) {
            this.stationcellid = stationcellid;
        }

        public double getMcutemp() {
            return mcutemp;
        }

        public void setMcutemp(double mcutemp) {
            this.mcutemp = mcutemp;
        }

        public int getDevicestatus() {
            return devicestatus;
        }

        public void setDevicestatus(int devicestatus) {
            this.devicestatus = devicestatus;
        }

        public double getSmokeconc() {
            return smokeconc;
        }

        public void setSmokeconc(double smokeconc) {
            this.smokeconc = smokeconc;
        }

        public int getAlarmthrdlow() {
            return alarmthrdlow;
        }

        public void setAlarmthrdlow(int alarmthrdlow) {
            this.alarmthrdlow = alarmthrdlow;
        }

        public int getAlarmthrdhigh() {
            return alarmthrdhigh;
        }

        public void setAlarmthrdhigh(int alarmthrdhigh) {
            this.alarmthrdhigh = alarmthrdhigh;
        }

        public Object getTroubletype() {
            return troubletype;
        }

        public void setTroubletype(Object troubletype) {
            this.troubletype = troubletype;
        }

        public long getTroubletime() {
            return troubletime;
        }

        public void setTroubletime(long troubletime) {
            this.troubletime = troubletime;
        }

        public Object getHandleperson() {
            return handleperson;
        }

        public void setHandleperson(Object handleperson) {
            this.handleperson = handleperson;
        }

        public Object getHandletime() {
            return handletime;
        }

        public void setHandletime(Object handletime) {
            this.handletime = handletime;
        }

        public Object getHandleexplain() {
            return handleexplain;
        }

        public void setHandleexplain(Object handleexplain) {
            this.handleexplain = handleexplain;
        }

        public String getD_location() {
            return d_location;
        }

        public void setD_location(String d_location) {
            this.d_location = d_location;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getSiteid() {
            return siteid;
        }

        public void setSiteid(int siteid) {
            this.siteid = siteid;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }


//    private String text;
//
//
//    public JsonBean(String text) {
//        this.text = text;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
}
