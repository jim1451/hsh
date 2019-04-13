package com.silence.commonframe.model;

import java.util.List;

public class DeviceType {
    /**
     * count : null
     * object : [{"id":1,"typeExp":"烟感探测器Tx3190","typePic":"picture/111.png","group_id":1,"explain":"独立式探测器"},{"id":2,"typeExp":"物联网网关Tx3252","typePic":"picture/222.png","group_id":2,"explain":"组合式网关"}]
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
         * id : 1
         * typeExp : 烟感探测器Tx3190
         * typePic : picture/111.png
         * group_id : 1
         * explain : 独立式探测器
         */

        private int id;
        private String typeExp;
        private String typePic;
        private int group_id;
        private String explain;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypeExp() {
            return typeExp;
        }

        public void setTypeExp(String typeExp) {
            this.typeExp = typeExp;
        }

        public String getTypePic() {
            return typePic;
        }

        public void setTypePic(String typePic) {
            this.typePic = typePic;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}
