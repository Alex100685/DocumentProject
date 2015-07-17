package ua.kiev.prog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name="records")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Record {
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@Column(name="date")
    private String date;
	
	@Column(name="user_name", nullable = false)
    private String name;
	
	@Column(name="user_id", nullable = false)
    private String userId;
	
	@Column(name="document_name", nullable = false)
    private String documentName;
	
	@Column(name="action_name", nullable = false)
    private String actionName;
	
	@Column(name="old_value")
    private String oldValue;
	
	@Column(name="new_value")
    private String newValue;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public String getOldValue() {
		return oldValue;
	}


	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}


	public String getNewValue() {
		return newValue;
	}


	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

}
