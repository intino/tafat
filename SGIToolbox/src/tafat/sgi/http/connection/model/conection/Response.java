package tafat.sgi.http.connection.model.conection;


public interface Response {
    String getBody();

    int getStatusCode();

    void setStatusCode(int code);
}