package com.dr.utile;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlGat {
    private  static final ClassPathXmlApplicationContext sc = new ClassPathXmlApplicationContext("xmlF/spring.xml");
    public static ClassPathXmlApplicationContext get(){
        return  sc;
    }
}
