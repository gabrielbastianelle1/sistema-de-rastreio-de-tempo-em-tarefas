package gabriel.infra.repository.interfaces;

import java.util.Collection;

public interface RepositoryInterface<T, ID> {
    public abstract Collection<T> findAll();

    public abstract ID save(T t);

    public abstract T findById(ID id);

    public abstract boolean delete(ID id);

    public abstract boolean update(ID id, T updated);
}