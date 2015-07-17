package ua.kiev.prog;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



public interface Actions {
	
	List <Document> DocumentList();
	List <BigSection> BigSectionList();
	List <Section> sectionList();
	List <Publisher> PublisherList();
	List <Document> searchByInvNumbList(String pattern);
	List <Document> searchByName(String pattern);
	List <Document> searchByPublisher(String pattern);
	
	public void add(BigSection bs);
	public void add(Document d);
	public void add(Publisher d);
	public void add(User u);
	
	List <Section> smallSectionList(String BigSectionName);
	public BigSection getBigSectionByName(String name);
	public BigSection getBigSectionByID(String id);
	public Section getSectionByName(String name);
	public Publisher getPublisherByName(String name);
	public Document getDocByInvNym(String inventaryNumber);
	void deleteDocByInvNum(String [] invNum);
	void deleteBigSection(BigSection bs);
	void deleteBigSectionWithSections(BigSection bs);
	Section getSectionByID(String id);
	void deleteSection(Section s);
	void deleteSectionWithDocuments(Section s);
	public User getUserByUserName(String username);
	public User getUserByPhone(String phone);
	List <User> getAllUsers();
	public Group getGroupByName(String name);
	public User getUserByID(int id);
	public void deleteUser(User user);
	List<Receiver> ReceiverList();
	public void add(Receiver p);
	public Receiver getReceiverByName(String receiverName);
	public Publisher getPublisherById(int id);
	public void deletePublisher(Publisher p);
	public void deleteReceiver(Receiver r);
	public Receiver getReceiverById(int id);
	public Fonts getFontByName(String string);
	public List<Fonts> fontsList();
	public void add(Fonts f);
	public Fonts getFontById(int id);
	public void deleteFont(Fonts f);
	public List<LoginRecord> getLoginRecordListLim(int limit);
	public void deleteLoginHistory(LoginRecord lr);
	

}
