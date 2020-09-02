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

    /**
     * 多实例完成条件变量名称
     */
    String MULTI_INSTANCE_VARIABLE_KEY = "pass";

    /**
     * 一票通过
     */
    String ONE_VOTE_PASS = "pass == true";

    /**
     * 一票否决
     */
    String ONE_PASS_REFUSE = "pass == false";

    /**
     * UserTask类型
     */
    interface user_task_type {

        /**
         * 普通UserTask
         */
        int NORMAL = 1;

        /**
         * 多实例
         */
        int MULTI_INSTANCE = 2;

        /**
         * 组任务
         */
        int GROUP = 3;
    }

}
