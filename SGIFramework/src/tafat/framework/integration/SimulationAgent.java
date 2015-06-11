package tafat.framework.integration;

import java.util.Collection;
import java.util.Date;

public interface SimulationAgent {
    Collection<String> roots();
    Collection<String> childrenOf(String objectId);
    Collection<String> attributesOf(String objectId);
    String valueGet(String objectId);
    boolean valueSet(String objectId, String value);

    boolean play();
    boolean pause();
    boolean reset();
}
