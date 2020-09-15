package com.tky.sentinel.persistent;

import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;

import java.util.Properties;

public class WriteableNacos<T> implements WritableDataSource<T> {

    private String group;

    private String dataId;

    private Properties properties;

    public WriteableNacos(String group, String dataId, Properties properties){
        this.group = group;
        this.dataId = dataId;
        this.properties = properties;
    }


    //当sentinel update 时候
    @Override
    public void write(T value) throws Exception {
        System.out.println("-------------write-----------");

        String s = JSON.toJSONString(value);
        System.out.println(s);
        ConfigService configService = NacosFactory.createConfigService((String)properties.get(PropertyKeyConst.SERVER_ADDR));
        boolean isPublishOk = configService.publishConfig(this.dataId, this.group, s);
        System.out.println(isPublishOk);
    }

    @Override
    public void close() throws Exception {

    }
}