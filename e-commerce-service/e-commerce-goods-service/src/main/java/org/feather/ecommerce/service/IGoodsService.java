package org.feather.ecommerce.service;

import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.goods.DeductGoodsInventory;
import org.feather.ecommerce.goods.GoodsInfo;
import org.feather.ecommerce.goods.SimpleGoodsInfo;
import org.feather.ecommerce.vo.PageSimpleGoodsInfo;

import java.util.List;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service
 * @className: IGoodsService
 * @author: feather
 * @description: 商品微服务相关服务接口定义
 * @since: 2023-08-17 6:56
 * @version: 1.0
 */

public interface IGoodsService {

    /**
     * <h2>根据 TableId 查询商品详细信息</h2>
     * */
    List<GoodsInfo> getGoodsInfoByTableId(TableId tableId);

    /**
     * <h2>获取分页的商品信息</h2>
     * */
    PageSimpleGoodsInfo getSimpleGoodsInfoByPage(int page);

    /**
     * <h2>根据 TableId 查询简单商品信息</h2>
     * */
    List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId);

    /**
     * <h2>扣减商品库存</h2>
     * */
    Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories);
}
