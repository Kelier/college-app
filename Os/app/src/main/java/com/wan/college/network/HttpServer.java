package com.wan.college.network;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by John Yan on 1/9/2017.
 * 描述app所需要的网络请求方法
 */

public class HttpServer {
    /**
     * 获取排行榜数据
     * @param callback
     */
    public static void getRank(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.rankUrl) ;
     //   params.addQueryStringParameter(Const.PARMA_CITY , city);
        x.http().get(params , callback) ;
    }
    /**
     * 获取新闻标题数据
     * @param callback
     */
    public static void getNewsTitles(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.newsTagsUrl) ;
        params.addQueryStringParameter("type", "news");
        x.http().get(params , callback) ;
    }

    /**
     * 获取高考资讯
     * @param callback
     */
    public static void getNews(Callback.CommonCallback<String> callback,String tag) {
        RequestParams params = new RequestParams(Const.newsUrl);
        params.addQueryStringParameter("type", "news");
        params.addQueryStringParameter("tag", tag);
        params.addQueryStringParameter("page_size", UserMsg.page_size);
        params.addQueryStringParameter("page", "1");
        x.http().get(params , callback) ;
    }

    /**
     * 获取必读数据
     * @param callback
     */
    public static void getBidu(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.biduUrl) ;
        x.http().get(params , callback) ;
    }
    /**
     * 获取政策数据
     * @param callback
     */
    public static void getZhengce(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.zhengceUrl) ;
        x.http().get(params , callback) ;
    }
    /**
     * 获取专业数据
     * @param callback
     */
    public static void getZhuanye(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.zhuanyeUrl) ;
        x.http().get(params , callback) ;
    }
    /**
     * 获取院校数据
     * @param callback
     */
    public static void getYuanxiao(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.yuanxiaoUrl) ;
        x.http().get(params , callback) ;
    }
    /**
     * 获取批次数据
     * @param callback
     */
    public static void getPici(Callback.CommonCallback<String> callback,String province) {
        RequestParams params = new RequestParams(Const.piciUrl) ;
        params.addQueryStringParameter("province", province);
        x.http().get(params , callback) ;
    }
    /**
     * 获取职业测试数据
     * @param callback
     */
    public static void getAnalysis(Callback.CommonCallback<String> callback,int id,int pid) {
        RequestParams params;
        if(id==2){
             params = new RequestParams(Const.analysisUrl+id+Const.analysisSecondUrl) ;
        }else{
            params = new RequestParams(Const.analysisUrl+id+Const.analysisSecondUrl+Const.analysisThirdUrl+pid+Const.analysisFourthUrl) ;
        }
        x.http().get(params , callback) ;
    }
    /**
     * 上传职业测试数据
     * @param callback
     */
    public static void uploadAnalysis(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.uploadAnalysisUrl) ;
        /*
        params.addQueryStringParameter("id", UserMsg.analysis_id);
        params.addQueryStringParameter("answer", UserMsg.analysis_answer);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("version", UserMsg.analysis_version);
        */
        params.addBodyParameter("id", UserMsg.analysis_id);
        params.addBodyParameter("answer", UserMsg.analysis_answer);
        params.addBodyParameter("sid", UserMsg.sid);
        params.addBodyParameter("version", UserMsg.analysis_version);

        x.http().post(params , callback) ;
    }
    /**
     * 获取大学列表数据
     * @param callback
     */
    public static void getCollegelist(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.collegeListUrl) ;
        params.addQueryStringParameter("page_size", UserMsg.page_size);
        params.addQueryStringParameter("subject", UserMsg.subject);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("score", UserMsg.score);
        params.addQueryStringParameter("province", UserMsg.province);
        if(UserMsg.risk>0&&UserMsg.risk<4)params.addQueryStringParameter("risk", String.valueOf(UserMsg.risk));
        if(UserMsg.risk==4)params.addQueryStringParameter("query",UserMsg.college);
        x.http().get(params , callback) ;
    }
//
    public static void getCollegelist(Callback.CommonCallback<String> callback,String province,String college_type,String last_cid) {
        RequestParams params = new RequestParams(Const.collegeListUrl) ;
        params.addQueryStringParameter("page_size", UserMsg.page_size);
        params.addQueryStringParameter("subject", UserMsg.subject);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("score", UserMsg.score);
        if(college_type!=null)
            params.addQueryStringParameter("college_type",college_type);
        if(province!=null)
            params.addQueryStringParameter("province",province);
        if(last_cid!=null)
            params.addQueryStringParameter("last_cid",last_cid);

        x.http().get(params , callback) ;
    }
    /**
     * 获取主页数据
     * @param callback
     */
    public static void getHome(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.homeUrl) ;
        params.addQueryStringParameter("score", UserMsg.score);
        params.addQueryStringParameter("subject", UserMsg.subject);
        params.addQueryStringParameter("province",UserMsg.province);
        x.http().get(params , callback) ;
    }
    /**
     * 获取大学具体详细信息
     * @param callback
     */
    public static void getCollegeDetail(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.collegeDetailUrl) ;
        params.addQueryStringParameter("cid", UserMsg.cid);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }
    /**
     * 获取学校具体录取分数线以及专业分数线
     * @param callback
     */
    public static void getCollegeAdmit(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.collegeAdimitUrl) ;
        params.addQueryStringParameter("cid", UserMsg.cid);
        params.addQueryStringParameter("province",UserMsg.province);
        params.addQueryStringParameter("subject", UserMsg.subject);
        params.addQueryStringParameter("score", UserMsg.score);
        x.http().get(params , callback) ;
    }
    /**
     *获取如何选专业
     */
    public static void getCareer(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.careerUrl);
        params.addQueryStringParameter("type", "career");
        x.http().get(params , callback) ;
    }
    /**
     *获取个人信息
     */
    public static void getUserMsg(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.userMessageUrl);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }

    /**
     *获取个人信息自己数据库
     */
    public static void getUserMsgs(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.userMsgUrl);
        params.addQueryStringParameter("uid", UserMsg.uid);
        x.http().get(params , callback) ;
    }
    /**
     *获取专业具体信息
     */
    public static void getMajorDetail(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.majorDetailUrl);
        params.addQueryStringParameter("mid", UserMsg.mid);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }
    /**
     *获取专业三级列表数据
     */
    public static void getMajorList(Callback.CommonCallback<String> callback,String first_level) {
        RequestParams params = new RequestParams(Const.majorListUrl);
        params.addQueryStringParameter("first_level", first_level);
        x.http().get(params , callback) ;
    }
    public static void getMajorList(Callback.CommonCallback<String> callback,String first_level,String second_level) {
        RequestParams params = new RequestParams(Const.majorListUrl);
        params.addQueryStringParameter("first_level", first_level);
        params.addQueryStringParameter("second_level", second_level);
        x.http().get(params , callback) ;
    }
    public static void getMajorList(Callback.CommonCallback<String> callback,String first_level,String second_level,String third_level) {
        RequestParams params = new RequestParams(Const.majorListUrl);
        params.addQueryStringParameter("first_level", first_level);
        params.addQueryStringParameter("second_level", second_level);
        params.addQueryStringParameter("third_level", third_level);
        x.http().get(params , callback) ;
    }
    /**
     *获取我的关注学校信息
     */
    public static void getInterestedCollegeList(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.interestedCollegeListUrl);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("page_size", UserMsg.page_size);
        params.addQueryStringParameter("score", UserMsg.score);
        params.addQueryStringParameter("province",UserMsg.province);
        params.addQueryStringParameter("subject", UserMsg.subject);
        x.http().get(params , callback) ;
    }
    public static void getInterestedCollegeList(Callback.CommonCallback<String> callback,String last_cid) {
        RequestParams params = new RequestParams(Const.interestedCollegeListUrl);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("page_size", UserMsg.page_size);
        params.addQueryStringParameter("score", UserMsg.score);
        params.addQueryStringParameter("province",UserMsg.province);
        params.addQueryStringParameter("subject", UserMsg.subject);
        params.addQueryStringParameter("last_cid",last_cid);
        x.http().get(params , callback) ;
    }
    /**
     *设置我的关注学校信息
     */
    public static void setInterestedCollege(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.interestedCollegeUrl);
        params.addQueryStringParameter("cid", UserMsg.cid);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }
    /**
     *设置移除我的关注学校信息
     */
    public static void removeInterestedCollege(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.removeInterestedCollegeUrl);
        params.addQueryStringParameter("cid", UserMsg.cid);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }
    /**
     *获取我的关注专业信息
     */
    public static void getInterestedMajorList(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.interestedMajorListUrl);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("mid", UserMsg.mid);
        x.http().get(params , callback) ;
    }
    public static void getInterestedMajorList(Callback.CommonCallback<String> callback,String last_mid) {
        RequestParams params = new RequestParams(Const.interestedMajorListUrl);
        params.addQueryStringParameter("sid", UserMsg.sid);
        params.addQueryStringParameter("last_mid",last_mid);
        x.http().get(params , callback) ;
    }
    /**
     *设置我的关注专业信息
     */
    public static void setInterestedMajor(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.interestedCollegeUrl);
        params.addQueryStringParameter("mid", UserMsg.mid);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }
    /**
     *设置移除我的关注专业信息
     */
    public static void removeInterestedMajor(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Const.removeInterestedCollegeUrl);
        params.addQueryStringParameter("mid", UserMsg.mid);
        params.addQueryStringParameter("sid", UserMsg.sid);
        x.http().get(params , callback) ;
    }
    /**
     *上传个人资料昵称信息
     */
    public static void postPersonInfoName(Callback.CommonCallback<String> callback,String name) {
        RequestParams params = new RequestParams(Const.setPersonInfoUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addBodyParameter("name",name);
        x.http().post(params,callback);
    }
    /**
     *上传个人资料性别信息
     */
    public static void postPersonInfoGender(Callback.CommonCallback<String> callback,String sex) {
        RequestParams params = new RequestParams(Const.setPersonInfoUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addBodyParameter("sex",sex);
        x.http().post(params,callback);
    }

    /**
     *上传个人资料文理科信息
     */
    public static void postPersonInfoSubject(Callback.CommonCallback<String> callback,String user_subject) {
        RequestParams params = new RequestParams(Const.setPersonInfoUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addBodyParameter("subject",user_subject);
        params.addBodyParameter("user_subject",user_subject);
        x.http().post(params,callback);
    }

    /**
     *上传个人资料省份信息
     */
    public static void postPersonInfoProvince(Callback.CommonCallback<String> callback,String user_province) {
        RequestParams params = new RequestParams(Const.setPersonInfoUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addBodyParameter("province",user_province);
        params.addBodyParameter("user_province",user_province);
        x.http().post(params,callback);
    }

    /**
     *上传个人资料分数信息
     */
    public static void postPersonInfoScore(Callback.CommonCallback<String> callback,String score) {
        RequestParams params = new RequestParams(Const.setPersonInfoUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addBodyParameter("score",score);
        x.http().post(params,callback);
    }

/**
 * 问答（最新问题）数据获取
 */
    public static void getNewestQuestion(Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(Const.getNewestQuestionUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addQueryStringParameter("page_size",UserMsg.page_size);
        x.http().get(params,callback);
    }
    /**
     * 问答（今日最热）数据获取
     */
    public static void getDayQuestion(Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(Const.getDayQuestionUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addQueryStringParameter("page_size",UserMsg.page_size);
        x.http().get(params,callback);
    }
    /**
     * 问答（一周最热）数据获取
     */
    public static void getWeekQuestion(Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(Const.getWeekQuestionUrl);
        params.addQueryStringParameter("sid",UserMsg.sid);
        params.addQueryStringParameter("page_size",UserMsg.page_size);
        x.http().get(params,callback);
    }
    /**
     * 注册信息上传
     */
    public static void postRegisterInfo(String uname,String upwd,Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(Const.registerUrl);
        params.addQueryStringParameter("uname",uname);
        params.addQueryStringParameter("upwd",upwd);
        x.http().post(params,callback);
    }
    /**
     * 登录请求
     */
    public static void postLogin(String uname,String upwd,Callback.CommonCallback<String> callback){
        RequestParams params=new RequestParams(Const.loginUrl);
        params.addQueryStringParameter("uname",uname);
        params.addQueryStringParameter("upwd",upwd);
        x.http().post(params,callback);
    }
//    /**
//     * 重置密码
//     */
//    public static void postLogin(String uname,String upwd,Callback.CommonCallback<String> callback){
//        RequestParams params=new RequestParams(Const.loginUrl);
//        params.addQueryStringParameter("uname",uname);
//        params.addQueryStringParameter("upwd",upwd);
//        x.http().post(params,callback);
//    }

    public static void updateUserInfo(Callback.CommonCallback<String> callback,String param,String value){
        RequestParams params=new RequestParams(Const.updateUserInfoUrl);
        params.addBodyParameter("uid",UserMsg.uid);
        params.addBodyParameter(param,value);
        x.http().post(params,callback);
    }

}
