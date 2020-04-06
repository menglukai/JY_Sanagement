package com.dr.dao;

import java.util.List;
import java.util.Map;

/**
 *@描述    代理数据持久层
 *@创建人 mlk
 *@创建时间 2019/11/21 16:29
 *@修改人和其它信息
 */
public interface Agenmapper {
    //代理登录验证
    public List<Map> agenSllog(Map map);
    //商户总数
    public Map agensummer();
    //顶置今日订单流水信箱
    public Map agendz();
    //新增商户数量 营业额 月份
    public List<Map> agenview();
    //商户管理
    public List<Map> agenmergl();
    //注册
    public int agenag(Map map);
    //修改代理信息接口

    public int UpdateAgentMapper(Map map);

}
