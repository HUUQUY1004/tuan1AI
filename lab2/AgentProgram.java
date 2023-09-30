package lab2;

import java.util.Random;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		String[] action = {"up", "right", "down", "left"};
		Random rand = new Random();
//		if env dirty
		if(p.getLocationState() == p.getLocationState().DIRTY) {
			return Environment.SUCK_DIRT;
		}
		else if (p.getAgentLocation() == Environment.LOCATION_A) {
			int randomIndex = rand.nextInt(action.length)-1;
			if(randomIndex ==0 ||randomIndex==3) {
				
				return null;
			}
			else if (randomIndex ==1) {
				return Environment.MOVE_RIGHT;
			}
			else {
				return Environment.MOVE_DOWN;
			}
		}
		else if (p.getAgentLocation() == Environment.LOCATION_B) {
			int randomIndex = rand.nextInt(action.length) -1;
			if(randomIndex ==0||randomIndex ==1) {
				return null;
			}
			else if (randomIndex==3) {
				return Environment.MOVE_LEFT;
			}
			else {
				return Environment.MOVE_DOWN;
			}
		}
		else if (p.getAgentLocation() == Environment.LOCATION_C) {
			int randomIndex = rand.nextInt(action.length)-1;
			if(randomIndex ==1 ||randomIndex ==2) {
				return null;
			}
			else if (randomIndex==0) {
				return Environment.MOVE_UP;
			}
			else {
				return Environment.MOVE_LEFT;
			}
		}
		else {
			int randomIndex = rand.nextInt(action.length)-1;
			if(randomIndex ==3 ||randomIndex==2) {
				return null;
			}
			else if (randomIndex==0) {
				return Environment.MOVE_UP;
			}
			else {
				return Environment.MOVE_RIGHT;
			}
		}
	}
}