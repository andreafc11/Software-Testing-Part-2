package aspects;

import main.RunningTestOne;

import larva.*;
public aspect _asp_APIFunctionality0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_APIFunctionality0.initialize();
}
}
before () : (call(* *.purge(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_APIFunctionality0.lock){

_cls_APIFunctionality0 _cls_inst = _cls_APIFunctionality0._get_cls_APIFunctionality0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 18/*purge*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 18/*purge*/);
}
}
before () : (call(* *.uploadIncAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_APIFunctionality0.lock){

_cls_APIFunctionality0 _cls_inst = _cls_APIFunctionality0._get_cls_APIFunctionality0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 16/*uploadIncAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 16/*uploadIncAlert*/);
}
}
before () : (call(* *.uploadCorrAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_APIFunctionality0.lock){

_cls_APIFunctionality0 _cls_inst = _cls_APIFunctionality0._get_cls_APIFunctionality0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 14/*uploadCorrAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 14/*uploadCorrAlert*/);
}
}
}