package spring.aop.kotlin.order

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory

@Aspect
class AspectV4Pointcut {

    @Around("spring.aop.kotlin.order.Pointcuts.allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        LOG.info("[LOG] {}", joinPoint.signature) // join point 시그니처
        return joinPoint.proceed()
    }

    // allOrder 적용 패키지 && *Service 클래스
    @Around("spring.aop.kotlin.order.Pointcuts.allOrderAndService()")
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
        try {
            LOG.info("[트랜잭션 시작] {}", joinPoint.signature)
            val result = joinPoint.proceed()
            LOG.info("[트랜잭션 커밋] {}", joinPoint.signature)
            return result
        } catch (e: Exception) {
            LOG.info("[트랜잭션 롤백] {}", joinPoint.signature)
            throw e
        } finally {
            LOG.info("[리소스 릴리즈] {}", joinPoint.signature)
        }
    }

    companion object {
        val LOG = LoggerFactory.getLogger(AspectV4Pointcut::class.java)
    }
}
