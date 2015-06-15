package tafat.site.route.identification;

public class UserIdentification implements Identification{
    private final String simulationId;
    private final String user;

    public UserIdentification(String user, String simulationId) {
        this.user = user;
        this.simulationId = simulationId;
    }

    public String getSimulationId() {
        return simulationId;
    }

    public String getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserIdentification that = (UserIdentification) o;

        if (simulationId != null ? !simulationId.equals(that.simulationId) : that.simulationId != null) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = simulationId != null ? simulationId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
