package tafat.sgi.exception;

public interface FunctionalWithException<Input>{
    Input execute() throws Exception;
}
