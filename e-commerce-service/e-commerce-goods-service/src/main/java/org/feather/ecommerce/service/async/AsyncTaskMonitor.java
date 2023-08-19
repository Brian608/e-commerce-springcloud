package org.feather.ecommerce.service.async;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.feather.ecommerce.constant.AsyncTaskStatusEnum;
import org.feather.ecommerce.vo.AsyncTaskInfo;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.async
 * @className: AsyncTaskMonitor
 * @author: feather
 * @description: 异步任务执行监控切面
 * @since: 2023-08-17 21:36
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class AsyncTaskMonitor {

    private final AsyncTaskManager asyncTaskManager;


    public AsyncTaskMonitor(AsyncTaskManager asyncTaskManager) {
        this.asyncTaskManager = asyncTaskManager;
    }


    /**
     * 异步任务执行环绕切面
     * 环绕切面可以让我们在方法执行之前和执行之后做一些 额外的操作
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* org.feather.ecommerce.service.async.AsyncServiceImpl.*(..))")
    public Object taskHandle(ProceedingJoinPoint proceedingJoinPoint){
        // 获取taskId  调用异步任务传入的第二个参数
        String taskId=proceedingJoinPoint.getArgs()[1].toString();
        //获取任务信息 在提交任务的时候就已经放入到容器中
        AsyncTaskInfo taskInfo = asyncTaskManager.getTaskInfo(taskId);
        log.info("AsyncTaskMonitor is monitoring async task :[{}]",taskId);
        taskInfo.setStatus(AsyncTaskStatusEnum.RUNNING);
        //设置为运行状态，并重新放入容器
        asyncTaskManager.setTaskInfo(taskInfo);
        AsyncTaskStatusEnum status;
        Object result;
        try {
            //执行异步任务
            result=proceedingJoinPoint.proceed();
            status=AsyncTaskStatusEnum.SUCCESS;
        }catch (Throwable ex){
            //异步任务出现了异常
            result=null;
            status=AsyncTaskStatusEnum.FAILED;
            log.error("AsyncTaskMonitor: async task [{}] is failed, Error Info: [{}]",
                    taskId, ex.getMessage(), ex);
        }
        // 设置异步任务其他的信息, 再次重新放入到容器中
        taskInfo.setEndTime(new Date());
        taskInfo.setStatus(status);
        taskInfo.setTotalTime(String.valueOf(
                taskInfo.getEndTime().getTime() - taskInfo.getStartTime().getTime()
        ));
        asyncTaskManager.setTaskInfo(taskInfo);
        return result;

    }
}
