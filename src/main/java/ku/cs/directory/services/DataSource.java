package ku.cs.directory.services;

public interface DataSource<T> {
    T readData();
    void writeData(T t);
}
