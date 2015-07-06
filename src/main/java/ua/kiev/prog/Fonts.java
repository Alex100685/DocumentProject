package ua.kiev.prog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
	
	@Entity
	@Table(name="fonts")
	@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
	public class Fonts {
	    @Id
	    @Column(name="id")
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    @Column(name="name")
	    private String name;

	    @Column(name="font_body")
	    private byte [] fontBody;

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

		public byte[] getFontBody() {
			return fontBody;
		}

		public void setFontBody(byte[] fontBody) {
			this.fontBody = fontBody;
		}
	    
	    
	}