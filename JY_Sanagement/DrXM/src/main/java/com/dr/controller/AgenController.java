package com.dr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dr.service.AgenService;
import com.dr.utile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @描述 代理后台
 * @创建人 mlk
 * @创建时间 2019/11/21 16:28
 * @修改人和其它信息
 */
@Controller
public class AgenController {
    @Autowired
    AgenService agenService;
    RedisUtile redisUtile;
    //登录校验
    @RequestMapping("alog")
    @ResponseBody
    public String alog(@RequestParam Map map, HttpServletRequest request) {
        //判断账号密码
        List<Map> map1 = agenService.agenSllog(map);
        //如果正确返回后台yes
        if(!map1.toString().equals("[]")){
            request.getSession().setAttribute("name",map.get("Username"));
            return "yes";
        }else {
            return "no";
        }
    }

    //主页面顶置
    @RequestMapping("agendz")
    @ResponseBody
    public String  agendz(){
        //获取商户总数
        Map agensummer = agenService.agensummer();
        //顶置今日订单流水信箱
        Map agendz = agenService.agendz();
        StringBuffer sb = new StringBuffer();

        //获取商户总数 今日订单 流水  信箱
        sb.append("{\"mr_number\":\"" + agensummer.get("mr_number") + "\",\"ag_order\":\"" + agendz.get("ag_order") + "\"," + "\"ag_waste\":\"" + agendz.get("ag_waste") + "\",\"agmess_number\":\"" + agendz.get("agmess_number") + "\"}");
        return sb.toString();

    }
    //主页数据可视化
    @RequestMapping("agenview")
    @ResponseBody
    public String agenview() throws UnsupportedEncodingException {
        //数据可视化
        List<Map> agenview = agenService.agenview();
      //[{monay=30000.0, months=06, nb=1}, {monay=45222.0, months=07, nb=1}, {monay=100110.0, months=08, nb=2}]
        //转为jsonobject格式
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(agenview));

        String encode = URLEncoder.encode(array.toString(), "utf-8");
        return encode;
    }
    //商户管理页面
    @RequestMapping("agenmer")
    @ResponseBody
    public String agenmer() throws UnsupportedEncodingException {
        //获取商户管理信息  agm_name,agm_adress,agm_phone
        List<Map> agenmergl = agenService.agenmergl();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(agenmergl));
        String encode = URLEncoder.encode(array.toString(), "utf-8");
        return encode;
    }
    //用户名
    @RequestMapping("allname")
    @ResponseBody
    public String allname(){

//获取redis中的value
        String name = redisUtile.getdata("allname");
        //页面常量name
       if(name==null){
           return "no";
       }else {
           return name;
       }
    }
    //输出redis中全局名称
    @RequestMapping("delallname")
    @ResponseBody
    public String delallname(){
        redisUtile.delete("allname");
       return "sucess";
    }
    //代理商注册
    @RequestMapping("agenzc")
    @ResponseBody
    public String agenzc(@RequestParam Map map){
        System.out.println("zhuce");
        System.out.println("-------------getkeyUsername"+map.get("Username"));

        int agenag = agenService.agenag(map);
        System.out.println(agenag);
        if(agenag!=0){
            return "sucess";
        }else {
            return "sb";
        }
    }
    //数据分析接口1
    @RequestMapping("R")
    public String R_1(@RequestParam Map map) throws Exception {
        //获取前台参数
        String username = (String)map.get("Username");
        System.out.println(username);
        System.out.println("R!!!!");
        R_Questionnaire r_questionnaire = new R_Questionnaire();
        r_questionnaire.Questionnaire();
        //构造JSON
        StringBuilder sb = new StringBuilder();
        sb.append("{\"Username\":\""+username+"\"}");
        String mess = sb.toString();
        JSONObject jsonObject = JSON.parseObject(mess);
        String info = URLEncoder.encode(jsonObject.toString(),"UTF-8");

        //跳转前端页面
        return "redirect:/base_tables_pricings.html";
    }

    //数据分析接口1
    @RequestMapping("R2")
    public String R_2(@RequestParam Map map) throws Exception {
        //获取前台参数
        String username = (String)map.get("Username");
        System.out.println(username);
        R_Defect r_Defect = new R_Defect();
        r_Defect.Defect();
        //构造JSON
        StringBuilder sb = new StringBuilder();
        sb.append("{\"Username\":\""+username+"\"}");
        String mess = sb.toString();
        JSONObject jsonObject = JSON.parseObject(mess);
        String info = URLEncoder.encode(jsonObject.toString(),"UTF-8");

        //跳转前端页面
        return "redirect:/base_tables_pricings.html";
    }

    //数据分析接口3
    @RequestMapping("R3")
    public String R_3(@RequestParam Map map) throws Exception {
        R_part r_part = new R_part();
        r_part.Part();
        //跳转前端页面
        return "redirect:/base_tables_pricings.html";
    }
    //修改个人信息
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ResponseBody
    public String Update_baocun(@RequestParam Map map) throws UnsupportedEncodingException {
        Object ag_name = map.get("ag_name");
        Object ag_pwd = map.get("ag_pwd");
        System.out.println(map);
        //调用代理修改接口
        int  i= agenService.UpdateAgentMapper(map);
        System.out.println(i);
        String info = URLEncoder.encode("UTF-8");
        if (i == 0) {
            System.out.println("修改失败");
            return "fail";
        } else {
            System.out.println("修改成功");
            return "success";
        }
    }


}
