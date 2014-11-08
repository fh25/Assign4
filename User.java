package assign4;

import java.io.Serializable;

/**
 * User abstract class that implements Serializable
 * @author fhj
 */
public abstract class User implements Serializable {

    /**
     * protected int id.
     */
	protected int id;
    
    /**
     * protected String name.
     */
	protected String name;

    /**
     * Returns id.
     * @return 
     */
    public int getId() {
      return id;
    }

    /**
     * Returns name.
     * @return 
     */
    public String getName() {
      return name;
    }

    /**
     * Sets id.
     * @param id 
     */
    public void setId(int id) {
      this.id = id;
    }

    /**
     * Sets name.
     * @param name 
     */
    public void setName(String name) {
      this.name = name;
    }
}