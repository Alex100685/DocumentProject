package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="big_section")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class BigSection {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="bigsection_name", nullable = false)
    private String name;

    @OneToMany(mappedBy="bigSection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Section> sections = new ArrayList<Section>();
    
    
    
    public BigSection() {}

    public BigSection(String name) {
        this.name = name;
    }
    
    public void addSection(Section section) {
        sections.add(section);
        if (section.getBigSection() != this)
            section.setBigSection(this);
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


    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
