package spring.aop.kotlin.order

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory

@Aspect
class AspectV1 {

    @Around("execution(* spring.aop.kotlin.order..*(..))")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        LOG.info("[LOG] {}", joinPoint.signature) // join point 시그니처
        return joinPoint.proceed()
    }

    companion object {
        val LOG = LoggerFactory.getLogger(AspectV1::class.java)
    }
}
