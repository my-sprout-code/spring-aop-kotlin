package spring.aop.kotlin.order

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory

@Aspect
class AspectV6Advice {

    @Before("spring.aop.kotlin.order.Pointcuts.allOrderAndService()")
    fun doBefore(joinPoint: JoinPoint) {
        LOG.info("[before] {}", joinPoint.signature)
    }

    @AfterReturning(value = "spring.aop.kotlin.order.Pointcuts.allOrderAndService()", returning = "result")
    fun doReturn(joinPoint: JoinPoint, result: Any?) {
        LOG.info("[return] {} return={}", joinPoint.signature, result)
    }

    @AfterThrowing(value = "spring.aop.kotlin.order.Pointcuts.allOrderAndService()", throwing = "ex")
    fun doReturn(joinPoint: JoinPoint, ex: Exception) {
        LOG.info("[ex] {} message={}", joinPoint.signature, ex)
    }

    @After(value = "spring.aop.kotlin.order.Pointcuts.allOrderAndService()")
    fun doAfter(joinPoint: JoinPoint) {
        LOG.info("[after] {}", joinPoint.signature)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(AspectV6Advice::class.java)
    }
}
