package gabriel.core.user.repository;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.repository.interfaces.RepositoryInterface;

public interface UserRepository extends RepositoryInterface<User, Username> {
}
