/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        for(int i = 0; i < follows.length; i ++){
            if(follows[i] != null){
                if (follows[i].equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {
        if(follows(name)){              //cheaking if user follow the person 
            return false;
        }
        for(int i = 0; i < follows.length; i ++){
            if(follows[i] == null){
                follows[i] = name;
                return true;
            }
        }
        return false;           // user's list is full
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
        for(int i = 0; i < follows.length; i ++){
            if(follows[i] != null){
                if(follows[i].equals(name)){
                    follows[i] = null;
                    return true;             // name is removed
                }   
            }
        }
        return false;           // name isn't in user list
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        int counter = 0;
        for(int i = 0; i < follows.length; i ++){
            if (this.follows[i] != null){
                counter ++;
            }
            if (other.getfFollows()[i] != null){
                counter ++;
            }
        }
        return counter;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
        if(!follows(other.getName())){          //tnis user follow the other
            return false;
        }
        if (!other.follows(this.name)){         //other is follow this use
            return false;
        }
        return true;
    }

    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}