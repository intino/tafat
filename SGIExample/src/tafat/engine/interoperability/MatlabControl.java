package tafat.engine.interoperability;

import tafat.engine.ModelObject;
import matlabcontrol.*;

public class MatlabControl{

	private static MatlabProxyFactory _factory;
	public static MatlabProxy _proxy;
	
	private static MatlabControl INSTANCE = null;
	

	private MatlabControl() throws MatlabConnectionException
	{
		_factory = new MatlabProxyFactory();
		
			try
			{
				_proxy = _factory.getProxy();
			}
			catch (MatlabConnectionException exc){}
	}
	
    public static MatlabControl getInstance() throws MatlabConnectionException {
        if (INSTANCE == null) { 
            INSTANCE = new MatlabControl();
        }     
        return INSTANCE;
    }
    
    
    
    public void setVariable(ModelObject object,String variableName, Object value){
    	
    	try {
			_proxy.setVariable(object.id.replace(".", "_") + variableName, value);
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}
    }
    
    public Object getVariable(ModelObject object, String variableName){
    	
    	try{
    		return _proxy.getVariable(object.id.replace(".","_") + variableName);
    	} catch (MatlabInvocationException e){
    		e.printStackTrace();
    		return null;
    	}
    }
    //Calls a function with parameters and a number of returning parameters
    public Object execute(String function, Object[] param,int nreturn){
    	
    	try {
			return _proxy.returningFeval(function,nreturn,param);
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
			return null;
		}
    }
    //Calls a function without parameters
    public Object execute(String function,int nreturn){
    	
    	try {
			return _proxy.returningEval(function,nreturn);
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
			return null;
		}	
    }
    public void eval(String expresion){

    	 try {
			_proxy.eval(expresion);
		} catch (MatlabInvocationException e) {

			e.printStackTrace();
		}	
    }
    
    //Closes matlab session and delete the matlabStub instance
    public static void destroyInstance() throws MatlabInvocationException{
    	if(_proxy != null && _proxy.isConnected())
		{
			try
			{
				_proxy.exit();
			}
			catch (MatlabInvocationException exc) { }
		INSTANCE = null;
		}
    }
}

	