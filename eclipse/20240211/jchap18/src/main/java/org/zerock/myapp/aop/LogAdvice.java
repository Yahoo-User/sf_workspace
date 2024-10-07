package org.zerock.myapp.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Aspect
@Component("logAdvice")
public class LogAdvice {
	
	
	
	//--1st. [ Before Advice ] for Some ___Service's All join points
	@Before("execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void logBefore() {
		log.debug("================================================");
		log.debug(">>> [ *Before* Advice ] logBefore() invoked.");
		log.debug("================================================");
		
		
	} // logBefore
	
	
	//--2nd. [ before advice ] for the specified Target join point to inspect method arguments.
	@Before("execution( * org.zerock.myapp.service.SampleService.doAdd(String, String) ) && args(s1, s2)")
	public void logBeforeWithArgs(String s1, String s2) {
		log.debug("================================================");
		log.debug(">>> [ *Before* Advice ] logBeforeWithArgs() invoked.");
		log.debug("================================================");
		
		log.info("\t+ s1: " + s1);
		log.info("\t+ s2: " + s2);
		
		
	} // logBeforeWithArgs
	
	
	//--3rd. [ After Throwing Advice ] acts when target object's join point throw a Exception.
	@AfterThrowing(pointcut="execution( * org.zerock.myapp.service.*Service.*(..) )", throwing="e")
	public void logExceptionAfterThrowing(Exception e) {
		log.debug("================================================");
		log.debug(">>> [ *AfterThrowing* Advice ] logExceptionAfterThrowing(e) invoked.");
		log.debug("================================================");
		
		log.info("\t+ e: " + e);
		
		
		e.printStackTrace();
	} // logExceptionAfterThrowing
	
	
	//--4nd. [ After Returning Advice ] acts After target object's join point finished normally.
	@AfterReturning(pointcut="execution( * org.zerock.myapp.service.*Service.*(..) )", returning="result")
	public void logAfterReturning(Object result) {
		log.debug("================================================");
		log.debug(">>> [ *AfterReturning* Advice ] logAfterReturning(result) invoked.");
		log.debug("================================================");
		
		if(result != null) {	// if return type is NOT void !!!
			log.info("\t+ result: " + result);
			log.info("\t+ type: " + result.getClass().getName());
		} // if
		
	} // logAfterReturning
	
	
	//--5th. [ After Advice ] acts after target object's join point finished regardless of throwing error.
	@After("execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void logAfter() {
		log.debug("================================================");
		log.debug(">>> [ *After* Advice ] logAfter() invoked.");
		log.debug("================================================");
		
		
	} // logAfter
	
	
	//--6th. [ Around Advice ] acts before and after target object's join point.
	//       **** (중요) ****
	//		 Around Advice 를 적용하면, 이전의 다른 종류의 Advice보다 우선 작동하고,
	//		 ProceedingJoinPoint.proceed() 메소드를 호출할 때, 다른 종류의 Advices와 해당 join point 모두
	//		 작동하게 됨 (******)
	@Around("execution( * org.zerock.myapp.service.*Service.*(..) )")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("===========================================================");
		log.debug(">>> [ *Around* Advice ] invoked (**before**) pjp.proceed().");
		log.debug("===========================================================");
		
		log.info("\t+ pjp: " + pjp);
		log.info("\t+ type: " + pjp.getClass().getName());
		
		log.info("\t\t1. target: " + pjp.getTarget());
		log.info("\t\t2. joinpoint: " + pjp.getSignature());
		log.info("\t\t3. params: " + Arrays.toString(pjp.getArgs()));
		
		
		long start = System.nanoTime();
		
		// To invoke the join point of this target object.
		Object returnValue = pjp.proceed();
		
		long end = System.nanoTime();

		log.debug("===========================================================");
		log.debug(">>> [ *Around* Advice ] invoked (**after**) pjp.proceed().");
		log.debug("===========================================================");
		
		log.info("\t\t4. returnValue: " + returnValue);
		log.info("\t\t5. Elapsed time: " + (end-start) / Math.pow(10.0, 9.0) + " seconds.");
		
		return returnValue;
	} // logAround
	
	
	

} // end class
