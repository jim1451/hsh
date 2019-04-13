package com.silence.commonframe.model;

import java.util.List;

public class Device {


    /**
     * code : 0
     * msg : success
     * data : {"dataList":[{"deviceId":"00000000006","deviceName":"1","ownProduct":"1","deviceLocation":"yuuhghhh","deviceGmtCreate":"2019-03-16 17:41:42","troubleType":"1","troubleGmtCreate":"2019-03-14 09:30:00"}],"rows":1}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dataList : [{"deviceId":"00000000006","deviceName":"1","ownProduct":"1","deviceLocation":"yuuhghhh","deviceGmtCreate":"2019-03-16 17:41:42","troubleType":"1","troubleGmtCreate":"2019-03-14 09:30:00"}]
         * rows : 1
         */

        private int rows;
        private List<DataListBean> dataList;

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * deviceId : 00000000006
             * deviceName : 1
             * ownProduct : 1
             * deviceLocation : yuuhghhh
             * deviceGmtCreate : 2019-03-16 17:41:42
             * troubleType : 1
             * troubleGmtCreate : 2019-03-14 09:30:00
             */

            private String deviceId;
            private String deviceName;
            private String ownProduct;
            private String deviceLocation;
            private String deviceGmtCreate;
            private String troubleType;
            private String troubleGmtCreate;

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

            public String getOwnProduct() {
                return ownProduct;
            }

            public void setOwnProduct(String ownProduct) {
                this.ownProduct = ownProduct;
            }

            public String getDeviceLocation() {
                return deviceLocation;
            }

            public void setDeviceLocation(String deviceLocation) {
                this.deviceLocation = deviceLocation;
            }

            public String getDeviceGmtCreate() {
                return deviceGmtCreate;
            }

            public void setDeviceGmtCreate(String deviceGmtCreate) {
                this.deviceGmtCreate = deviceGmtCreate;
            }

            public String getTroubleType() {
                return troubleType;
            }

            public void setTroubleType(String troubleType) {
                this.troubleType = troubleType;
            }

            public String getTroubleGmtCreate() {
                return troubleGmtCreate;
            }

            public void setTroubleGmtCreate(String troubleGmtCreate) {
                this.troubleGmtCreate = troubleGmtCreate;
            }
        }
    }
}
