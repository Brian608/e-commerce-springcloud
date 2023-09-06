package org.feather.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.stream.DefaultReceiveService;
import org.feather.ecommerce.stream.featherAuto.FeatherReceiveService;
import org.feather.ecommerce.vo.FeatherMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: MessageController
 * @author: feather
 * @description:
 * @since: 2023-09-06 21:36
 * @version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {
    private final DefaultReceiveService defaultReceiveService;

    private final FeatherReceiveService featherReceiveService;

    public MessageController(DefaultReceiveService defaultReceiveService, FeatherReceiveService featherReceiveService) {
        this.defaultReceiveService = defaultReceiveService;
        this.featherReceiveService = featherReceiveService;
    }

    @GetMapping("/default")
    public  void defaultSend(){
        defaultReceiveService.receiveMessage(FeatherMessage.defaultMessage());
    }

    @GetMapping("/feather")
    public  void featherSend(){
        featherReceiveService.receiveMessage(FeatherMessage.defaultMessage());
    }
}
