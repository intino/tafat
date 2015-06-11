package tafat.framework.services;

public interface NotificationService extends FrameworkService{
    void push(String username, String message);

    void broadcast(String message);
}
