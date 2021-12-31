package spring.aop.kotlin.order

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order

class AspectV5Order {

    @Aspect
    @Order(2)
    class LogAspect {
        @Around("spring.aop.kotlin.order.Pointcuts.allOrder()")
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            LOG.info("[log] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }

    @Aspect
    @Order(1)
    class TxAspect {
        @Around("spring.aop.kotlin.order.Pointcuts.allOrderAndService()")
        fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
            return try {
                LOG.info("[트랜잭션 시작] {}", joinPoint.signature)
                val result = joinPoint.proceed()
                LOG.info("[트랜잭션 커밋] {}", joinPoint.signature)
                result
            } catch (e: Exception) {
                LOG.info("[트랜잭션 롤백] {}", joinPoint.signature)
                throw e
            } finally {
                LOG.info("[리소스 릴리즈] {}", joinPoint.signature)
            }
        }
    }

    companion object {
        val LOG = LoggerFactory.getLogger(AspectV5Order::class.java)
    }
}
