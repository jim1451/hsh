package com.silence.commonframe.model;

import java.util.List;

public class UploadModel {

    /**
     * code : 0
     * msg : 文件上传成功！
     * data : [{"attachId":"155420492911264409","url":"/api/file/picture/155420492911264409.jpg","thumbnailUrl":null,"attachType":"jpg"}]
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
         * attachId : 155420492911264409
         * url : /api/file/picture/155420492911264409.jpg
         * thumbnailUrl : null
         * attachType : jpg
         */

        private String attachId;
        private String url;
        private Object thumbnailUrl;
        private String attachType;

        public String getAttachId() {
            return attachId;
        }

        public void setAttachId(String attachId) {
            this.attachId = attachId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(Object thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getAttachType() {
            return attachType;
        }

        public void setAttachType(String attachType) {
            this.attachType = attachType;
        }
    }
}
