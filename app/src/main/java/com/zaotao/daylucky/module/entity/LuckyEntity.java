package com.zaotao.daylucky.module.entity;

import java.io.Serializable;
import java.util.List;

public class LuckyEntity implements Serializable {


    /**
     * code : 200
     * msg : 获取成功
     * data : {"astro_intro":"徘徊在冥府之中的引路人","astro":7,"banner":[{"id":13,"name":"纳新","img_url":"http://content.astro90.com/webadmin_image_1584084562834z19fjy1g.jpg","type":1,"target_url":"http://192.168.1.124:8080/#/activity?name=h5_activity2.JPG&save=1","content":"纳新","status":1,"sort":1,"color":"#655C98","create_at":"2020-03-13 15:29:27","update_at":"2020-03-13 16:15:10"}],"today":{"id":2051,"cont":"你能够享受轻松自在的一天，会有很多增值的机会。工作方面，你会有更多的时间和机会向成功人士取经学习。","cont1":"在10月31号水星逆行在白羊的第八宫，在这个阶段更加在意的是一个人的独处。如果是有暧昧对象的话，在这个阶段也是比较细腻的类型，会让对方觉得有压力，而且也很容易因为一点小事就觉得对方并不是非常在意这段关系。在11月21号水星顺行，对待自己的亲密关系，也并不会那么感性。而且在11月2号，金星进入到射手座，会有想和人交往以及是参加社交活动的欲望。在11月22号，太阳也进入到射手座，会更多的想要出去旅行或者是出国游玩，以及去其他城市看看。在这个阶段还是会遇到一些比较不错的异性的，你自己可以试着相处看看。在11月27号海王星开始顺行，那么一早之前对待感情不切实际的想法，整个人也开始变得踏实了起来，而且如果之前有暗恋的话，在这个阶段可能会跳出比较矫情的状态会更理智的表达自己的感情。","cont2":"","match_id":3,"cont3":"在11月2号金星进入到射手座，如果在这段期间有出差或者是去其他地方工作的打算的话，还是能在不同地方发现不同的机会的。而且在11月19号火星进入到天蝎座，如果有人找你合作或者是投资的话，一定不要盲目的行动。虽然在工作中执行力比较强，但是也很容易出现做事马虎的状况。在十一月二十一号水星顺行那么在接受信息这方面并不是非常的感性了，所以如果有比较不错的副业的话，还是可以考虑看看的。在11月22号太阳进入到射手座，在这段期间，如果是有做和电商或者是异国或者是异地的交易的话，成功率还是比较高的。而且所有的重心都是放在旅途上面，有这方面打算的话，可以在旅途中重点留意一下。在11月26号金星进入到摩羯座，也就是白羊的天顶，但是土星和冥王星仍然停留在摩羯座，所以在本月仍然会受到来自于自己上司的压力或者是一些旧的问题的存在。","cont4":"","cont5":"11月2号金星进入到射手座，如果有考试这方面的准备的话，比如说考教师资格证或者是考研的打算的话，会觉得考试的过程中进行的还是比较顺利的，而且特别是在宏观掌握这方面，整个人的学习状态或者是整个人的知识结构框架已经大体成型。但是也会有各种各样的社交活动，以及是其他地方的活动充斥着你的生活。但是参加业余活动之余一定要顾虑到自己的学习状态才行。而且考试通过的成功率相对来说还是比较高的，在11月26号射手新月，对待自己的学习或者是出国留学都会有一个新的规划和认知。","cont6":"","cont7":"在本月你会比较想要提升自己，特别是在学习方面，比如说会有一些购买课本和书籍，还会准备一些相关的考试。但是临近考试，你会因为紧张买各种各样不太需要的资料。而且也会有一些旅行上的花销，但是在整个旅途的过程中一定要量力而行，综合的判断自己能不能坚持下去，然后再投入自己也会比较好。","cont8":"","cont9":"在10月31号水星逆行在你的8宫，会有心理压力影响着你自己的生活状态，平时也会有一些失眠或者是多梦的问题，你的健康状态还是比较不错的，主要是来自于你的情绪。一定要控制自己，不要被自己的情绪所驱使。","cont10":"","cont11":"不能出去否则撞车","match_desc":"巨蟹座的想象力丰富，十分重感情。","match_astro":"水象星座,逻辑感强,不表达","match_reason":"啊大多数","title":"日运","luck_desc":"日运日运日运日运日运日运日运日运日运","luck":"吃饭","bad":"吃屎","luck_thing":"啊大苏打"},"week":null,"today_affection":[],"today_bussiness":[],"today_fortune":[],"audio_count":0,"date":{"time":1585733766,"year":"2020","month":"04","day":"01","weekday":4,"month_abbr":"Apr.","week":"03.30-04.05/2020"},"hidden_login":0}
     */

    private LuckyContentEntity today;
    private LuckyContentEntity week;
    private LuckyContentEntity month;
    private List<LuckyTodayEntity> today_affection;
    private List<LuckyTodayEntity> today_bussiness;
    private List<LuckyTodayEntity> today_fortune;
    private LuckyDateEntity date;
    private int astro;

    public LuckyContentEntity getToday() {
        return today;
    }

    public void setToday(LuckyContentEntity today) {
        this.today = today;
    }

    public LuckyContentEntity getWeek() {
        return week;
    }

    public void setWeek(LuckyContentEntity week) {
        this.week = week;
    }

    public LuckyContentEntity getMonth() {
        return month;
    }

    public void setMonth(LuckyContentEntity month) {
        this.month = month;
    }

    public int getAstro() {
        return astro;
    }

    public void setAstro(int astro) {
        this.astro = astro;
    }

    public List<LuckyTodayEntity> getToday_affection() {
        return today_affection;
    }

    public void setToday_affection(List<LuckyTodayEntity> today_affection) {
        this.today_affection = today_affection;
    }

    public List<LuckyTodayEntity> getToday_bussiness() {
        return today_bussiness;
    }

    public void setToday_bussiness(List<LuckyTodayEntity> today_bussiness) {
        this.today_bussiness = today_bussiness;
    }

    public List<LuckyTodayEntity> getToday_fortune() {
        return today_fortune;
    }

    public void setToday_fortune(List<LuckyTodayEntity> today_fortune) {
        this.today_fortune = today_fortune;
    }

    public LuckyDateEntity getDate() {
        return date;
    }

    public void setDate(LuckyDateEntity date) {
        this.date = date;
    }

}