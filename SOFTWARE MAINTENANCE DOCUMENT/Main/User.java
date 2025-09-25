/**
 * User type determines what functions the user can access
 */
public class User {
    //type of user, determines what functions the user can access
    private UserType userType;
    //the users username
    public String userName;

    /**
     * initialises the user
     */
    public User(){
    }

    /**
     * initialises the user to the given user type, for testing
     */
    public User(UserType userType){
        setUserType(userType);
    }

    /**
     * @return the current user type
     */
    public UserType getUserType(){
        return userType;
    }

    /**
     * Set the type of the user
     * @param userType type to set the user to
     * @throws IllegalArgumentException if userType not a UserType
     */
    public void setUserType(UserType userType) throws IllegalArgumentException{
        if (userType == null){
            throw new IllegalArgumentException();
        }
        this.userType = userType;
    }

    /**
     * @return the currently set username
     */
    public String getUserName(){
        return userName;
    }

    
}
