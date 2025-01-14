package co.yixiang.utils;

import cn.hutool.core.date.DateUtil;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName OrderUtil
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/13
 **/
public class OrderUtil {

    /**
     * 时间戳订单号
     * @return
     */
    public static String orderSn(){
        Date date = DateUtil.date();
        return DateUtil.format(date,"yyyyMMddHHmmssSSS");
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s) * 1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /**
     * 获取精确到秒的时间戳
     * @return
     **/
    public static int getSecondTimestampTwo(){
        String timestamp = String.valueOf(new Date().getTime()/1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 获取精确到秒的时间戳
     * @return
     **/
    public static int dateToTimestamp(Date date){
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 获取订单状态名称
     * @param paid
     * @param status
     * @param shipping_type
     * @param refund_status
     * @return
     */
    public static String orderStatusStr(int paid,int status,
                                        int shipping_type,int refund_status){
        String statusName = "";
        if(paid == 0 && status == 0){
            statusName = "未支付";
        }else if(paid == 1 && status == 0 && shipping_type == 1 && refund_status == 0){
            statusName = "未发货";
        }else if(paid == 1 &&  status == 0 && shipping_type == 2 && refund_status == 0){
            statusName = "未核销";
        }else if(paid == 1 && status == 1 && shipping_type ==1 && refund_status == 0){
            statusName = "待收货";
        }else if(paid == 1 && status == 1 && shipping_type == 2 && refund_status == 0){
            statusName = "未核销";
        }else if(paid == 1 && status == 2 && refund_status == 0){
            statusName = "待评价";
        }else if(paid == 1 && status == 3 && refund_status == 0){
            statusName = "已完成";
        }else if(paid == 1 && refund_status == 1){
            statusName = "退款中";
        }else if(paid == 1 && refund_status == 2){
            statusName = "已退款";
        }

        return statusName;
    }


    /**
     * 获取状态数值
     * @param paid
     * @param status
     * @param refund_status
     * @return
     */
    public static int orderStatus(int paid,int status,int refund_status){
        //todo  1-已付款 2-未发货 3-退款中 4-待收货 5-待评价 6-已完成 7-已退款
        int _status = 0;

        if(paid == 0 && status == 0 && refund_status == 0){
            _status = 1;
        }else if(paid == 1 && status == 0 && refund_status == 0){
            _status = 2;
        }else if(paid == 1 && refund_status == 1){
            _status = 3;
        }else if(paid == 1 && status == 1 && refund_status == 0){
            _status = 4;
        }else if(paid == 1 && status == 2 && refund_status == 0){
            _status = 5;
        }else if(paid == 1 && status == 3 && refund_status == 0){
            _status = 6;
        }else if(paid == 1 && refund_status == 2){
            _status =7 ;
        }

        return _status;

    }


    /**
     * 支付方式
     * @param pay_type
     * @param paid
     * @return
     */
    public static String payTypeName(String pay_type, int paid){
        String payTypeName = "";
        if(paid == 1 ){
            switch(pay_type){
                case "weixin":
                    payTypeName = "微信支付";
                    break;
                case "yue":
                    payTypeName = "余额支付";
                    break;
                case "offline":
                    payTypeName = "线下支付";
                    break;
                default:
                    payTypeName = "其他支付";
                    break;
            }
        }else{
            switch(pay_type){
                default:
                    payTypeName = "未支付";
                    break;
                case "offline":
                    payTypeName = "线下支付";
                    break;
            }
        }


        return payTypeName;
    }

    //todo 订单类型
    public static String orderType(int pink_id){
        return "普通订单";
    }


}
