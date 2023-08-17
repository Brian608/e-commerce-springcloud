package org.feather.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.ecommerce.constant.AsyncTaskStatusEnum;

import java.util.Date;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.vo
 * @className: AsyncTaskInfo
 * @author: feather
 * @description: 异步任务执行信息
 * @since: 2023-08-17 7:00
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsyncTaskInfo {

    /** 异步任务 id */
    private String taskId;

    /** 异步任务执行状态 */
    private AsyncTaskStatusEnum status;

    /** 异步任务开始时间 */
    private Date startTime;

    /** 异步任务结束时间 */
    private Date endTime;

    /** 异步任务总耗时 */
    private String totalTime;
}
