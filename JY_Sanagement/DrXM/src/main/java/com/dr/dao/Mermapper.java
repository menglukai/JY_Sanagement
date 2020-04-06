package com.dr.dao;

import java.util.List;
import java.util.Map;

/**
 *@描述   商家数据持久层
 *@创建人 mlk
 *@创建时间 2019/11/21 16:28
 *@修改人和其它信息
 */
public interface Mermapper {
    //登录 m_name m_pwd
    public List<Map>  mersecjy(Map map);
    //商户顶置信息
    public Map merdzsess();
    //月份订单比
    public List<Map> mermpkm();
    //商品数量比
    public List<Map> mercpkn();
    //门店管理
    public List<Map>  mermdgl();
    //员工管理
    public List<Map> mersfgl();
    //商品中心
    public List<Map> mercc();
    //会员表
    public  List<Map> mervip();
    //商家注册
    public int merzc(Map map);
    //删除员工
    public int satffdel(Map map);
    //分店内容
    //分店月份订单比
    public  List<Map> fd_mer_mpkm (Map map);
    //分店商品销量比
    public  List<Map>  fd_mer_spkc (Map map);
    //分店店铺管理
    public  List<Map> fd_mer_sp (Map map);
    //分店员工管理
    public  List<Map>  fd_mer_sf (Map map);
    //分店商品管理
    public  List<Map> fd_mer_sc (Map map);
    //分店vip用户管理
    public  List<Map> fd_mer_vip (Map map);

    //修改商家信息接口（cxy）
    public int  UpdateMerchantsMapper(Map map);


}
