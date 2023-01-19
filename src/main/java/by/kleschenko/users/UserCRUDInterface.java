package by.kleschenko.users;

import by.kleschenko.repository.CRUDOperation;

public interface UserCRUDInterface<User,String> extends CRUDOperation<User, String> {

    boolean update(String login, String newPassword) throws Exception;
}
