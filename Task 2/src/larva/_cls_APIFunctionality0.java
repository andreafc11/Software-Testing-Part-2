package larva;


import main.RunningTestOne;

import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_APIFunctionality0 implements _callable{

public static PrintWriter pw; 
public static _cls_APIFunctionality0 root;

public static LinkedHashMap<_cls_APIFunctionality0,_cls_APIFunctionality0> _cls_APIFunctionality0_instances = new LinkedHashMap<_cls_APIFunctionality0,_cls_APIFunctionality0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\josep\\Desktop\\Task 2/src/output_APIFunctionality.txt");

root = new _cls_APIFunctionality0();
_cls_APIFunctionality0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_APIFunctionality0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public int alertCounter =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_APIFunctionality0() {
}

public void initialisation() {
}

public static _cls_APIFunctionality0 _get_cls_APIFunctionality0_inst() { synchronized(_cls_APIFunctionality0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_APIFunctionality0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_APIFunctionality0_instances){
_performLogic_apiFunctionalityProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_APIFunctionality0[] a = new _cls_APIFunctionality0[1];
synchronized(_cls_APIFunctionality0_instances){
a = _cls_APIFunctionality0_instances.keySet().toArray(a);}
for (_cls_APIFunctionality0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_APIFunctionality0_instances){
_cls_APIFunctionality0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_apiFunctionalityProperty = 11;

public void _performLogic_apiFunctionalityProperty(String _info, int... _event) {

_cls_APIFunctionality0.pw.println("[apiFunctionalityProperty]AUTOMATON::> apiFunctionalityProperty("+") STATE::>"+ _string_apiFunctionalityProperty(_state_id_apiFunctionalityProperty, 0));
_cls_APIFunctionality0.pw.flush();

if (0==1){}
else if (_state_id_apiFunctionalityProperty==11){
		if (1==0){}
		else if ((_occurredEvent(_event,14/*uploadCorrAlert*/)) && (alertCounter <5 )){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 9;//moving to state alertsHaveBeenUploaded
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,18/*purge*/))){
		alertCounter =0 ;

		_state_id_apiFunctionalityProperty = 10;//moving to state alertsHaveBeenPurged
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,16/*uploadIncAlert*/))){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 8;//moving to state apiHasBadStatus
		_goto_apiFunctionalityProperty(_info);
		}
}
else if (_state_id_apiFunctionalityProperty==8){
		if (1==0){}
		else if ((_occurredEvent(_event,14/*uploadCorrAlert*/)) && (alertCounter >=5 )){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 8;//moving to state apiHasBadStatus
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,16/*uploadIncAlert*/))){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 8;//moving to state apiHasBadStatus
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,18/*purge*/))){
		alertCounter =0 ;

		_state_id_apiFunctionalityProperty = 10;//moving to state alertsHaveBeenPurged
		_goto_apiFunctionalityProperty(_info);
		}
}
else if (_state_id_apiFunctionalityProperty==10){
		if (1==0){}
		else if ((_occurredEvent(_event,18/*purge*/))){
		alertCounter =0 ;

		_state_id_apiFunctionalityProperty = 10;//moving to state alertsHaveBeenPurged
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,16/*uploadIncAlert*/))){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 8;//moving to state apiHasBadStatus
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,14/*uploadCorrAlert*/)) && (alertCounter ==0 )){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 9;//moving to state alertsHaveBeenUploaded
		_goto_apiFunctionalityProperty(_info);
		}
}
else if (_state_id_apiFunctionalityProperty==9){
		if (1==0){}
		else if ((_occurredEvent(_event,14/*uploadCorrAlert*/)) && (alertCounter <5 )){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 9;//moving to state alertsHaveBeenUploaded
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,18/*purge*/))){
		alertCounter =0 ;

		_state_id_apiFunctionalityProperty = 10;//moving to state alertsHaveBeenPurged
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,16/*uploadIncAlert*/))){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 8;//moving to state apiHasBadStatus
		_goto_apiFunctionalityProperty(_info);
		}
		else if ((_occurredEvent(_event,14/*uploadCorrAlert*/)) && (alertCounter ==5 )){
		alertCounter ++;

		_state_id_apiFunctionalityProperty = 8;//moving to state apiHasBadStatus
		_goto_apiFunctionalityProperty(_info);
		}
}
}

public void _goto_apiFunctionalityProperty(String _info){
_cls_APIFunctionality0.pw.println("[apiFunctionalityProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_apiFunctionalityProperty(_state_id_apiFunctionalityProperty, 1));
_cls_APIFunctionality0.pw.flush();
}

public String _string_apiFunctionalityProperty(int _state_id, int _mode){
switch(_state_id){
case 11: if (_mode == 0) return "apiHasBeenInitialised"; else return "apiHasBeenInitialised";
case 8: if (_mode == 0) return "apiHasBadStatus"; else return "!!!SYSTEM REACHED BAD STATE!!! apiHasBadStatus "+new _BadStateExceptionAPIFunctionality().toString()+" ";
case 10: if (_mode == 0) return "alertsHaveBeenPurged"; else return "alertsHaveBeenPurged";
case 9: if (_mode == 0) return "alertsHaveBeenUploaded"; else return "alertsHaveBeenUploaded";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}