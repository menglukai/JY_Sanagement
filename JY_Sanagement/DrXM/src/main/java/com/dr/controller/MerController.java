package com.dr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dr.service.MerServiceI;
import com.dr.utile.RedisUtile;

import com.dr.utile.sendsms;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述 商户后台
 * @创建人 mlk
 * @创建时间 2019/11/21 16:27
 * @修改人和其它信息
 */
@Controller
@Scope("prototype")
public class MerController {
    @Autowired
    MerServiceI merServiceI;
    RedisUtile redisUtile;


    //用户登录
    @RequestMapping("merlog")
    @ResponseBody
    public String merlog(@RequestParam Map map, HttpServletRequest request) {
        List<Map> mersecjy = merServiceI.mersecjy(map);
        //如果正确返回后台yes
        if (!mersecjy.toString().equals("[]")) {
            request.getSession().setAttribute("mername", map.get("Username"));
            return "yes";
        } else {
            return "no";
        }
    }

    //顶置信息今日订单，订单总数，昨日订单，信箱 (td_order,all_order,yd_order,m_mess)
    @RequestMapping("merdz")
    @ResponseBody
    public String merdz() {
        String name = redisUtile.getdata("merallname");
        if(name.equals("zhangsan")||name.equals("lisi")||name.equals("sxc")){
            Map merdzsess = merServiceI.merdzsess();
            StringBuffer sb = new StringBuffer();
            //获取商户总数 今日订单 流水  信箱
            sb.append("{\"td_order\":\"" + merdzsess.get("td_order") + "\",\"all_order\":\"" + merdzsess.get("all_order") + "\"," + "\"yd_order\":\"" + merdzsess.get("yd_order") + "\",\"m_mess\":\"" + merdzsess.get("m_mess") + "\"}");
            return sb.toString();
        }else {
            return "";
        }

    }

    //月份订单数可视化图一   订单数，月份 （nb,months）
    @RequestMapping("mervw1")
    @ResponseBody
    public String mervw1() throws UnsupportedEncodingException {
        String name = redisUtile.getdata("merallname");
        if (name.equals("zhangsan") || name.equals("lisi")) {
            HashMap map = new HashMap();
            map.put("fd_name", name);
            List<Map> maps = merServiceI.fd_mer_mpkm(map);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(maps));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else if (name.equals("sxc")) {
            List<Map> mermpkm = merServiceI.mermpkm();

            JSONArray array = JSONArray.parseArray(JSON.toJSONString(mermpkm));

            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else {
            return "";
        }
    }

    //商品销售占比可视化二 商品名称 数量  (c_name,nb)
    @RequestMapping("mervw2")
    @ResponseBody
    public String mervw2() throws UnsupportedEncodingException {
        String name = redisUtile.getdata("merallname");
        if (name.equals("zhangsan") || name.equals("lisi")) {
            HashMap map = new HashMap();
            map.put("fd_name", name);
            List<Map> maps = merServiceI.fd_mer_spkc(map);

            JSONArray array = JSONArray.parseArray(JSON.toJSONString(maps));
            System.out.println(array.toString());
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else if (name.equals("sxc")) {
            List<Map> mercpkn = merServiceI.mercpkn();

            JSONArray array = JSONArray.parseArray(JSON.toJSONString(mercpkn));

            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else {
            return "";
        }
    }

    //门店管理 门店名称 门店地址 店长电话 (ms_name,ms_adress,ms_phone)
    @RequestMapping("mershop")
    @ResponseBody
    public String mermd() throws UnsupportedEncodingException {
        String name = redisUtile.getdata("merallname");
        if (name.equals("zhangsan") || name.equals("lisi")) {
            HashMap map = new HashMap();
            map.put("fd_name", name);
            List<Map> maps = merServiceI.fd_mer_sp(map);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(maps));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else if (name.equals("sxc")) {
            List<Map> mermdgl = merServiceI.mermdgl();
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(mermdgl));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else {
            return "";
        }
    }

    //员工管理 员工姓名 所在店铺 联系方式 职位 (mstaff_name,madr_name,mstaff_phone,mstaff_job)
    @RequestMapping("mersfat")
    @ResponseBody
    public String mersfat() throws UnsupportedEncodingException {
        String name = redisUtile.getdata("merallname");
        if (name.equals("zhangsan") || name.equals("lisi")) {
            HashMap map = new HashMap();
            map.put("fd_name", name);
            List<Map> maps = merServiceI.fd_mer_sf(map);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(maps));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else if (name.equals("sxc")) {
            List<Map> mersfgl = merServiceI.mersfgl();
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(mersfgl));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else {
            return "";
        }
    }

    //商品中心  商品名称，销量，单价 (c_name,sb,c_price)
    @RequestMapping("mercomm")
    @ResponseBody
    public String mercomm() throws UnsupportedEncodingException {
        String name = redisUtile.getdata("merallname");
        if (name.equals("zhangsan") || name.equals("lisi")) {
            HashMap map = new HashMap();
            map.put("fd_name", name);
            List<Map> maps = merServiceI.fd_mer_sc(map);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(maps));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else if (name.equals("sxc")) {
            List<Map> mercc = merServiceI.mercc();
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(mercc));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        }else{
            return "";
        }
    }

    //会员表 会员号 卡内余额 办卡地址 会员电话 (vip_number,vip_balance,vip_name,vip_phone)
    @RequestMapping("mervip")
    @ResponseBody
    public String mervip() throws UnsupportedEncodingException {
        String name = redisUtile.getdata("merallname");
        if (name.equals("zhangsan") || name.equals("lisi")) {
            HashMap map = new HashMap();
            map.put("fd_name", name);
            List<Map> maps = merServiceI.fd_mer_vip(map);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(maps));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else if (name.equals("sxc")) {
            List<Map> mervip = merServiceI.mervip();
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(mervip));
            String encode = URLEncoder.encode(array.toString(), "utf-8");
            return encode;
        } else {
            return "";
        }
    }

    //全局用户名
    @RequestMapping("merallname")
    @ResponseBody
    public String allname() {


        String name = redisUtile.getdata("merallname");
        //页面常量name
        if (name == null) {
            return "no";
        } else {
            return name;
        }
    }

    //输出redis中全局名称
    @RequestMapping("delallmername")
    @ResponseBody
    public String delallmername() {

        redisUtile.delete("merallname");
        return "sucess";
    }

    //商家注册
    @RequestMapping("merzc")
    @ResponseBody
    public String merzc(@RequestParam Map map) {
        String phone = (String) map.get("phone");
        String meryzm = redisUtile.getdata("meryzm");
        System.out.println("来到验证码验证");
        System.out.println(phone);
        int i;
        if(meryzm.equals(phone)){
             i = merServiceI.merzc(map);
        }else {
            i=0;
        }
        if (i == 0) {
            return "sb";
        } else {
            return "sucess";
        }
    }

    //删除职员    mersfatdel  mstaffname
    @RequestMapping("mersfatdel")
    @ResponseBody
    public String mersfatdel(@RequestParam Map map) {

        int i = merServiceI.satffdel(map);
        if (i == 0) {
            return "sb";
        } else {
            return "sucess";
        }
    }
    //发送验证码
    @RequestMapping("meryzm")
    @ResponseBody
    public String meryzm(@RequestParam Map map) {
        //使用发送短信类

        String yzm = (String) map.get("phone");
        String messsome = sendsms.messsome(yzm);
        redisUtile.put("meryzm",messsome);
        return "yes";
    }
    //修改个人信息
    @RequestMapping(value = "/updateMerchant",method = RequestMethod.GET)
    @ResponseBody
    public String Update_baocun(@RequestParam Map map) throws UnsupportedEncodingException {
        //获取商家个人信息
        Object agent_name = map.get("cus_name");
        Object agent_password = map.get("cus_password");
        System.out.println(map);
        //调用商家修改接口
        int  i= merServiceI.UpdateMerchantsMapper(map);
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
