package com.tky.sentinel.fluid_control;


import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.tky.sentinel.persistent.WriteableNacos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Component
public class SentinelFlowCtl {

    private String dataId = "flow";

    @Value("${nacos.config.server-addr}")
    private String serverAddr;

    private static String group = "DEFAULT_GROUP";

    @PostConstruct
    public void  init() throws NacosException {
        System.out.println("-------------init-----------");



        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        // properties.put(PropertyKeyConst.NAMESPACE, "public");  // 指定namespace



        //  这一段参照“https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95”对应的页面的“使用 Nacos 配置规则”标题下的内容
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(serverAddr, group, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        // 这一段参照“https://github.com/alibaba/Sentinel/wiki/%E5%9C%A8%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E4%B8%AD%E4%BD%BF%E7%94%A8-Sentinel”对应页面的“FileDataSourceInit ”的代码
        //System.out.println(flowRuleDataSource.getProperty());
        WritableDataSource writableDataSource = new WriteableNacos<List<FlowRule>>(group, dataId, properties);
        // 流控规则
        WritableDataSourceRegistry.registerFlowDataSource(writableDataSource);
    }
}