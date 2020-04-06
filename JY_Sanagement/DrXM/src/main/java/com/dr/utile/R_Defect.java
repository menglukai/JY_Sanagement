package com.dr.utile;

import org.rosuda.REngine.Rserve.RConnection;

import static com.dr.utile.StartRserve.launchRserve;

/**
 * @Author yhz
 * @Data 1:13 2019/11/27
 */
public class R_Defect {
    public  void  Defect() throws Exception {
        //差注册表
        Process rp = Runtime.getRuntime().exec("reg query HKLM\\Software\\R-core\\R");
        StreamHog regHog = new StreamHog(rp.getInputStream(), true);
        rp.waitFor();
        regHog.join();
        String installPath = regHog.getInstallPath();
        System.out.println(installPath+"?????????????");
        launchRserve(installPath+"\\bin\\R.exe");
        //创建R链接
        RConnection rc = new RConnection();
        //指定文件位置
        String fileName = "F:/R_WorkSpeace/dr2.R";
        //服务器注册文件信息
        rc.assign("filename",fileName);
        //执行
        rc.eval("source(filename)");
        System.out.println("R结束");
    }

}
