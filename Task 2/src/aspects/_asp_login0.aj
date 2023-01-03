package aspects;

import main.RunningTestTwo;

import larva.*;
public aspect _asp_login0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_login0.initialize();
}
}
before () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 12/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 12/*viewAlerts*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 10/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 10/*goodLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 6/*badLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 6/*badLogin*/);
}
}
before () : (call(* *.logOut(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 8/*logOut*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 8/*logOut*/);
}
}
}