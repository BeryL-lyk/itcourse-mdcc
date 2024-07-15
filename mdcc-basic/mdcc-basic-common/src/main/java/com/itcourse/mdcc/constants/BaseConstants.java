package com.itcourse.mdcc.constants;

import java.text.SimpleDateFormat;

public class BaseConstants {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DD_HOURSE_MINI = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public class Verify{
        public static final String IMAGE_CODE = "verify:imageCode:";
        public static final String SMS_CODE = "verify:smsCode:";
        public static final int CODE_LENGTH = 4;
        public static final int TIMEOUT = 60 * 10;
        public static final int EXPIRE = 60 * 9;
        public static final int WIDTH = 130;
        public static final int HEIGHT = 35;
    }

    public class Kill{
        // 秒杀状态:0待发布，1已发布，3秒杀结束
        public static final int STATUS_PUBLISH_WAIT = 0;
        public static final int STATUS_PUBLISHING = 1;
        public static final int STATUS_OFF_LINE = 2;

        //秒杀课程的KEY
        public static final String KEY_KILL_COURSES = "KEY_KILL_COURSES";
        //秒杀课程的 库存前缀
        public static final String KEY_KILL_STORE = "KILL_STORE:";
        //秒杀记录前缀
        public static final String KEY_KILLLOG = "KILLLOG:";
        //秒杀成功之后的数据
        public static final String KEY_KILLED_INFO = "KILLED_INFO:";


        //秒杀资格的超时退库存的延迟消息
        public static final String TOPIC_KILL_PAY_OUT_TIME = "TOPIC_KILL_PAY_OUT_TIME";
        public static final String TAG_KILL_PAY_OUT_TIME = "TAG_KILL_PAY_OUT_TIME";
    }


    public class User{
        //用户绑定手机的状态
        public static final long STATE_PHONE = 1;
        //用户绑定邮箱的状态
        public static final long STATE_EMAIL = 2;
        //用户实名认证的状态
        public static final long STATE_REAL_AUTH = 4;
        //用户设置支付密码的状态
        public static final long STATE_PAY_PASSWORD = 8;
    }

    public class Login{
        public static final int TYPE_SYSTEM = 0;
        public static final int TYPE_USER = 1;
    }

    public class Uaa{
        //Token秘钥
        public static final String SIGN_KEY = "123";
        //请求头中的token
        public static final String AUTHORIZATION_HEADER = "Authorization";

        //后台用户
        public static final int USER_TYPE_SYSTEM = 0;
        //前台用户
        public static final int USER_TYPE_WEBSITE = 1;
    }

    public class CourseLean{

        //正常
        public static final int STATE_NORMAL = 0;
        //过期
        public static final int STATE_EXPIRE = 1;

    }


    public class MessageMQ {
        //课程发布消息推送
        public static final String TOPIC_MESSAGE = "topic-message";
        //消息的tags
        public static final String TAGS_SMS_NAME = "tags-sms";
        public static final String TAGS_EMAIL_NAME = "tags-email";
        public static final String TAGS_STATION_MESSAGE = "tags-station-message";
    }

    public class OrderMQ{

        //订单事务组名字
        public static final String TX_ORDER_GROUP = "tx-order-group";

        public static final String TOPIC_COURSE_ORDER = "topic-order";

        //下单后，保存积分的tags
        public static final String TAGS_INTEGRATION = "tags-integration";

        //下单后，保存支付单的tags
        public static final String TAGS_PAY_ORDER = "tags-pay-order";

        //课程延迟队列topic
        public static final String TOPIC_COURSE_ORDER_DEALY = "topic-order-delay";

        //课程延迟队列tag
        public static final String TAGS_COURSE_ORDER_DEALY = "tags-order-delay";


    }


    public class PayMQ{
        //支付结果事务组名字
        public static final String TX_PAY_RESULT_GROUP = "tx-pay-result";
        public static final String TOPIC_PAY_RESULT = "topic-pay-result";
        public static final String TAGS_BUYCOURSE_RESULT_2COURSE = "tags-buycouseresult-2course";
        public static final String TAGS_BUYCOURSE_RESULT_2ORDER = "tags-buycouseresult-2order";
    }

    // 订单状态 ：
    public class Order{
        //0下单成功待支付，
        public static final int STATUS_APPLY = 0 ;
        //1支付成功订单完成
        public static final int STATUS_COMPLETE = 1;
        //2用户手动取消订单(未支付)
        public static final int STATUS_CHANEL = 2 ;
        //3.支付失败
        public static final int STATUS_PAY_FAIL = 3 ;
        //4.超时自动订单取消
        public static final int STATUS_OUT_TIME_CHANEL = 4 ;
    }

    // 订单状态 ：
    public class Pay{

        //0.支付中，1.支付成功，2.支付失败，4.支付超时
        public static final int STATUS_APPLY = 0 ;
        public static final int STATUS_SUCCESS = 1;
        public static final int STATUS_FAIL = 2 ;
        public static final int STATUS_OUT_TIME = 3;

        //支付方式:0余额直接，1支付宝，2微信,3银联
        public static final int PAY_TYPE_AMOUNT = 0 ;
        public static final int PAY_TYPE_ALIPAY = 1 ;
        public static final int PAY_TYPE_WXPAY = 2 ;
        public static final int PAY_TYPE_BLANK = 3 ;
    }

    public class CourseConstants{
        //课程状态，下线：0 ， 上线：1
        public static final int STATUS_OFF_LIINE = 0 ;
        public static final int STATUS_ON_LIINE = 1 ;
    }

    public class Course{
        public static final String COURSE_TYPE = "course::type";
    }
}
