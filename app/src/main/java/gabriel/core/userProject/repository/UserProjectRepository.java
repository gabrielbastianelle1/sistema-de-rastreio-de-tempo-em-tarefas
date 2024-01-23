package gabriel.core.userProject.repository;

import java.util.Collection;
import java.util.UUID;

import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.domain.UserProject;
import gabriel.infra.repository.interfaces.RepositoryInterface;

public interface UserProjectRepository extends RepositoryInterface<UserProject, UUID> {

    /**
     * Should return all users joined by given project
     *
     * @param project
     * @return
     */
    public abstract Collection<User> findAllUsersByProject(Project project);

    /**
     * Should delete the UserProject by given project and user
     *
     * @param project
     * @param user
     * @return
     */
    public abstract Boolean deleteByUserAndProject(Project project, User user);

}
