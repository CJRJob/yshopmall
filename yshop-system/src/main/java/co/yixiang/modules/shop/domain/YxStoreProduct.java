package co.yixiang.modules.shop.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-04
*/
@Entity
@Data
@Table(name="yx_store_product")
public class YxStoreProduct implements Serializable {

    // 商品id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 商户Id(0为总后台管理员创建,不为0的时候是商户后台创建)
    @Column(name = "mer_id",nullable = false)
    private Integer merId;

    // 商品图片
    @Column(name = "image",nullable = false)
    @NotBlank(message = "请上传商品图片")
    private String image;

    // 轮播图
    @Column(name = "slider_image",nullable = false)
    @NotBlank(message = "请上传轮播图")
    private String sliderImage;

    // 商品名称
    @Column(name = "store_name",nullable = false)
    @NotBlank(message = "请填写商品名称")
    private String storeName;

    // 商品简介
    @Column(name = "store_info",nullable = false)
    @NotBlank(message = "请填写商品简介")
    private String storeInfo;

    // 关键字
    @Column(name = "keyword",nullable = false)
    @NotBlank(message = "请填写关键字")
    private String keyword;

    // 产品条码（一维码）
    @Column(name = "bar_code",nullable = false)
    private String barCode;

    // 分类id
    @Column(name = "cate_id",nullable = false)
    @NotBlank(message = "请选择分类")
    private String cateId;

    // 商品价格
    @Column(name = "price",nullable = false)
    @Min(value = 0)
    private BigDecimal price;

    // 会员价格
    @Column(name = "vip_price",nullable = false)
    @Min(value = 0)
    private BigDecimal vipPrice;

    // 市场价
    @Column(name = "ot_price",nullable = false)
    @Min(value = 0)
    private BigDecimal otPrice;

    // 邮费
    @Column(name = "postage",nullable = false)
    @Min(value = 0)
    private BigDecimal postage;

    // 单位名
    @Column(name = "unit_name",nullable = false)
    @NotBlank(message = "请填写单位")
    private String unitName;

    // 排序
    @Column(name = "sort",nullable = false)
    @Min(value = 0)
    private Integer sort;

    // 销量
    @Column(name = "sales",nullable = false)
    @Min(value = 0)
    private Integer sales;

    // 库存
    @Column(name = "stock",nullable = false)
    @Min(value = 0)
    private Integer stock;

    // 状态（0：未上架，1：上架）
    @Column(name = "is_show",columnDefinition="int default 1")
    private Integer isShow;

    // 是否热卖
    @Column(name = "is_hot",columnDefinition="int default 0")
    private Integer isHot;

    // 是否优惠
    @Column(name = "is_benefit",columnDefinition="int default 0")
    private Integer isBenefit;

    // 是否精品
    @Column(name = "is_best",columnDefinition="int default 0")
    private Integer isBest;

    // 是否新品
    @Column(name = "is_new",columnDefinition="int default 0")
    private Integer isNew;

    // 产品描述
    @Column(name = "description",nullable = false)
    @NotBlank(message = "产品描述")
    private String description;

    // 添加时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 是否包邮
    @Column(name = "is_postage",columnDefinition="int default 0")
    private Integer isPostage;

    // 是否删除
    @Column(name = "is_del",columnDefinition="int default 0")
    private Integer isDel;

    // 商户是否代理 0不可代理1可代理
    @Column(name = "mer_use",nullable = false)
    private Integer merUse;

    // 获得积分
    @Column(name = "give_integral",columnDefinition="int default 0")
    @Min(value = 0)
    private BigDecimal giveIntegral;

    // 成本价
    @Column(name = "cost",columnDefinition="int default 0")
    @Min(value = 0)
    private BigDecimal cost;

    // 秒杀状态 0 未开启 1已开启
    @Column(name = "is_seckill",columnDefinition="int default 0")
    private Integer isSeckill;

    // 砍价状态 0未开启 1开启
    @Column(name = "is_bargain",columnDefinition="int default 0")
    private Integer isBargain;

    // 是否优品推荐
    @Column(name = "is_good",columnDefinition="int default 0")
    private Integer isGood;

    // 虚拟销量
    @Column(name = "ficti",columnDefinition="int default 0")
    private Integer ficti;

    // 浏览量
    @Column(name = "browse",columnDefinition="int default 0")
    private Integer browse;

    // 产品二维码地址(用户小程序海报)
    @Column(name = "code_path",nullable = false)
    private String codePath;

    // 淘宝京东1688类型
    @Column(name = "soure_link")
    private String soureLink;

    public void copy(YxStoreProduct source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}