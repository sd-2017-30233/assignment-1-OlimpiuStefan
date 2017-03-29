package BusinessLogic;

import DAL.Users;
/**
 * Created by Olimpiu on 3/26/2017.
 */
public class LoginBusiness {
    private AdminBusiness adminBusiness;
    private UsersBusiness usersBusiness;

    public AdminBusiness getAdminBusiness() {
        return adminBusiness;
    }

    public void setAdminBusiness(AdminBusiness adminBusiness) {
        this.adminBusiness = adminBusiness;
    }

    public UsersBusiness getUserBusiness() {
        return usersBusiness;
    }

    public void setUserBusiness(UsersBusiness userBusiness) {
        this.usersBusiness = userBusiness;
    }

    public static int getUserType(String username, String password)
    {
        Users user = new Users();
        int rezultat=user.getTypeByUserAndPass(username,password);
        return rezultat;
    }
}

