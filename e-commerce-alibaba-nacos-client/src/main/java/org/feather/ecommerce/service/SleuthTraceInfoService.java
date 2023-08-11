package org.feather.ecommerce.service;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: SleuthTraceInfoService
 * @author: feather
 * @description:使用代码更直观 的看到sleuth 生成的相关跟踪信息
 * @since: 2023-08-09 7:26
 * @version: 1.0
 */
@Slf4j
@Service
public class SleuthTraceInfoService {
    /** brave.Tracer 跟踪对象 */
    private final Tracer tracer;

    public SleuthTraceInfoService(Tracer tracer) {
        this.tracer = tracer;
    }

    /**
     * <h2>打印当前的跟踪信息到日志中</h2>
     * */
    public void logCurrentTraceInfo() {

        log.info("Sleuth trace id: [{}]", tracer.currentSpan().context().traceId());
        log.info("Sleuth span id: [{}]", tracer.currentSpan().context().spanId());
    }
}
