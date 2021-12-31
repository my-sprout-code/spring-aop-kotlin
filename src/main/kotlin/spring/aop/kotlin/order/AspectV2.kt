package spring.aop.kotlin.order

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory

@Aspect
class AspectV2 {

    @Pointcut("execution(* spring.aop.kotlin.order..*(..))")
    fun allOrder() {} // pointcut signature

    @Around("allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        LOG.info("[LOG] {}", joinPoint.signature) // join point 시그니처
        return joinPoint.proceed()
    }

    companion object {
        val LOG = LoggerFactory.getLogger(AspectV2::class.java)
    }
}
