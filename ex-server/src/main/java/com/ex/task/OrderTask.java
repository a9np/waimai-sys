package com.ex.task;

import com.ex.entity.Orders;
import com.ex.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    //每5秒执行
    @Scheduled(cron = "0 * * * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional
    public void processTimeoutOrder() {
        log.info("处理超时订单：{}", LocalDateTime.now());

        List<Orders> orders = orderMapper.getByStatusAndOrderTimeLT(
                Orders.PENDING_PAYMENT,
                LocalDateTime.now().plusMinutes(-15));
        if (orders != null && !orders.isEmpty()) {
            for (Orders order : orders) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
    public void processDeliveryOrder() {
        log.info("处理未完成订单：{}", LocalDateTime.now());

        List<Orders> orders = orderMapper.getByStatusAndOrderTimeLT(
                Orders.DELIVERY_IN_PROGRESS,
                LocalDateTime.now().plusHours(-1));
        if (orders != null && !orders.isEmpty()) {
            for (Orders order : orders) {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            }
        }
    }
}
