package tafat.framework.services.defaults;

import tafat.sgi.http.connection.controller.PetitionClient;
import tafat.framework.services.ServicesManager;
import tafat.framework.services.NotificationService;
import tafat.framework.finder.ReflectionServiceFinder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PushServiceTest {

    @Before
    public void setUp() throws Exception {
        ServicesManager.setUp(new ReflectionServiceFinder(), "tafat.framework.services.defaults");
    }

    @Test
    public void getPushNotificationService() {
        assertSame(PushNotification.class, ServicesManager.get(NotificationService.class).getClass());
    }

    @Test
    public void sendPushNotificationSensorJSON() throws IOException {
        PetitionClient petitionClient = mock(PetitionClient.class);
        new PushNotification(petitionClient).push("Bryan","{\"sensor2\":12}");
        verify(petitionClient).sendRequest(anyObject(), anyObject());
    }

}