package ku.cs.directory.services;

public interface Filterer <T> {

    boolean filter(T o);
}
