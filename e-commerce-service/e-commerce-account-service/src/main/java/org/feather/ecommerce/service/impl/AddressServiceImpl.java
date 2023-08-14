package org.feather.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.feather.ecommerce.account.AddressInfo;
import org.feather.ecommerce.common.TableId;
import org.feather.ecommerce.dao.EcommerceAddressDao;
import org.feather.ecommerce.entity.EcommerceAddress;
import org.feather.ecommerce.filter.AccessContext;
import org.feather.ecommerce.service.IAddressService;
import org.feather.ecommerce.vo.LoginUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.service.impl
 * @className: AddressServiceImpl
 * @author: feather
 * @description:
 * @since: 2023-08-14 21:03
 * @version: 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl  implements IAddressService {

    private  final EcommerceAddressDao addressDao;

    public AddressServiceImpl(EcommerceAddressDao ecommerceAddressDao) {
        this.addressDao = ecommerceAddressDao;
    }

    /**
     * <h2>存储多个地址信息</h2>
     * */
    @Override
    public TableId createAddressInfo(AddressInfo addressInfo) {

        // 不能直接从参数中获取用户的 id 信息
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 将传递的参数转换成实体对象
        List<EcommerceAddress> ecommerceAddresses = addressInfo.getAddressItems().stream()
                .map(a -> EcommerceAddress.to(loginUserInfo.getId(), a))
                .collect(Collectors.toList());

        // 保存到数据表并把返回记录的 id 给调用方
        List<EcommerceAddress> savedRecords = addressDao.saveAll(ecommerceAddresses);
        List<Long> ids = savedRecords.stream()
                .map(EcommerceAddress::getId).collect(Collectors.toList());
        log.info("create address info: [{}], [{}]", loginUserInfo.getId(),
                JSON.toJSONString(ids));

        return new TableId(
                ids.stream().map(TableId.Id::new).collect(Collectors.toList())
        );
    }

    @Override
    public AddressInfo getCurrentAddressInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 根据 userId 查询到用户的地址信息, 再实现转换
        List<EcommerceAddress> ecommerceAddresses = addressDao.findAllByUserId(
                loginUserInfo.getId()
        );
        List<AddressInfo.AddressItem> addressItems = ecommerceAddresses.stream()
                .map(EcommerceAddress::toAddressItem)
                .collect(Collectors.toList());

        return new AddressInfo(loginUserInfo.getId(), addressItems);
    }

    @Override
    public AddressInfo getAddressInfoById(Long id) {

        EcommerceAddress ecommerceAddress = addressDao.findById(id).orElse(null);
        if (null == ecommerceAddress) {
            throw new RuntimeException("address is not exist");
        }

        return new AddressInfo(
                ecommerceAddress.getUserId(),
                Collections.singletonList(ecommerceAddress.toAddressItem())
        );
    }

    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {

        List<Long> ids = tableId.getIds().stream()
                .map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get address info by table id: [{}]", JSON.toJSONString(ids));

        List<EcommerceAddress> ecommerceAddresses = addressDao.findAllById(ids);
        if (CollectionUtils.isEmpty(ecommerceAddresses)) {
            return new AddressInfo(-1L, Collections.emptyList());
        }

        List<AddressInfo.AddressItem> addressItems = ecommerceAddresses.stream()
                .map(EcommerceAddress::toAddressItem)
                .collect(Collectors.toList());

        return new AddressInfo(
                ecommerceAddresses.get(0).getUserId(), addressItems
        );
    }
}
