package com.zaotao.daylucky.module.entity;

import java.io.Serializable;

/**
 * Description LuckyContentEntity
 * Created by wangisu@qq.com on 12/21/20.
 */
public class LuckyContentEntity implements Serializable {
    /**
     * id : 2051
     * cont : 你能够享受轻松自在的一天，会有很多增值的机会。工作方面，你会有更多的时间和机会向成功人士取经学习。
     * cont1 : 在10月31号水星逆行在白羊的第八宫，在这个阶段更加在意的是一个人的独处。如果是有暧昧对象的话，在这个阶段也是比较细腻的类型，会让对方觉得有压力，而且也很容易因为一点小事就觉得对方并不是非常在意这段关系。在11月21号水星顺行，对待自己的亲密关系，也并不会那么感性。而且在11月2号，金星进入到射手座，会有想和人交往以及是参加社交活动的欲望。在11月22号，太阳也进入到射手座，会更多的想要出去旅行或者是出国游玩，以及去其他城市看看。在这个阶段还是会遇到一些比较不错的异性的，你自己可以试着相处看看。在11月27号海王星开始顺行，那么一早之前对待感情不切实际的想法，整个人也开始变得踏实了起来，而且如果之前有暗恋的话，在这个阶段可能会跳出比较矫情的状态会更理智的表达自己的感情。
     * cont2 :
     * match_id : 3
     * cont3 : 在11月2号金星进入到射手座，如果在这段期间有出差或者是去其他地方工作的打算的话，还是能在不同地方发现不同的机会的。而且在11月19号火星进入到天蝎座，如果有人找你合作或者是投资的话，一定不要盲目的行动。虽然在工作中执行力比较强，但是也很容易出现做事马虎的状况。在十一月二十一号水星顺行那么在接受信息这方面并不是非常的感性了，所以如果有比较不错的副业的话，还是可以考虑看看的。在11月22号太阳进入到射手座，在这段期间，如果是有做和电商或者是异国或者是异地的交易的话，成功率还是比较高的。而且所有的重心都是放在旅途上面，有这方面打算的话，可以在旅途中重点留意一下。在11月26号金星进入到摩羯座，也就是白羊的天顶，但是土星和冥王星仍然停留在摩羯座，所以在本月仍然会受到来自于自己上司的压力或者是一些旧的问题的存在。
     * cont4 :
     * cont5 : 11月2号金星进入到射手座，如果有考试这方面的准备的话，比如说考教师资格证或者是考研的打算的话，会觉得考试的过程中进行的还是比较顺利的，而且特别是在宏观掌握这方面，整个人的学习状态或者是整个人的知识结构框架已经大体成型。但是也会有各种各样的社交活动，以及是其他地方的活动充斥着你的生活。但是参加业余活动之余一定要顾虑到自己的学习状态才行。而且考试通过的成功率相对来说还是比较高的，在11月26号射手新月，对待自己的学习或者是出国留学都会有一个新的规划和认知。
     * cont6 :
     * cont7 : 在本月你会比较想要提升自己，特别是在学习方面，比如说会有一些购买课本和书籍，还会准备一些相关的考试。但是临近考试，你会因为紧张买各种各样不太需要的资料。而且也会有一些旅行上的花销，但是在整个旅途的过程中一定要量力而行，综合的判断自己能不能坚持下去，然后再投入自己也会比较好。
     * cont8 :
     * cont9 : 在10月31号水星逆行在你的8宫，会有心理压力影响着你自己的生活状态，平时也会有一些失眠或者是多梦的问题，你的健康状态还是比较不错的，主要是来自于你的情绪。一定要控制自己，不要被自己的情绪所驱使。
     * cont10 :
     * cont11 : 不能出去否则撞车
     * match_desc : 巨蟹座的想象力丰富，十分重感情。
     * match_astro : 水象星座,逻辑感强,不表达
     * match_reason : 啊大多数
     * title : 日运
     * luck_desc : 日运日运日运日运日运日运日运日运日运
     * luck : 吃饭
     * bad : 吃屎
     * luck_thing : 啊大苏打
     */

    private int id;
    private String cont;
    private String cont1;
    private String cont2;
    private int match_id;
    private String cont3;
    private String cont4;
    private String cont5;
    private String cont6;
    private String cont7;
    private String cont8;
    private String cont9;
    private String cont10;
    private String cont11;
    private String match_desc;
    private String match_astro;
    private String match_reason;
    private String title;
    private String luck_desc;
    private String luck;
    private String bad;
    private String luck_thing;
    private String luck_colour;
    private String today_advice;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getCont1() {
        return cont1;
    }

    public void setCont1(String cont1) {
        this.cont1 = cont1;
    }

    public String getCont2() {
        return cont2;
    }

    public void setCont2(String cont2) {
        this.cont2 = cont2;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public String getCont3() {
        return cont3;
    }

    public void setCont3(String cont3) {
        this.cont3 = cont3;
    }

    public String getCont4() {
        return cont4;
    }

    public void setCont4(String cont4) {
        this.cont4 = cont4;
    }

    public String getCont5() {
        return cont5;
    }

    public void setCont5(String cont5) {
        this.cont5 = cont5;
    }

    public String getCont6() {
        return cont6;
    }

    public void setCont6(String cont6) {
        this.cont6 = cont6;
    }

    public String getCont7() {
        return cont7;
    }

    public void setCont7(String cont7) {
        this.cont7 = cont7;
    }

    public String getCont8() {
        return cont8;
    }

    public void setCont8(String cont8) {
        this.cont8 = cont8;
    }

    public String getCont9() {
        return cont9;
    }

    public void setCont9(String cont9) {
        this.cont9 = cont9;
    }

    public String getCont10() {
        return cont10;
    }

    public void setCont10(String cont10) {
        this.cont10 = cont10;
    }

    public String getCont11() {
        return cont11;
    }

    public void setCont11(String cont11) {
        this.cont11 = cont11;
    }

    public String getMatch_desc() {
        return match_desc;
    }

    public void setMatch_desc(String match_desc) {
        this.match_desc = match_desc;
    }

    public String getMatch_astro() {
        return match_astro;
    }

    public void setMatch_astro(String match_astro) {
        this.match_astro = match_astro;
    }

    public String getMatch_reason() {
        return match_reason;
    }

    public void setMatch_reason(String match_reason) {
        this.match_reason = match_reason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLuck_desc() {
        return luck_desc;
    }

    public void setLuck_desc(String luck_desc) {
        this.luck_desc = luck_desc;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public String getLuck_thing() {
        return luck_thing;
    }

    public void setLuck_thing(String luck_thing) {
        this.luck_thing = luck_thing;
    }

    public String getLuck_colour() {
        return luck_colour;
    }

    public void setLuck_colour(String luck_colour) {
        this.luck_colour = luck_colour;
    }

    public String getToday_advice() {
        return today_advice;
    }

    public void setToday_advice(String today_advice) {
        this.today_advice = today_advice;
    }
}
