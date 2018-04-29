//package aspects;
//
//import java.util.logging.Logger;
//
//public aspect LogAspect {
//	private final static Logger log = Logger.getLogger(LogAspect.class.getName());
//	
//	public pointcut callMethod(): call(* *.*(..)) && !within(LogAspect) ;
//	 
//    before() : callMethod() {
//        log.info("Calling method "+ thisJoinPointStaticPart.getSignature());
//    }
//}
