package bartersystem;
public class UserInfo
{
	private String name,email,password;
        private int id;

    

      // default constructor
      public UserInfo()
      {       
         name = "";
         email = "";
         password = "";
         id = 0; 
      }

	public UserInfo(int id, String name,String email, String password)
	{
                this.id = id;
		this.name = name;
		this.email = email;
                this.password = password;
	}
 
      // param construcrtor with 4 values 
	public UserInfo(String name, String email, String password)
	{
		this.name = name;
		this.email = email;
                this.password = password;
	}

      // setters
	public void setId(int i)
	{
		id = i;
	}
	public void setName(String n)
	{
		name=n;		
	}
	public void setEmail(String e)
	{
		email=e;
	}

      // getters
	public int getId( )
	{
		return id;
      }

	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}
        
        public String getPassword() {
        return password;
        }

        public void setPassword(String password) {
        this.password = password;
        }
	

}