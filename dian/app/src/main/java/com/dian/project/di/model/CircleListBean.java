package com.dian.project.di.model;

import java.util.List;

public class CircleListBean {
    /**
     * result : [{"commodityId":27,"content":"我红烧豆腐卡萨丁","createTime":1566415709000,"greatNum":4,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2090,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-21/5948220190821142829.png","nickName":"MB_Z7ZbV","userId":1516,"whetherGreat":2},{"commodityId":3,"content":"aisdoiasd","createTime":1566004765000,"greatNum":5,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2089,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-16/1578620190816201925.jpeg","nickName":"百事可乐","userId":1719,"whetherGreat":2},{"commodityId":5,"content":"我丢","createTime":1565972255000,"greatNum":22,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-08-13/20190813152325.jpg","id":2088,"image":"","nickName":"初拥","userId":1692,"whetherGreat":2},{"commodityId":18,"content":"来来，冲八万！耶！","createTime":1565972252000,"greatNum":10,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-08-13/20190813152325.jpg","id":2087,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-16/4015320190816111732.gif","nickName":"初拥","userId":1692,"whetherGreat":2},{"commodityId":10010001,"content":"哈哈哈","createTime":1565971514000,"greatNum":2,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2085,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-16/9645820190816110514.png","nickName":"8P_48Y2Y","userId":1693,"whetherGreat":2},{"commodityId":10010001,"content":"哈哈哈","createTime":1565971498000,"greatNum":1,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2084,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-16/1065720190816110458.png","nickName":"8P_48Y2Y","userId":1693,"whetherGreat":2},{"commodityId":18,"content":"来来冲八万","createTime":1565967296000,"greatNum":2,"headPic":"http://mobile.bwstudent.com/images/small/head_pic/2019-08-13/20190813152325.jpg","id":2082,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-16/2406720190816095456.gif","nickName":"初拥","userId":1692,"whetherGreat":2},{"commodityId":6,"content":"52","createTime":1565886572000,"greatNum":6,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2081,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-15/5027820190815112932.jpg","nickName":"百事可乐","userId":1719,"whetherGreat":2},{"commodityId":4,"content":"mmp","createTime":1565836097000,"greatNum":8,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2080,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-14/6729320190814212817.jpeg","nickName":"百事可乐","userId":1719,"whetherGreat":2},{"commodityId":9,"content":"海绵宝宝我们去抓水母叭","createTime":1565833681000,"greatNum":8,"headPic":"http://mobile.bwstudent.com/images/small/default/user.jpg","id":2078,"image":"http://mobile.bwstudent.com/images/small/circle_pic/2019-08-14/6759220190814204801.png","nickName":"百事可乐","userId":1719,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 27
         * content : 我红烧豆腐卡萨丁
         * createTime : 1566415709000
         * greatNum : 4
         * headPic : http://mobile.bwstudent.com/images/small/default/user.jpg
         * id : 2090
         * image : http://mobile.bwstudent.com/images/small/circle_pic/2019-08-21/5948220190821142829.png
         * nickName : MB_Z7ZbV
         * userId : 1516
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;//2为否  1位是

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
