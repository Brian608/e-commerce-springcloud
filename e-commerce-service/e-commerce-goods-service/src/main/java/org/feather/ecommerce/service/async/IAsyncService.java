package org.feather.ecommerce.service.async;

import org.feather.ecommerce.goods.GoodsInfo;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.async
 * @className: IAsyncService
 * @author: feather
 * @description: 异步服务接口定义
 * @since: 2023-08-17 6:58
 * @version: 1.0
 */
public interface IAsyncService {

    /**
     * <h2>异步将商品信息保存下来</h2>
     * */
    void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId);
}
