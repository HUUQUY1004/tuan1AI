package Lab1;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		//drity
		if(p.getLocationState() ==  p.getLocationState().DIRTY) {
			return Environment.SUCK_DIRT;
		}
//		CLEAN
		else if(p.getAgentLocation() == Environment.LOCATION_A) {
			return Environment.MOVE_RIGHT;
		}
		else {
			return Environment.MOVE_LEFT;
		}
		
	}
}