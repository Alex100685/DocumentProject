package ua.kiev.prog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ActionsImpl implements Actions {
	
	@Autowired
    private EntityManager entityManager;
	
	
	

	public List<Document> DocumentList() {
		try{
		List<Document> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT d FROM Document d", Document.class);
		list = query.getResultList();
		return list;
	} catch(NoResultException e) {
        return null;
    }
	}
	
	public List<BigSection> BigSectionList() {
		try{
		List<BigSection> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT b FROM BigSection b", BigSection.class);
		list = query.getResultList();
		return list;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public void add(BigSection bs) {
		try {
            entityManager.getTransaction().begin();
            entityManager.persist(bs);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
		
	}

	@Override
	public List<Section> smallSectionList(String bigSectionName) {
		BigSection bs;
		Query query = entityManager.createQuery("SELECT b FROM BigSection b WHERE b.name = '"+bigSectionName+"'", BigSection.class);
		bs = (BigSection) query.getSingleResult();
		List <Section> list = new ArrayList<>();
		list = bs.getSections();
		return list;
	}
	
	public BigSection getBigSectionByName(String name){
		try{
		BigSection bs;
		Query query = entityManager.createQuery("SELECT b FROM BigSection b WHERE b.name = '"+name+"'", BigSection.class);
		bs = (BigSection) query.getSingleResult();
		return bs;
	} catch(NoResultException e) {
        return null;
    }
		
		
	}

	@Override
	public Section getSectionByName(String name) {
		try{
		Section s;
		Query query = entityManager.createQuery("SELECT s FROM Section s WHERE s.name ='"+name+"'", Section.class);
		s = (Section) query.getSingleResult();
		return s;	
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public void add(Document d) {
		try {
			EntityTransaction tr = entityManager.getTransaction();
          	tr.begin();
            entityManager.persist(d);
            entityManager.flush();
            tr.commit();
            
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
		
		
	}
	
	@Override
	public void add(Publisher p) {
		try {
			EntityTransaction tr = entityManager.getTransaction();
          	tr.begin();
            entityManager.persist(p);
            tr.commit();
            
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
		
		
	}

	@Override
	public List<Document> searchByInvNumbList(String pattern) {
		try{
		List<Document> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT d FROM Document d", Document.class);
		list = query.getResultList();
		List <Document> searchList = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getInventaryNumber().contains(pattern)){
				searchList.add(list.get(i));
			}
		}
		return searchList;
	} catch(NoResultException e) {
        return null;
    }
	}
	
	public List<Document> searchByName(String pattern) {
		try{
		List<Document> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT d FROM Document d", Document.class);
		list = query.getResultList();
		List <Document> searchList = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getName().contains(pattern)){
				searchList.add(list.get(i));
			}
		}
		return searchList;
	} catch(NoResultException e) {
        return null;
    }
	}
	
	public List<Document> searchByPublisher(String pattern) {
		try{
		List<Publisher> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class);
		list = query.getResultList();
		List <Publisher> searchList = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getName().contains(pattern)){
				searchList.add(list.get(i));
			}
		}
		List <Document> docList = new ArrayList <Document>();
		for(int i=0; i<searchList.size(); i++){
			List <Document> tempList = searchList.get(i).getDocuments();
			for(int j=0; j<tempList.size(); j++){
				docList.add(tempList.get(j));
			}
			
		}
		
		return docList;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public List<Publisher> PublisherList() {
		try{
		List<Publisher> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class);
		list = query.getResultList();
		return list;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public Publisher getPublisherByName(String name) {
		try{
		Query query = entityManager.createQuery("SELECT p FROM Publisher p WHERE p.name = '"+name+"'");
		Publisher p = (Publisher) query.getSingleResult();
		return p;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public Document getDocByInvNym(String inventaryNumber) {
		try{
		Query query = entityManager.createQuery("SELECT d FROM Document d WHERE d.inventaryNumber = '"+inventaryNumber+"'");
		Document d = (Document) query.getSingleResult();
		return d;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public void deleteDocByInvNum(String [] invNum) {
		List <Document> docList = new ArrayList<>();
		for(int i=0; i<invNum.length; i++){
			Query query = entityManager.createQuery("SELECT d FROM Document d WHERE d.inventaryNumber = '"+invNum[i]+"'");
			Document d = (Document) query.getSingleResult();
			d.getSection().getDocuments().remove(d);
			if(d.getPublisher()!=null){
			d.getPublisher().getDocuments().remove(d);
			}
			if(d.getReceiver()!=null){
				d.getReceiver().getDocuments().remove(d);
			}
			docList.add(d);
		}
		entityManager.getTransaction().begin();
		for(Document d:docList){		
			if (entityManager.contains(d))
		    {
		        entityManager.remove(d);
		    }
		    else
		    {
		        entityManager.remove(entityManager.merge(d));
		    }
		}
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Section> sectionList() {
		try{
		Query query = entityManager.createQuery("SELECT s FROM Section s", Section.class);
		List <Section> sList = query.getResultList();
		return sList;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public BigSection getBigSectionByID(String id) {
		try{
		Query query = entityManager.createQuery("SELECT b FROM BigSection b WHERE b.id = '"+id+"'", BigSection.class);
		BigSection b = (BigSection) query.getSingleResult();
		return b;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public void deleteBigSection(BigSection bs) {
		entityManager.getTransaction().begin();
		entityManager.remove(bs);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void deleteBigSectionWithSections(BigSection bs) {
		List <Section> sList = bs.getSections();
		entityManager.getTransaction().begin();
		int iteration = sList.size();
		for(int i=0; i<iteration; i++){
			List <Document> dList = sList.get(0).getDocuments();
			int jteration = dList.size();
			if(!dList.isEmpty()){
			for(int j=0; j<jteration; j++){
				Document dtemp = dList.get(0);
				if(dList.get(0).getPublisher()!=null){
					if(dList.get(0).getPublisher().getDocuments().contains(dtemp)){
						dList.get(0).getPublisher().getDocuments().remove(dtemp);
					}
				}
				if(dList.get(0).getReceiver()!=null){
					if(dList.get(0).getReceiver().getDocuments().contains(dtemp)){
						dList.get(0).getReceiver().getDocuments().remove(dtemp);
					}
				}
				dList.remove(dList.get(0));
				entityManager.remove(dtemp);
			}
			}
			Section stemp = sList.get(0);
			sList.remove(sList.get(0));
			entityManager.remove(stemp);	
		}	
		entityManager.remove(bs);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public Section getSectionByID(String id) {
		Query query = entityManager.createQuery("SELECT s FROM Section s WHERE s.id = '"+id+"'", Section.class);
		Section s = (Section) query.getSingleResult();
		return s;
	}

	@Override
	public void deleteSection(Section s) {
		entityManager.getTransaction().begin();
		s.getBigSection().getSections().remove(s);
		entityManager.remove(s);
		entityManager.getTransaction().commit();	
	}

	@Override
	public void deleteSectionWithDocuments(Section s) {
		List <Document> dList = s.getDocuments();
		entityManager.getTransaction().begin();
		s.getBigSection().getSections().remove(s);
		int iteration = dList.size();
		for(int i=0; i<iteration; i++){
			Document dtemp = dList.get(0);
			if(dList.get(0).getPublisher()!=null){
				if(dList.get(0).getPublisher().getDocuments().contains(dList.get(0))){
					dList.get(0).getPublisher().getDocuments().remove(dList.get(0));
				}
			}
			
			if(dList.get(0).getReceiver()!=null){
				if(dList.get(0).getReceiver().getDocuments().contains(dList.get(0))){
					dList.get(0).getReceiver().getDocuments().remove(dList.get(0));
				}
			}
			
			dList.remove(dList.get(0));
			entityManager.remove(dtemp);
		}
		entityManager.remove(s);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public User getUserByUserName(String username) {
		try{
		User u;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username ='"+username+"'", User.class);
		u = (User) query.getSingleResult();
		return u;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public User getUserByPhone(String phone) {
		try{
		User u;
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.phone ='"+phone+"'", User.class);
		u = (User) query.getSingleResult();
		return u;	
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public void add(User u) {
		try{
		 entityManager.getTransaction().begin();
         entityManager.persist(u);
         entityManager.getTransaction().commit();
		} catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
		
	}

	@Override
	public List<User> getAllUsers() {
		List <User> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
		list = query.getResultList();
		return list;
	}

	@Override
	public Group getGroupByName(String name) {
		Group g;
		Query query = entityManager.createQuery("SELECT g FROM Group g WHERE g.name ='"+name+"'", Group.class);
		g = (Group) query.getSingleResult();
		return g;
	}

	@Override
	public User getUserByID(int id) {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id ='"+id+"'", User.class);
		User u = (User) query.getSingleResult();
		return u;
	}

	@Override
	public void deleteUser(User user) {
		try{
		entityManager.getTransaction().begin();
		if(user.getGroup()!=null){
		user.getGroup().getUsers().remove(user);
		}
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<Receiver> ReceiverList() {
		List <Receiver> list = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT r FROM Receiver r", Receiver.class);
		list = query.getResultList();
		return list;
	}

	@Override
	public void add(Receiver r) {
		try{
			 entityManager.getTransaction().begin();
	         entityManager.persist(r);
	         entityManager.getTransaction().commit();
			} catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            ex.printStackTrace();
	        }
		
	}

	@Override
	public Receiver getReceiverByName(String receiverName) {
		try{
		Query query = entityManager.createQuery("SELECT r FROM Receiver r WHERE r.name ='"+receiverName+"'", Receiver.class);
		Receiver r = (Receiver) query.getSingleResult();
		return r;
	} catch(NoResultException e) {
        return null;
    }
	}

	@Override
	public Publisher getPublisherById(int id) {
		Query query = entityManager.createQuery("SELECT p FROM Publisher p WHERE p.id ='"+id+"'", Publisher.class);
		Publisher p = (Publisher) query.getSingleResult();
		return p;
	}

	@Override
	public void deletePublisher(Publisher p) {
		try{
			entityManager.getTransaction().begin();
			entityManager.remove(p);
			entityManager.getTransaction().commit();
			}catch(Exception e){
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void deleteReceiver(Receiver r) {
		try{
			entityManager.getTransaction().begin();
			entityManager.remove(r);
			entityManager.getTransaction().commit();
			}catch(Exception e){
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		
	}

	@Override
	public Receiver getReceiverById(int id) {
		Query query = entityManager.createQuery("SELECT r FROM Receiver r WHERE r.id ='"+id+"'", Receiver.class);
		Receiver r = (Receiver) query.getSingleResult();
		return r;
	}
}
