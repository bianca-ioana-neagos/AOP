package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PerformanceAspect {
	
	@Pointcut("execution(* repo.TodoRepo.*(..))")
    public void monitor() { }

	@Around("monitor()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println();
        long start = System.currentTimeMillis();
        try {
        	System.out.println("Going to call the method.");
            Object output = pjp.proceed();
            System.out.println("Method execution completed.");
            System.out.println();
            return output;
        } finally { 
            long elapsedTime = System.currentTimeMillis() - start;
            System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
            System.out.println();
        }
            		
    }

}