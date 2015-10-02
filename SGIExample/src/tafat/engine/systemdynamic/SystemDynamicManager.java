package tafat.engine.systemdynamic;

import java.util.ArrayList;

import org.opensourcephysics.numerics.ODESolver;
import org.opensourcephysics.numerics.ODESolverFactory;


public class SystemDynamicManager {

	private static SystemDynamicManager instance = null;
	
	ArrayList <SystemDynamicSolver> systemDynamicList = new ArrayList <SystemDynamicSolver> ();
	
	public static void createInstance(){
		if (instance == null)
			instance = new SystemDynamicManager();
	}
	
	public static SystemDynamicManager getInstance(){
		return instance;
	}
	
	public void registerSystemDynamic (SystemDynamic systemDynamic, String solverName, double step){
		ODESolver solver = ODESolverFactory.createODESolver(systemDynamic, solverName);
		solver.setStepSize(step);
		
		systemDynamicList.add(new SystemDynamicSolver(systemDynamic, solver));
	}
	
	public void calculate (){
		for (SystemDynamicSolver systemDynamicSolver : systemDynamicList){
//			systemDynamicSolver.systemDynamic.updateParam();
			for (int i = 0; i < systemDynamicSolver.timesPerSecond; i++)
				systemDynamicSolver.solver.step();
		}
	}
	
	class SystemDynamicSolver{
		SystemDynamic systemDynamic;
		ODESolver solver;
		int timesPerSecond;
		
		public SystemDynamicSolver (SystemDynamic systemDynamic, ODESolver solver){
			this.systemDynamic = systemDynamic;
			this.solver = solver;
			timesPerSecond = (int) (1. / solver.getStepSize());
		}
	}
}
