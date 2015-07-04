package ua.kiev.prog;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="document")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public class Document {
    @Id
    @Column(name="inventary_number")
    private String inventaryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="section_id")
    private Section section;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="publisher_id")
    private Publisher publisher;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    private Receiver receiver;

    @Column(name="publish_date")
    private String publishDate;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private String status;

    @Column(name="note")
    private String note;

    @Column(name="doc_type", nullable = false)
    private String docType;
    
    @Column(name="quanity", nullable = false)
    private int quantity;
    
    @Column(name="file_name")
    private String fileName;
    
    @Column(name="file_body")
    private byte [] fileBody;
    
    
    public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBody() {
		return fileBody;
	}

	public void setFileBody(byte[] fileBody) {
		this.fileBody = fileBody;
	}

	

    public Document() {}

    public Document(String name, String status, String note) {
        this.name = name;
        this.status = status;
        this.note = note;
    }

    public void setSection(Section section) {
        this.section = section;
        if ( !section.getDocuments().contains(this))
            section.getDocuments().add(this);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        if ( !publisher.getDocuments().contains(this))
            publisher.getDocuments().add(this);
    }
   

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }
    
    public Publisher getPublisher(){
    	return publisher;
    }

    public Section getSection() {
        return section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	

}

