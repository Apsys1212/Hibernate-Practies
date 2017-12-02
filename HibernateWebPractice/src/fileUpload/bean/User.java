package fileUpload.bean;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1879181700782530736L;

	@Id
	@GeneratedValue
	private Integer userId;
	
	private String userName;
   
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column
    private byte[] upfile;
 
    public Integer getUserId() {
        return userId;
    }
 
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public byte[] getUpfile() {
		return upfile;
	}

	public void setUpfile(byte[] upfile) {
		this.upfile = upfile;
	}
 
 
    
}