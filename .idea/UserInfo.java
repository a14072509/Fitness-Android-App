
public class UserInfo
{
    //instance variables
    private String userName;
    private String password;

    private String email; //optional

    private double weight; // in unit of pounds

    private double height; // in unit of centimeters
    private char gender; //'F' = female, 'M' = male, 'U' = Unknown

    //constructors
    public UserInfo(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
        this.gender = 'U';
    }

    public UserInfo(String userName, String password, double weight, double height)
    {
        this(userName, password);
        this.weight = weight;
        this.height = height;
    }

    public UserInfo(String userName, String password, double weight, double height, char gender)
    {
        this(userName, password, weight, height);
        this.gender = gender;
    }

    public UserInfo(String userName, String password, double weight,
                    double height, char gender, String email)
    {
        this(userName, password, weight, height, gender);
        this.email = email;
    }

    //methods


    //getters and setters
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public char getGender()
    {
        return gender;
    }

    public void setGender(char gender)
    {
        this.gender = gender;
    }
}