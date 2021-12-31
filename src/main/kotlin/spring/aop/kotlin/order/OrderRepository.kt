package spring.aop.kotlin.order

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
open class OrderRepository {

    open fun save(itemId: String): String {
        LOG.info("[orderRepository] 실행")
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!")
        }
        return "ok"
    }

    companion object {
        val LOG = LoggerFactory.getLogger(OrderRepository::class.java)
    }
}
