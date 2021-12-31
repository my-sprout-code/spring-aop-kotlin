package spring.aop.kotlin

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spring.aop.kotlin.order.AspectV6Advice
import spring.aop.kotlin.order.OrderRepository
import spring.aop.kotlin.order.OrderService

@SpringBootTest
@Import(AspectV6Advice::class)
class AopTest {

    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Test
    fun aopInfo() {
        LOG.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService))
        LOG.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository))
    }

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Test
    fun exception() {
        Assertions.assertThatThrownBy { orderService.orderItem("ex") }
            .isInstanceOf(IllegalStateException::class.java)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(AopTest::class.java)
    }
}
