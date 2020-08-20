package com.example.activiti.business.constant;

/**
 * @author hjs
 * @date 2020/8/18
 * @description
 */
public interface ActivitiConstant {
    /**
     * 流程变量名称
     */
    String VARIABLE_KEY = "status";

    /**
     * 审批意见
     */
    interface advice {
        /**
         * 通过
         */
        String ADVICE_PASS = "pass";

        /**
         * 驳回
         */
        String ADVICE_REFUSE = "refuse";

        /**
         * 两种情况：
         * 1、存在下一级审批人
         * 2、驳回时指定下一级审批人，可将流程打回到当前审批人之前除发起人外的任意审批人
         */
        String ADVICE_LOOP = "loop";
    }

}
