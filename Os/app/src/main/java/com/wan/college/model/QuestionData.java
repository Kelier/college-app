package com.wan.college.model;

import java.util.List;

/**
 * Created by 万文杰 on 2017/1/15.
 */

public class QuestionData {

    /**
     * error_info : ok
     * questions : [{"answers":["是","否"],"question":"在看一本有关谋杀案的小说时，你是否常常能在作者未交代结果之前知道作品中哪个人物是罪犯?","question_id":1},{"answers":["是","否"],"question":"你是否很少写错别字?","question_id":2},{"answers":["是","否"],"question":"你是否宁可参加音乐会而不愿呆在家里闲聊?","question_id":3},{"answers":["是","否"],"question":"墙上的画挂歪了，你是否想去扶正?","question_id":4},{"answers":["是","否"],"question":"你是否常论及自己看过或听过的事物?","question_id":5},{"answers":["是","否"],"question":"你宁可读一些散文和小品文而不愿看小说?","question_id":6},{"answers":["是","否"],"question":"你是否愿少做几件事一定要做好，而不想多做几件事而马马乎乎?","question_id":7},{"answers":["是","否"],"question":"你是否喜欢打牌或下棋?","question_id":8},{"answers":["是","否"],"question":"你是否对自己的消费预算均有控制?","question_id":9},{"answers":["是","否"],"question":"喜欢学习是否能使钟、开关、马达发生效用的原因?","question_id":10},{"answers":["是","否"],"question":"你是否很想改变一下日常生活中的一些惯例,使自己有一些充裕时间?","question_id":11},{"answers":["是","否"],"question":"闲暇时，是否较喜欢参加一些运动，而不愿意看书?","question_id":12},{"answers":["是","否"],"question":"你是否认为数学不难?","question_id":13},{"answers":["是","否"],"question":"你是否喜欢与比你年轻的在一起?","question_id":14},{"answers":["是","否     "],"question":"你能列出五个你自己认为够朋友的人吗?","question_id":15},{"answers":["是","否"],"question":"对于你能办到的事情别人求你时，你是否愿意给予帮助?","question_id":16},{"answers":["是","否"],"question":"你是否不喜欢太细碎的工作?","question_id":17},{"answers":["是","否"],"question":"你看书是否很快?","question_id":18},{"answers":["是","否"],"question":"你是否相信\u201c小心谨慎，稳扎稳打\u201d是至理名言?","question_id":19},{"answers":["是","否"],"question":"你是否喜欢新朋友、新地方和新东西?","question_id":20}]
     * status : 0
     * total : 20
     * version : 1
     */

    private String error_info;
    private int status;
    private int total;
    private int version;
    private List<QuestionsBean> questions;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * answers : ["是","否"]
         * question : 在看一本有关谋杀案的小说时，你是否常常能在作者未交代结果之前知道作品中哪个人物是罪犯?
         * question_id : 1
         */

        private String question;
        private int question_id;
        private List<String> answers;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }
    }
}
