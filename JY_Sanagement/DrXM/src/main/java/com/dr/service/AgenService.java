package com.dr.service;

import com.dr.dao.Agenmapper;
import com.dr.utile.RedisUtile;
import com.dr.utile.XmlGat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @描述 代理实现类
 * @创建人 mlk
 * @创建时间 2019/11/21 16:30
 * @修改人和其它信息
 */
@Service
public class AgenService implements AgenServiceI {
    @Autowired
    Agenmapper agenmapper;
    RedisUtile redisUtile;
    //代理登录
    @Override
    public List<Map> agenSllog(Map map) {
        List<Map> map1 = agenmapper.agenSllog(map);

        for (Map ma :map1
             ) {
            Object ag_name = ma.get("ag_name");
            redisUtile.put("allname",ag_name);
        }

        return map1;
    }

    //获取商户总数
    @Override
    public Map agensummer() {

        return agenmapper.agensummer();
    }

    //顶置今日订单流水信箱
    @Override
    public Map agendz() {
        return agenmapper.agendz();
    }

    //新增商户数量 营业额 月份
    @Override
    public List<Map> agenview() {
        return agenmapper.agenview();
    }

    //商户管理
    @Override
    public List<Map> agenmergl() {
        return agenmapper.agenmergl();
    }

    @Override
    public int agenag(Map map) {
        int agenag = agenmapper.agenag(map);
        return agenag;
    }

    public AgenService(){
        System.out.println("注入成功");
    }

    //修改代理个人密码
    @Override
    public int UpdateAgentMapper(Map map) {

        return agenmapper.UpdateAgentMapper(map);
    }


}
