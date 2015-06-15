package tafat.framework.services.defaults;

import tafat.framework.integration.simulation.Watcher;
import tafat.framework.services.SensorService;
import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.framework.state.ServerState;

public class SensorsService implements SensorService {

    @Override
    public Response installSensor(Request request) {
        Watcher watcher = new Watcher(request.getParameter("objectId"), request.getParameter("measurableAttributeName"));
        ServerState.state().watcherContainer().addWatcher(request.getParameter("username"), watcher);
        return new HttpResponse(200, "{\"message\":\"sensors installed\"}");
    }

    @Override
    public HttpResponse remove(Request request) {
        Watcher watcher = new Watcher(request.getParameter("objectId"), request.getParameter("measurableAttributeName"));
        ServerState.state().watcherContainer().removeUserWatcher(request.getParameter("username"), watcher);
        return new HttpResponse(200, "{\"message\":\"sensors remove\"}");
    }
}
