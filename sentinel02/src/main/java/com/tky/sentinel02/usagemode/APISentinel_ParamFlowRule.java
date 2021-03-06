package com.tky.sentinel02.usagemode;

//热点参数案例
import java.util.Collections;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;

public class APISentinel_ParamFlowRule {

    private static final int PARAM_A = 1;
    private static final int PARAM_B = 2;
    private static final int PARAM_C = 3;
    private static final int PARAM_D = 4;

    private static final Integer[] PARAMS = new Integer[] {PARAM_A, PARAM_B, PARAM_C, PARAM_D};

    private static final String RESOURCE_KEY = "resA";

    public static void main(String[] args) throws Exception {
        initParamFlowRules();

        final int threadCount = 20;
        ParamFlowQpsRunner<Integer> runner = new ParamFlowQpsRunner<>(PARAMS, RESOURCE_KEY, threadCount, 120);
        runner.tick();

        Thread.sleep(1000);
        runner.simulateTraffic();
    }

    private static void initParamFlowRules() {
        ParamFlowRule rule = new ParamFlowRule(RESOURCE_KEY)
                .setParamIdx(0)
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                //.setDurationInSec(3)
                //.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
                //.setMaxQueueingTimeMs(600)
                .setCount(5);

        ParamFlowItem item = new ParamFlowItem().setObject(String.valueOf(PARAM_B))
                .setClassType(int.class.getName())
                .setCount(10);
        rule.setParamFlowItemList(Collections.singletonList(item));
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
    }
}