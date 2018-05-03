package aspects;

import java.util.HashMap;

import java.util.Map;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CacheAspect {
	private Logger logger = Logger.getLogger(CacheAspect.class.getName());
	private Map<String, Object> cache;

	public CacheAspect() {
		cache = new HashMap<String, Object>();
	}
	
	@Pointcut("execution(@annotations.Cacheable * *.*(..))")
	private void cache() {}

	@Around("cache()")
	public Object aroundCachedMethods(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		System.out.println("Execution of Cacheable method catched");

		// generate the key under which cached value is stored
		StringBuilder keyBuff = new StringBuilder();

		// append name of the class
		keyBuff.append(thisJoinPoint.getTarget().getClass().getName());

		// append name of the method
		keyBuff.append(".").append(thisJoinPoint.getSignature().getName());

		keyBuff.append("(");
		// loop through cacheable method arguments
		for (final Object arg : thisJoinPoint.getArgs()) {
			// append argument type and value
			keyBuff.append(arg.getClass().getSimpleName() + "=" + arg + ";");
		}
		keyBuff.append(")");
		String key = keyBuff.toString();

		System.out.println("Key = " + key);
		Object result = cache.get(key);
		if (result == null) {
			System.out.println("Task not yet cached. Must be retrieved...");
			result = thisJoinPoint.proceed();
			System.out.println("Storing retrieved task '" + result + "' to cache");
			cache.put(key, result);
		} else {
			System.out.println("Task '" + result + "' was found in cache");
		}

		return result;
	}
	
}
