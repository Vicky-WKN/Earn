package com.earn.model;

import java.util.ArrayList;

/**
 * Created by asus on 2017/8/13.
 */

public class NewResult {

    private int status;
    private ArrayList<News> news;

    public ArrayList<News> getNews(){
        return news;
    }

    public void setNews(ArrayList<News> news){
        this.news = news;
    }

    public int getStatus(){
        return status;
    }
    public void setStatus(int status){
        this.status = status;
    }





    public class News {
        private String title;
        private String times;
        private String imgLinks=null;
        private String imgLinks2 = null;
        private String artcle;

        public void setTitle(String title){
            this.title = title ;
        }
        public void setTimes(String times){
            this.times = times;
        }
        public void setImgLinks(String imgLinks){
            this.imgLinks = imgLinks;
        }
        public void setImgLinks2(String imgLinks2){
            this.imgLinks2 = imgLinks2;
        }
        public void setArtcle(String artcle){
            this.artcle = artcle;
        }

        public String getTitle(){
            return title;
        }
        public String getTimes(){
            return times;
        }
        public String getImgLinks(){
            return imgLinks;
        }
        public String getImgLinks2(){
            return imgLinks2;
        }
        public String getArtcle(){
            return artcle;
        }

        @Override
        public String toString() {
            return "new{" +
                    "title='" + title + '\'' +
                    ", times=" + times+
                    ", imgLinks=" + imgLinks+
                    ", artcle=" + artcle+
                    '}';
        }
    }
}
