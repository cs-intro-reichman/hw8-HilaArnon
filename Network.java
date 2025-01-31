/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        if (name == null || userCount == 0){
            return null;
        }
        
        String actualName = Character.toString(name.charAt(0)).toUpperCase();
        actualName += name.substring(1);
        for (int i = 0; i < users.length; i ++){
            if(users[i] != null){
                if (users[i].getName().equals(actualName)){
                    return users[i]; 
                }    
            }
        }
        return null;            // name wasn't found
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        if(users.length == userCount || getUser(name) != null){
            return false;
        }
        for (int i = 0; i < users.length; i ++){
            if(users[i] == null){
                users[i] = new User(name);
                userCount ++;
                return true;
            }
        }
        return true;          //Just beause I have to
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        if(name1 == null || name2 == null){
            return false;
        }

        String actualName1 = Character.toString(name1.charAt(0)).toUpperCase();
        actualName1 += name1.substring(1);

        String actualName2 = Character.toString(name2.charAt(0)).toUpperCase();
        actualName2 += name2.substring(1);

        if(getUser(actualName1) == null || getUser(actualName2) == null){
            return false;                           //one of them nit in the network
        }

        if (!getUser(actualName1).addFollowee(actualName2)){
            return false;
        }

        return true;
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        User maxUser = null;
        int maxMutuals = -1;
        for (int i = 0; i < userCount; i++){
            if (!users[i].getName().equals(name)){
                int correncMutuals = users[i].countMutual(getUser(name));
                if (correncMutuals > maxMutuals){
                    maxMutuals = correncMutuals;
                    maxUser = users[i];
                }
            }
        }
        return maxUser.getName();
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        int maxFollow = -1;
        String popularName = null;
        for(int i = 0; i < users.length; i ++){
            if(users[i] != null){
                int correntUserFollows = followeeCount(users[i].getName());
                if(correntUserFollows > maxFollow){
                    popularName = users[i].getName();
                    maxFollow = correntUserFollows;
                }
            }
        }
        return popularName;
    }

    // public String mostPopularUser() {
    //     int [] beingFollow = new int[users.length];          //haw many people follow this user
    //     for(int i = 0; i < users.length; i ++){
    //         if (users[i] != null){
    //             String correntName = users[i].getName(); 
    //             for(int j = 0; j < users.length; j ++){      //cheaking who follows that user
    //                 if (users[j] != null && i != j){
    //                     if(users[j].follows(correntName)){    
    //                         beingFollow[i] ++;
    //                     }
    //                 } 
    //             }
    //         }
    //     }

    //     //cheaking who is the most popoular
    //     int indexPopular = 0;
    //     for(int i = 1; i < beingFollow.length; i ++){
    //         if (beingFollow[i] != 0){
    //             if(beingFollow[i] >= beingFollow[indexPopular]){
    //                 indexPopular = i;
    //             }
    //         }
    //     }
    //     return users[indexPopular].getName();
    // }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int counter = 0;
        for(int i = 0; i < users.length; i ++){
            if (users[i] != null){
                if(!users[i].getName().equals(name)){
                    if(users[i].follows(name)){ 
                        counter ++;
                    }
                }                                 
            }
        }
        return counter;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
        String str = "Network:";
        for(int i = 0; i < users.length; i ++){
            if (users[i] != null){
                str+= "\n";
                str += users[i].toString();
            }
        }
       return str;
    }
}
