package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


// AOP = Aspect Oriented Programming (관점 지향 프로그래밍)
// 프록시를 사용한 기술
// helloController는 만들어진 가짜 스프링 빈(프록시)을 호출시킴 ->
// aop가 실행됨 ->
// joinPoint.proceed()가 호출되면 진짜 스프링 빈이 실행됨

// 회원가입, 회원 조회 등 핵심 관심사항과 시간 측정하는 공통 관심사항을 분리함
@Aspect
@Component
public class TimeTraceAop {
    // 'hellospring 하위 패키지에 다 적용시켜라' 이 뜻
    // 대부분 패키지 단위로 함
    // ex) 서비스 패키지만 하고 싶다 @Around("execution(* hello.hellospring.service..*(..))")
    @Around("execution(* hello.hellospring..*(..))")
    // 메서드 호출될 때마다 execute 여기서 걸리는거임
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // proceed() 하기 전에 조건 걸어서 proceed 못하게 할 수도 있고
            // joinPoint 안에 여러 가지 조작할 수 있느 메소드들 많음
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    };
}
