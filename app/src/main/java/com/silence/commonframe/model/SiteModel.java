package com.silence.commonframe.model;

import java.util.List;

public class SiteModel {

    /**
     * code : 0
     * msg : success
     * data : {"dataList":[{"id":"1b5aacbb489d4468b17f8d1681235543","deployment":"余杭区111","regionCode":"4546","regionName":"消防设备3","longtitude":"string","latitude":"string","location":"杭州市西湖区11","userId":"a82378cc14ed48a5a66682cd53a10b0c","inputTime":null,"isValid":"1","userIdCreate":null,"gmtCreate":"2019-03-16 14:29:03","userIdUpdate":null,"gmtUpdate":null},{"id":"4547145ceaa141fea7ace277b9efaacb","deployment":"余杭区11","regionCode":"4546","regionName":"消防设备1","longtitude":"string","latitude":"string","location":"杭州市西湖区1","userId":"a82378cc14ed48a5a66682cd53a10b0c","inputTime":null,"isValid":"1","userIdCreate":null,"gmtCreate":"2019-03-16 14:28:20","userIdUpdate":null,"gmtUpdate":null},{"id":"32a7269bf9f840bb863b43dffae8eea7","deployment":"余杭区1","regionCode":"4546","regionName":"消防设备","longtitude":"string","latitude":"string","location":"杭州市西湖区","userId":"a82378cc14ed48a5a66682cd53a10b0c","inputTime":null,"isValid":"1","userIdCreate":null,"gmtCreate":"2019-03-16 14:28:03","userIdUpdate":null,"gmtUpdate":null}],"rows":4}
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
         * dataList : [{"id":"1b5aacbb489d4468b17f8d1681235543","deployment":"余杭区111","regionCode":"4546","regionName":"消防设备3","longtitude":"string","latitude":"string","location":"杭州市西湖区11","userId":"a82378cc14ed48a5a66682cd53a10b0c","inputTime":null,"isValid":"1","userIdCreate":null,"gmtCreate":"2019-03-16 14:29:03","userIdUpdate":null,"gmtUpdate":null},{"id":"4547145ceaa141fea7ace277b9efaacb","deployment":"余杭区11","regionCode":"4546","regionName":"消防设备1","longtitude":"string","latitude":"string","location":"杭州市西湖区1","userId":"a82378cc14ed48a5a66682cd53a10b0c","inputTime":null,"isValid":"1","userIdCreate":null,"gmtCreate":"2019-03-16 14:28:20","userIdUpdate":null,"gmtUpdate":null},{"id":"32a7269bf9f840bb863b43dffae8eea7","deployment":"余杭区1","regionCode":"4546","regionName":"消防设备","longtitude":"string","latitude":"string","location":"杭州市西湖区","userId":"a82378cc14ed48a5a66682cd53a10b0c","inputTime":null,"isValid":"1","userIdCreate":null,"gmtCreate":"2019-03-16 14:28:03","userIdUpdate":null,"gmtUpdate":null}]
         * rows : 4
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
             * id : 1b5aacbb489d4468b17f8d1681235543
             * deployment : 余杭区111
             * regionCode : 4546
             * regionName : 消防设备3
             * longtitude : string
             * latitude : string
             * location : 杭州市西湖区11
             * userId : a82378cc14ed48a5a66682cd53a10b0c
             * inputTime : null
             * isValid : 1
             * userIdCreate : null
             * gmtCreate : 2019-03-16 14:29:03
             * userIdUpdate : null
             * gmtUpdate : null
             */

            private String id;
            private String deployment;
            private String regionCode;
            private String regionName;
            private String longtitude;
            private String latitude;
            private String location;
            private String userId;
            private Object inputTime;
            private String isValid;
            private Object userIdCreate;
            private String gmtCreate;
            private Object userIdUpdate;
            private Object gmtUpdate;
            private String isMsg;
            private String isTel;

            public String getIsMsg() {
                return isMsg;
            }

            public void setIsMsg(String isMsg) {
                this.isMsg = isMsg;
            }

            public String getIsTel() {
                return isTel;
            }

            public void setIsTel(String isTel) {
                this.isTel = isTel;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDeployment() {
                return deployment;
            }

            public void setDeployment(String deployment) {
                this.deployment = deployment;
            }

            public String getRegionCode() {
                return regionCode;
            }

            public void setRegionCode(String regionCode) {
                this.regionCode = regionCode;
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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public Object getInputTime() {
                return inputTime;
            }

            public void setInputTime(Object inputTime) {
                this.inputTime = inputTime;
            }

            public String getIsValid() {
                return isValid;
            }

            public void setIsValid(String isValid) {
                this.isValid = isValid;
            }

            public Object getUserIdCreate() {
                return userIdCreate;
            }

            public void setUserIdCreate(Object userIdCreate) {
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
}
