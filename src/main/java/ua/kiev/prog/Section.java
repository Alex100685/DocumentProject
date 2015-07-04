package ua.kiev.prog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="section")
public class Section {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="section_name", nullable = false)
    private String name;

    @OneToMany(mappedBy="section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents = new ArrayList<Document>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bigsection_id")
    private BigSection bigSection;

    public Section() {}

    public Section(String name) {
        this.name = name;
    }
    
    public void setBigSection(BigSection bigSection) {
        this.bigSection = bigSection;
        if ( !bigSection.getSections().contains(this))
            bigSection.getSections().add(this);
    }
    
    public BigSection getBigSection(){
    	return bigSection;
    }
    
    

    public void addDocument(Document document) {
        documents.add(document);
        if (document.getSection() != this)
            document.setSection(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}

