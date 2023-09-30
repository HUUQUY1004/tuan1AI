package lab2;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}
	private int score = 0;
	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		String[] actions = { "UP", "RIGHT", "DOWN", "LEFT" };
		Random rand = new Random();
		int randomIndex = rand.nextInt(actions.length)-1;
		int total = 0;
		// TODO
		if (action == SUCK_DIRT) {
			String agentLoaction = envState.getAgentLocation();
			envState.setLocationState(agentLoaction, LocationState.CLEAN);
			total += 500;
		}
		else if (envState.getAgentLocation() == LOCATION_A) {
			if(action == MOVE_RIGHT) {
				total -=10;
				envState.setAgentLocation(LOCATION_B);
			}
			else if(action == MOVE_DOWN) {
				total -=10;
				envState.setAgentLocation(LOCATION_D);
			}
			else {
				total -=100;
				envState.setAgentLocation(LOCATION_A);
			}
		}
		else if (envState.getAgentLocation() == LOCATION_B) {
			if(action == MOVE_LEFT) {
				total -=10;
				envState.setAgentLocation(LOCATION_A);
			}
			else if(action == MOVE_DOWN) {
				total -=10;
				envState.setAgentLocation(LOCATION_C);
			}
			else {
				total -=100;
				envState.setAgentLocation(LOCATION_B);
			}
		}
		else if (envState.getAgentLocation() == LOCATION_C) {
			if(action == MOVE_UP) {
				total -=10;
				envState.setAgentLocation(LOCATION_B);
			}
			else if(action == MOVE_LEFT) {
				total -=10;
				envState.setAgentLocation(LOCATION_D);
			}
			else {
				total -=100;
				envState.setAgentLocation(LOCATION_C);
			}
		}
		else if (envState.getAgentLocation() == LOCATION_D) {
			if(action == MOVE_RIGHT) {
				total -=10;
				envState.setAgentLocation(LOCATION_C);
			}
			else if(action == MOVE_UP) {
				total -=10;
				envState.setAgentLocation(LOCATION_A);
			}
			else {
				total -=100;
				envState.setAgentLocation(LOCATION_D);
			}
		}
		 this.score = total;
		return envState;
	}
	public void score() {
		System.out.println("Tổng điểm : " + this.score);
	}
	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// get agent Loaction
		String agentLocation = envState.getAgentLocation();
		LocationState state = envState.getLocationState(agentLocation);
		Percept per = new Percept(agentLocation, state);
		return per;
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
