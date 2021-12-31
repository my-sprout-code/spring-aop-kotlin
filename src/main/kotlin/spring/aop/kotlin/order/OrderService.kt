package spring.aop.kotlin.order

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
open class OrderService(private val orderRepository: OrderRepository) {

    open fun orderItem(itemId: String) {
        LOG.info("[orderService] 실행")
        orderRepository.save(itemId)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(OrderService::class.java)
    }
}
