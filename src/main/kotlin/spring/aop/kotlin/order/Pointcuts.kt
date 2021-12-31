package spring.aop.kotlin.order

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {

    @Pointcut("execution(* spring.aop.kotlin.order..*(..))")
    fun allOrder() {
    }

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {
    }

    @Pointcut("allOrder() && allService()")
    fun allOrderAndService() {
    }

}