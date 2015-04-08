package tafat.toolbox.systemdynamic;

import org.junit.Test;

public class SystemDynamicAccepted {

    @Test
    public void testName() throws Exception {
        SystemDynamic.define().
                stock("Temperature").calculus("Hvac + Tranfers + Currents + Others").
                stock("ContentTemperature").calculus("ContentGains").
                stock("PlateTemperature").calculus("InternalHeat + Q - CoolingCapacity").
                flow("DoorGains").calculus("DoorOpen * (ExternalTemperature - InternalTemperature) / ZDoor").
                flow("ContentLosses").
                flow("ContentGains").
                flow("PlateGains").
                flow("ExternalHeat").
                flow("InternalHeat").
                flow("Q").
                flow("CoolingCapacity").
                parameter().
    }

//    @Test
//    public void testName() throws Exception {
//        SystemDynamic.define().
//                stock("InternalTemperature").calculus("ExternalHeat + ContentLosses + DoorGains").
//                stock("ContentTemperature").calculus("ContentGains").
//                stock("PlateTemperature").calculus("InternalHeat + Q - CoolingCapacity").
//                flow("DoorGains").calculus("DoorOpen * ExternalTemperature - InternalTemperature) / Z_Door").
//                flow("ContentLosses").
//                flow("ContentGains").
//                flow("PlateGains").
//                flow("ExternalHeat").
//                flow("InternalHeat").
//                flow("Q").
//                flow("CoolingCapacity").
//                parameter().
//    }

}
