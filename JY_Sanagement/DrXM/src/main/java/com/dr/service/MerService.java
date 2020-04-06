package com.dr.service;

import com.dr.dao.Agenmapper;
import com.dr.dao.Mermapper;
import com.dr.utile.RedisUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @描述 商家实现类
 * @创建人 mlk
 * @创建时间 2019/11/21 16:30
 * @修改人和其它信息
 */
@Service
public class MerService implements MerServiceI {
    @Autowired
    Mermapper merServiceI;
    RedisUtile redisUtile;

    //用户登录
    @Override
    public List<Map> mersecjy(Map map) {
        List<Map> mersecjy = merServiceI.mersecjy(map);
        for (Map ma : mersecjy
        ) {
            Object mer_name = ma.get("m_name");
            redisUtile.put("merallname", mer_name);
        }
        return mersecjy;
    }

    //顶置信息今日订单，订单总数，昨日订单，信箱 (td_order,all_order,yd_order,m_mess)
    @Override
    public Map merdzsess() {

        return merServiceI.merdzsess();
    }
//月份订单数可视化图一   订单数，月份 （nb,months）
    @Override
    public List<Map> mermpkm() {

        return merServiceI.mermpkm();
    }
//商品销售占比可视化二 商品名称 数量  (c_name,nb)
    @Override
    public List<Map> mercpkn() {
        return merServiceI.mercpkn();
    }
//门店管理 门店名称 门店地址 店长电话 (ms_name,ms_adress,ms_phone)
    @Override
    public List<Map> mermdgl() {
        return merServiceI.mermdgl();
    }
//员工管理 员工姓名 所在店铺 联系方式 职位 (mstaff_name,madr_name,mstaff_phone,mstaff_job)
    @Override
    public List<Map> mersfgl() {
        return merServiceI.mersfgl();
    }
//商品中心  商品名称，销量，单价 (c_name,sb,c_price)
    @Override
    public List<Map> mercc() {
        return merServiceI.mercc();
    }
//会员表 会员号 卡内余额 办卡地址 会员电话 (vip_number,vip_balance,vip_name,vip_phone)
    @Override
    public List<Map> mervip() {
        return merServiceI.mervip();
    }
//商家注册  需要验证码
    @Override
    public int merzc(Map map) {
      int i=  merServiceI.merzc(map);
        return i;
    }


    //删除员工
    @Override
    public int satffdel(Map map) {
        int u = merServiceI.satffdel(map);
        return u;
    }
     //分店月份订单比
    @Override
    public List<Map> fd_mer_mpkm(Map map) {
        return merServiceI.fd_mer_mpkm(map);
    }
    //分店商品销量
    @Override
    public List<Map> fd_mer_spkc(Map map) {
        return merServiceI.fd_mer_spkc(map);
    }
    //分店商铺
    @Override
    public List<Map> fd_mer_sp(Map map) {
        return merServiceI.fd_mer_sp(map);
    }
    //分店员工
    @Override
    public List<Map> fd_mer_sf(Map map) {
        return merServiceI.fd_mer_sf(map);
    }
    //分店商品
    @Override
    public List<Map> fd_mer_sc(Map map) {
        return merServiceI.fd_mer_sc(map);
    }
    //分店vip
    @Override
    public List<Map> fd_mer_vip(Map map) {
        return merServiceI.fd_mer_vip(map);
    }
    @Override
    public int UpdateMerchantsMapper(Map map) {
        return merServiceI.UpdateMerchantsMapper(map);
    }


}
