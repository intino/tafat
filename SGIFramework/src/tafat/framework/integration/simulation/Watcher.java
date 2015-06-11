package tafat.framework.integration.simulation;

public class Watcher {
    private String objectId;
    private String measurableAttribute;

    public Watcher(String objectId, String measurableAttribute) {
        this.objectId = objectId;
        this.measurableAttribute = measurableAttribute;
    }

    public String path() {
        return objectId + "/" + measurableAttribute;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getMeasurableAttribute() {
        return measurableAttribute;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Watcher watcher = (Watcher) o;

        if (objectId != null ? !objectId.equals(watcher.objectId) : watcher.objectId != null) return false;
        return !(measurableAttribute != null ? !measurableAttribute.equals(watcher.measurableAttribute) : watcher.measurableAttribute != null);

    }
}
