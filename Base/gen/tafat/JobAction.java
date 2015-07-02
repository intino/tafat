package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class JobAction extends magritte.wraps.Definition {
    public magritte.Set<tafat.Job> job() {
        return _getMultiple("job").as(tafat.Job.class);
    }

    public tafat.Job job(int index) {
        return job().get(index);
    }

    public void job(Tafat.Job... job) {
        _edit().set("job", job);
    }

    public void job(magritte.Expression<tafat.Job> value) {
        _edit().let("job", value);
    }

    public void job(magritte.wraps.Operation operation, Tafat.Job... job) {
        _edit(operation).set("job", job);
    }
}
