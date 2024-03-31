package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP 사용
@Component//@Component 컴포넌트 스캔하도록 해도 되지만, SpringConfig 에 @Bean을 등록하여 스프링 빈에 직접 등록 (정형화된 서비스와 달리 AOP를 특이로 인지하기 위함?)
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //hello.hellospring 패키지 하위 모두 적용하라
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+joinPoint.toString() + " "+ timeMs + "ms");
        }
    }
}
