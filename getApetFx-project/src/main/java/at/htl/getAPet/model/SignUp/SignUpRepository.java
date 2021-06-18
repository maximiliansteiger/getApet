package at.htl.getAPet.model.SignUp;

import at.htl.getAPet.model.User.User;

public interface SignUpRepository {

    User createAccount(String name, String email, String Password,String phoneNr);
}
