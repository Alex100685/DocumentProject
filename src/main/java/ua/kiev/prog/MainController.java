package ua.kiev.prog;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.kiev.prog.security.JDBCAccess;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


@Controller
public class MainController {

	@Autowired
	private Actions actions;
	
	@Autowired
    private ShaPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "login";
    }
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView listClients(
            @RequestParam String login,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2
    ) {
		
        if(!password.equals(password2)) {
            return new ModelAndView("login", "errMsg", "Password and confirm password values are not equal!");
        }

        if(actions.getUserByUserName(login) != null) {
            return new ModelAndView("login", "errMsg", "Account with entered login is already exists. Please, enter another login!");
        }
        // generate the "salt" value for password encoding
        byte[] saltBytes = KeyGenerators.secureRandom(2).generateKey();
        String salt = DatatypeConverter.printHexBinary(saltBytes);
        // creating password for smf database
        password = passwordEncoder.encodePassword(password, salt);
        User user = new User();
        user.setUsername(login);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setSalt(salt);
        user.setAuthorized(false);
        user.setLoginAttempts(0);
        actions.add(user);
        return new ModelAndView("login");
    }

	
	@RequestMapping("/client/")
	public ModelAndView listDocs() {		
		List <BigSection> bsList = actions.BigSectionList(); 
		return new ModelAndView("main", "bs", bsList);
	}
	
	@RequestMapping("/admin/deleteDocuments")
	public ModelAndView deleteDocuments(
			@RequestParam(value="id []", required=false) String [] id
			) 
	{
		if(id == null){
			return new ModelAndView("errorPage", "note", "Вы не выбрали ни одного документа для удаления!");
		}
			actions.deleteDocByInvNum(id);
			List <BigSection> bsList = actions.BigSectionList(); 
			return new ModelAndView("main", "bs", bsList);	
	}
	
	
	@RequestMapping("/client/sortByInvNum")
	public ModelAndView sortByInvNum() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByInvNum());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByInvNumRev")
	public ModelAndView sortByInvNumRev() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByInvNumRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByName")
	public ModelAndView sortByName() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByName());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByNameRev")
	public ModelAndView sortByNameRev() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByNameRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByPublisher")
	public ModelAndView sortByPublisher() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByPublisher());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByPublishDate")
	public ModelAndView sortByPublishDate() {
		List <Document> docList = actions.DocumentList();
		for(Document doc: docList){
			if(doc.getPublisher() == null){
				return new ModelAndView("errorPage", "note", "Для сортировки по издателю необходимо указать издателя во всех документах.");
			}
		}
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByPublishDate());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByPublishDateRev")
	public ModelAndView sortByPublishDateRev() {
		List <Document> docList = actions.DocumentList();
		for(Document doc: docList){
			if(doc.getPublisher() == null){
				return new ModelAndView("errorPage", "note", "Для сортировки по издателю необходимо указать издателя во всех документах.");
			}
		}
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByPublishDateRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByPublisherRev")
	public ModelAndView sortByPublisherRev() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByPublisherRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByNote")
	public ModelAndView sortByNote() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByNote());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByNoteRev")
	public ModelAndView sortByNoteRev() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByNoteRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByDocType")
	public ModelAndView sortByDocType() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByDocType());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByDocTypeRev")
	public ModelAndView sortByDocTypeRev() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByDocTypeRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByQuantity")
	public ModelAndView sortByQuantity() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByQuantity());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByQuantityRev")
	public ModelAndView sortByQuantityRev() {
		List <Document> docList = actions.DocumentList();
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByQuantityRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByStatus")
	public ModelAndView sortByStatus() {
		List <Document> docList = actions.DocumentList();
		for(Document doc: docList){
			if(doc.getReceiver() == null){
				return new ModelAndView("errorPage", "note", "Для сортировки по получателю необходимо указать получателя во всех документах.");
			}
		}
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByStatus());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	@RequestMapping("/client/sortByStatusRev")
	public ModelAndView sortByStatusRev() {
		List <Document> docList = actions.DocumentList();
		for(Document doc: docList){
			if(doc.getReceiver() == null){
				return new ModelAndView("errorPage", "note", "Для сортировки по получателю необходимо указать получателя во всех документах.");
			}
		}
		Document [] docArray = new Document [docList.size()];
		for(int i=0; i<docList.size(); i++){
			docArray[i] = docList.get(i);
		}
		Arrays.sort(docArray, new SortByStatusRev());
		
		return new ModelAndView("index", "document", docArray);
	}
	
	
	@RequestMapping("/client/searchByInvNumb")
	public ModelAndView searchByInvNumbl(
			@RequestParam(value="pattern") String pattern
			) {
		return new ModelAndView("index", "document", actions.searchByInvNumbList(pattern));
	}
	
	@RequestMapping("/client/searchByName")
	public ModelAndView searchByName(
			@RequestParam(value="pattern") String pattern
			) {
		return new ModelAndView("index", "document", actions.searchByName(pattern));
	}
	
	@RequestMapping("/client/searchByPublisher")
	public ModelAndView searchByPublisher(
			@RequestParam(value="pattern") String pattern
			) {
		return new ModelAndView("index", "document", actions.searchByPublisher(pattern));
	}
	
	
	
	@RequestMapping(value = "/admin/addDocument", method = RequestMethod.POST)
	public ModelAndView chooseBigSection() {
		List <BigSection> list = actions.BigSectionList();
		return new ModelAndView("choosebigsection", "bigSections", list);
	}
	
	@RequestMapping(value = "/admin/ChooseSmallSection", method = RequestMethod.POST)
	public ModelAndView chooseSmallSection(
			@RequestParam(value="SelectBigSection") String name
			) {
		if(actions.smallSectionList(name).size() == 0){
			List <Section> sList = new ArrayList<>();
			Section s = new Section();
			s.setId("");
			s.setName("");
			sList.add(s);
			return new ModelAndView("choosesmallsection", "Sections", sList);
		}
		List <Object> all = new ArrayList<>();
		all.add(actions.smallSectionList(name));
		all.add(name);
		return new ModelAndView("choosesmallsection", "all", all);
	}
	
	
	
	
	@RequestMapping("/admin/addBigSection")
	public ModelAndView addBigSection(Model model) {
		List <BigSection> sList = actions.BigSectionList();
		List <Integer> iList = new ArrayList<>();
		for(BigSection s: sList){
			iList.add(Integer.parseInt(s.getId()));	
		}
		int i=1;
		for(; i<(iList.size()+2); i++){
			if(!iList.contains(i)){
				break;
			}
		}
		return new ModelAndView ("addbigsection", "id", (i));
	}
	
	
	@RequestMapping("/admin/createNewPublisher")
	public String createNewPublisher(Model model) {
		return "createNewPublisher";
	}
	
	@RequestMapping("/admin/addSmallSection")
	public ModelAndView addSmallSection(Model model) {
		return new ModelAndView ("addsmallsection", "BigSection", actions.BigSectionList());
	}
	
	
	@RequestMapping(value = "/admin/SaveNewBigSection", method = RequestMethod.POST)
	public ModelAndView saveBigSection(
			@RequestParam(value="id") String id,
			 @RequestParam(value="name") String name
			) {
		List <BigSection> list = actions.BigSectionList();
		if("".equals(id)){
			return new ModelAndView("addbigsectionerror");
		}
		for(BigSection bs : list){
			if(bs.getId().equals(id) || bs.getName().equals(name)){
				return new ModelAndView("addbigsectionerror");
				
			}
		}
		BigSection bs = new BigSection();
		bs.setName(name);
		bs.setId(id);
		actions.add(bs);
		List <BigSection> list2 = actions.BigSectionList();
		
		return new ModelAndView("choosebigsection", "bigSections", list2);	
	}
	
	
	@RequestMapping(value = "/admin/saveSmallSection", method = RequestMethod.POST)
	public ModelAndView saveSmallSection(
			@RequestParam(value="UpperSection") String upperSection,
			@RequestParam(value="number") String id,
			 @RequestParam(value="name") String name
			) {
		
		List <Section> list = actions.smallSectionList(upperSection);
		if("".equals(id)){
			int i=0;
			for(Section s: list){
				String [] invPart = s.getId().split("\\.");
				if(Integer.parseInt(invPart[1])>=i){
					i=Integer.parseInt(invPart[1]);
					i++;
				}
			}
			
			for(i=0;i<list.size(); i++){
				if(!(list.get(i).getId()).contains("."+String.valueOf(i+1))){
					break;
				}			
			}
			id = String.valueOf(i);
		}
		for(Section s : list){
			if(s.getId().equals(id) || s.getName().equals(name)){
				return new ModelAndView("addsmallsectionerror");
				
			}
		}
		BigSection bs = actions.getBigSectionByName(upperSection);
		Section s = new Section();
		s.setName(name);
		s.setId(bs.getId()+"."+id);
		s.setBigSection(bs);
		actions.add(bs);
		
		return new ModelAndView("choosebigsection", "bigSections", actions.BigSectionList());	
	}

	
	@RequestMapping(value = "/admin/toDocumentData", method = RequestMethod.POST)
	public ModelAndView toDocumentData(
			@RequestParam(value="smallSection") String sectionName,
			@RequestParam(value="bigSection") String bigSectionName
			)
	{
		if(sectionName.equals("")){
			return new ModelAndView("errorPage", "note", "В данном разделе нет подразделов, создайте сначала подраздел!");
		}
		List <Publisher> pList = actions.PublisherList();
		List <Receiver> rList = actions.ReceiverList();
		BigSection b = actions.getBigSectionByName(bigSectionName);
		List <Section> sList = b.getSections();
		Section s = null;
		for(Section s1: sList){
			if(s1.getName().equals(sectionName)){
				s = s1;
			}
		}
		List <Document> dList = s.getDocuments();
		int i=1;
		for(Document d: dList){
			String [] invPart = d.getInventaryNumber().split("\\.");
			if(Integer.parseInt(invPart[2])>=i){
				i=Integer.parseInt(invPart[2]);
				i++;
			}
		}
		
		List <Object> all = new ArrayList<>();
		all.add(pList);
		all.add(s);
		all.add(i);
		all.add(rList);
		
				return new ModelAndView ("add_page", "all", all);
	}
	
	
	@RequestMapping(value = "/admin/edit")
	public ModelAndView editData(
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		List <Receiver> rList = actions.ReceiverList();
		List <Publisher> pList = actions.PublisherList();
		List <Object> all = new ArrayList<>();
		String inventaryNumber = request.getParameter("in");
		Document d = actions.getDocByInvNym(inventaryNumber);
		all.add(pList);
		all.add(d);
		all.add(rList);
		return new ModelAndView ("edit_page", "all", all);
	}
	
	@RequestMapping(value = "/admin/deleteFile")
	public ModelAndView deleteFile(
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		String invName = request.getParameter("in");
		Document d = actions.getDocByInvNym(invName);
		d.setFileBody(null);
		d.setFileName(null);
		List <Receiver> rList = actions.ReceiverList();
		List <Publisher> pList = actions.PublisherList();
		List <Object> all = new ArrayList<>();
		all.add(pList);
		all.add(d);
		all.add(rList);
		
		
		return new ModelAndView ("edit_page", "all", all);
	}
	
	
	@RequestMapping(value = "/admin/uploadFile")
	public ModelAndView uploadFile(
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		String inventaryNumber = request.getParameter("in");
		Document d = actions.getDocByInvNym(inventaryNumber);
		
		return new ModelAndView ("upload", "document", d);
	}
	
	@RequestMapping(value = "/admin/upload")
	public ModelAndView upload(
			@RequestParam(value="inventaryNumber") String inventaryNumber,
			 @RequestParam(value="file") MultipartFile file
			)
	{	
		Document d = actions.getDocByInvNym(inventaryNumber);
		d.setFileName(file.getOriginalFilename());
		String cyrillicChars = "йцукенгшщзхъфывапролджэячсмитьбюё";
		for(int i=0; i<cyrillicChars.length(); i++){
			if(file.getOriginalFilename().contains(cyrillicChars.substring(i, i+1))){
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
				d.setFileName("content"+suffix);
				break;
		}
		}
		try {
			d.setFileBody(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions.add(d);
		List <BigSection> bsList = actions.BigSectionList(); 
		return new ModelAndView("main", "bs", bsList);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/admin/createDocument", method = RequestMethod.POST)
	public ModelAndView addDoc(
				
				
						@RequestParam(value="sectionName") String sectionName,
						 @RequestParam(value="numberInSection") String inventaryNumber,
						 @RequestParam(value="name") String name,
						 @RequestParam(value="publisher") String publisher,
						 @RequestParam(value="publishDate") String publishDate,
						 @RequestParam(value="receiver") String receiver,
						 @RequestParam(value="note") String note,
						 @RequestParam(value="doctype") String doctype,
						 @RequestParam(value="quantity") int quantity,
						 @RequestParam(value="file") MultipartFile file,
						 HttpServletRequest request,
						 HttpServletResponse response
						
						 
			)
	{
		try {
			
			
			
			Document doc = new Document();
			Section s = actions.getSectionByName(sectionName);
			List <Document> dList = s.getDocuments();
			if("".equals(inventaryNumber)){
				
				int i=0;
				for(Document d: dList){
					String [] invPart = d.getInventaryNumber().split("\\.");
					if(Integer.parseInt(invPart[2])>=i){
						i=Integer.parseInt(invPart[2]);
						i++;
					}
				}
				inventaryNumber = String.valueOf(i);
			}
			for(Document d: dList){
				if((s.getId()+"."+inventaryNumber).equals(d.getInventaryNumber())){
					return new ModelAndView("errorPage", "note", "Указанный Вами номер документа уже существует!");
				}
			}
			doc.setSection(s);
			doc.setInventaryNumber(s.getId()+"."+inventaryNumber);
			doc.setName(name);
			
			
			Publisher p = actions.getPublisherByName(publisher);
			doc.setPublisher(p);
			doc.setPublishDate(publishDate);
			if(!receiver.equals("")){
			Receiver r = actions.getReceiverByName(receiver);
			doc.setReceiver(r);
			}
			doc.setNote(note);
			doc.setDocType(doctype);
			doc.setQuantity(quantity);
			doc.setFileBody(file.getBytes());
			doc.setFileName(file.getOriginalFilename());
			String cyrillicChars = "йцукенгшщзхъфывапролджэячсмитьбюё";
			for(int i=0; i<cyrillicChars.length(); i++){
				if(file.getOriginalFilename().contains(cyrillicChars.substring(i, i+1))){
					String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
					doc.setFileName("content"+suffix);
					break;
			}
			}
			if(file.getOriginalFilename() == ""){
				doc.setFileName(null);
			}
			
					
			actions.add(doc);
			List <BigSection> bsList = actions.BigSectionList(); 
			return new ModelAndView("main", "bs", bsList);
		} catch (IOException ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
	
	@RequestMapping(value = "/admin/SaveNewPublisher", method = RequestMethod.POST)
	public ModelAndView saveBigSection(
			@RequestParam(value="nameOfPublisher") String nameOfPublisher
			) {
	
		List <Publisher> pList = actions.PublisherList();
		for(int i=0; i<pList.size(); i++){
			if(pList.get(i).getName().equals(nameOfPublisher)){
				return new ModelAndView("createNewPublishererror");
			}
		}
		
		Publisher p = new Publisher();
		p.setName(nameOfPublisher);
		actions.add(p);
		List <BigSection> list = actions.BigSectionList();
		
		return new ModelAndView("choosebigsection", "bigSections", list);	
	}
	
	@RequestMapping(value = "/admin/SaveNewPublisherFromMain", method = RequestMethod.POST)
	public ModelAndView saveNewPublisherFroMain(
			@RequestParam(value="nameOfPublisher") String nameOfPublisher
			) {
		List <Publisher> pList = actions.PublisherList();
		for(int i=0; i<pList.size(); i++){
			if(pList.get(i).getName().equals(nameOfPublisher)){
				return new ModelAndView("addPublisherFromMain", "note", "Издатель с таким именем уже существует");
			}
		}
		Publisher p = new Publisher();
		p.setName(nameOfPublisher);
		actions.add(p);
		List <BigSection> list = actions.BigSectionList();
		return new ModelAndView("main", "bs", list);
	}
	
	@RequestMapping(value = "/admin/SaveNewReceiverFromMain", method = RequestMethod.POST)
	public ModelAndView saveNewReceiverFroMain(
			@RequestParam(value="nameOfReceiver") String nameOfReceiver
			) {
		List <Receiver> rList = actions.ReceiverList();
		for(int i=0; i<rList.size(); i++){
			if(rList.get(i).getName().equals(nameOfReceiver)){
				return new ModelAndView("addReceiverFromMain", "note", "Получатель с таким именем уже существует");
			}
		}
		Receiver r = new Receiver();
		r.setName(nameOfReceiver);
		actions.add(r);
		List <BigSection> list = actions.BigSectionList();
		return new ModelAndView("main", "bs", list);
	}
	
	@RequestMapping(value = "/admin/addPublisherFromMain")
	public ModelAndView addNewPublisherFroMain()
	{
		return new ModelAndView("addPublisherFromMain", "note", "");
	}
	
	@RequestMapping(value = "/admin/addReceiverFromMain")
	public ModelAndView addReceiverFromMain()
	{
		return new ModelAndView("addReceiverFromMain", "note", "");
	}
	
	
	
	
	
	
	@RequestMapping(value = "/admin/saveEditedDoc2", method = RequestMethod.POST)
	public ModelAndView saveEditedDoc2(
						@RequestParam(value="inventaryNumber") String inventaryNumber,
						 @RequestParam(value="name") String name,
						 @RequestParam(value="publisher") String publisherName,
						 @RequestParam(value="publishDate") String publishDate,
						 @RequestParam(value="receiver") String receiverName,
						 @RequestParam(value="note") String note,
						 @RequestParam(value="doctype") String doctype,
						 @RequestParam(value="quantity") int quantity,
						 @RequestParam(value="file") MultipartFile file,
						 HttpServletRequest request,
						 HttpServletResponse response)
	{
		
		Document doc = actions.getDocByInvNym(inventaryNumber);
		//doc.setPublishDate(publishDate);
		doc.setName(name);
		Receiver rOld = doc.getReceiver();
		if(rOld!=null){
		rOld.getDocuments().remove(doc);
		}
		Receiver receiverNew = actions.getReceiverByName(receiverName);
		if(!receiverNew.getDocuments().contains(doc)){
		receiverNew.getDocuments().add(doc);
		}
		doc.setReceiver(receiverNew);
		doc.setNote(note);
		doc.setDocType(doctype);
		doc.setQuantity(quantity);
		try {
			doc.setFileBody(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		doc.setFileName(file.getOriginalFilename());
		String cyrillicChars = "йцукенгшщзхъфывапролджэячсмитьбюё";
		for(int i=0; i<cyrillicChars.length(); i++){
			if(file.getOriginalFilename().contains(cyrillicChars.substring(i, i+1))){
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
				doc.setFileName("content"+suffix);
				break;
		}
		}
		if(file.getOriginalFilename() == ""){
			doc.setFileName(null);
		}
		Publisher pOld = doc.getPublisher();
		if(pOld!=null){
		pOld.getDocuments().remove(doc);
		}
		Publisher pNew = actions.getPublisherByName(publisherName);
		if(!pNew.getDocuments().contains(doc)){
			pNew.getDocuments().add(doc);
		}
		doc.setPublisher(pNew);
		doc.setPublishDate(publishDate);
		
		actions.add(doc);
		
		List <BigSection> bsList = actions.BigSectionList(); 
		return new ModelAndView("main", "bs", bsList);
	}
	
	@RequestMapping(value = "/admin/saveEditedDoc", method = RequestMethod.POST)
	public ModelAndView saveEditedDoc(
						@RequestParam(value="inventaryNumber") String inventaryNumber,
						 @RequestParam(value="name") String name,
						 @RequestParam(value="publisher") String publisherName,
						 @RequestParam(value="publishDate") String publishDate,
						 @RequestParam(value="receiver") String receiverName,
						 @RequestParam(value="note") String note,
						 @RequestParam(value="doctype") String doctype,
						 @RequestParam(value="quantity") int quantity,
						 HttpServletRequest request,
						 HttpServletResponse response)
	{
		
		Document doc = actions.getDocByInvNym(inventaryNumber);
		//doc.setPublishDate(publishDate);
		doc.setName(name);
		
		Receiver rOld = doc.getReceiver();
		if(rOld!=null){
			rOld.getDocuments().remove(doc);
			}
		Receiver receiverNew = actions.getReceiverByName(receiverName);
		if(!receiverNew.getDocuments().contains(doc)){
		receiverNew.getDocuments().add(doc);
		}
		doc.setReceiver(receiverNew);
		doc.setNote(note);
		doc.setDocType(doctype);
		doc.setQuantity(quantity);
		
		Publisher pOld = doc.getPublisher();
		if(pOld!=null){
			pOld.getDocuments().remove(doc);
			}
		Publisher p = actions.getPublisherByName(publisherName);
		if(!p.getDocuments().contains(doc)){
			p.getDocuments().add(doc);
		}
		doc.setPublisher(p);
		doc.setPublishDate(publishDate);
		
		actions.add(doc);
		
		List <BigSection> bsList = actions.BigSectionList(); 
		return new ModelAndView("main", "bs", bsList);
	}
	
	@RequestMapping(value = "/admin/downloadFile")
	public void downloadFile(
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		String invNumb = request.getParameter("in");
		Document d = actions.getDocByInvNym(invNumb);
		
		response.setCharacterEncoding("Content-Transfer-Encoding:binary");
		response.setContentType("Content-Type:application/octet-stream");
		response.setHeader("Content-Disposition:attachment; filename=", d.getFileName());
		response.setContentLength((int) d.getFileBody().length);
		OutputStream os;
		BufferedOutputStream bos;
		try {
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			bos.write(d.getFileBody());
			bos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/admin/deleteBigSection")
	public ModelAndView deleteBigSection(
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		String id = request.getParameter("id");
		BigSection bs = actions.getBigSectionByID(id);
		
		if(bs.getSections().isEmpty()){
			
			actions.deleteBigSection(bs);
			
			return new ModelAndView("main", "bs", actions.BigSectionList());
		}
		else{
			
			return new ModelAndView("confirmBigSectionDelete", "bs", bs);
		}
	}
	
	@RequestMapping(value = "/admin/deleteBSAnyway")
	public ModelAndView deleteBigSectionAnyway(
			 @RequestParam(value="bsId") String id,
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		BigSection bs = actions.getBigSectionByID(id);
		actions.deleteBigSectionWithSections(bs);
			return new ModelAndView("main", "bs", actions.BigSectionList());
	}
	
	@RequestMapping(value = "/admin/deleteSection")
	public ModelAndView deleteSection(
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		String id = request.getParameter("id");
		Section s = actions.getSectionByID(id);
		
		if(s.getDocuments().isEmpty()){
			
			actions.deleteSection(s);
			
			return new ModelAndView("main", "bs", actions.BigSectionList());
		}
		else{
			
			return new ModelAndView("confirmSectionDelete", "s", s);
		}
	}
	
	@RequestMapping(value = "/admin/deleteSAnyway")
	public ModelAndView deleteSectionAnyway(
			 @RequestParam(value="sId") String id,
			HttpServletRequest request,
			 HttpServletResponse response
			)
	{	
		Section s = actions.getSectionByID(id);
		actions.deleteSectionWithDocuments(s);
			return new ModelAndView("main", "bs", actions.BigSectionList());
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/client/seeFile")
	public void seeFile(
			 
			HttpServletRequest request,
			 HttpServletResponse response
			) throws IOException, DocumentException
	{
		
		String tempDir = System.getProperty("java.io.tmpdir");
		File f = new File(tempDir+"/Arial.ttf");
		
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			Fonts fonts = actions.getFontByName("Arial.ttf");
			byte [] fontBody = fonts.getFontBody();
			fos.write(fontBody);
			fos.close();
		
		String invNum = request.getParameter("in");
		Document d = actions.getDocByInvNym(invNum);
		String fileName = d.getFileName();
		byte [] file = d.getFileBody();
		String suffix = fileName.substring(fileName.indexOf(".")+1, fileName.length());
		if(!suffix.equals("xls") && !suffix.equals("doc") && !suffix.equals("pdf")){
			file = MyPDFCreator.createPdfWarning();
			suffix = "pdf";
		}
		response.reset();
		response.setBufferSize(1024);
	//	response.setHeader("Content-Disposition", "attachment;filename=data."+suffix);
		
		
		if(suffix.equals("doc")){
			file = MyWordPdfConverter.convertWordToPdf(file);
			suffix = "pdf";
		}
		if(suffix.equals("xls")){
		file = MyPdfConverter.convertExcelToPdf(file);
		suffix = "pdf";
		}
		if(suffix.equals("pdf")){
			response.setContentType("application/pdf");
			file = MyPdfEncyptor.encryptNoCopy(file);
			
			OutputStream os = response.getOutputStream(); 
			
			os.write(file);
		}	
	}
	
	@RequestMapping(value = "/superadmin/accessManagement")
	public ModelAndView accessManagement(){
		List <User> allUsers = actions.getAllUsers();
		for(int i=0; i<allUsers.size(); i++){
			if(allUsers.get(i).getUsername().equals("superadmin")){
				allUsers.remove(i);
			}
		}
		return new ModelAndView ("accessManagement", "Users", allUsers);		
	}
	
	@RequestMapping(value = "/admin/deletePublisherFromMain")
	public ModelAndView deletePublisherFromMain(){
		List <Object> all = new ArrayList<>();
		List <Publisher> pList = actions.PublisherList();
		all.add(pList);
		all.add("");
		return new ModelAndView ("deletePublisherFromMain", "all", all);		
	}
	
	@RequestMapping(value = "/admin/deleteReceiverFromMain")
	public ModelAndView deleteReceiverFromMain(){
		List <Object> all = new ArrayList<>();
		List <Receiver> rList = actions.ReceiverList();
		all.add(rList);
		all.add("");
		return new ModelAndView ("deleteReceiverFromMain", "all", all);
	}
	
	@RequestMapping(value = "/admin/deletePublisher")
	public ModelAndView deletePublisher(
			HttpServletRequest request,
			 HttpServletResponse response
			){
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		Publisher p = actions.getPublisherById(id);
		List <Document> dList = p.getDocuments();
		String note = "";
		List <Object> all = new ArrayList<>();
		if(!dList.isEmpty()){
			List <Publisher> pList = actions.PublisherList();
			note = "Существуют документы, которые привязаны к данному издателю! Удаление невозможно!";
			all.add(pList);
			all.add(note);
					return new ModelAndView ("deletePublisherFromMain", "all", all);
		}
		
		actions.deletePublisher(p);
		List <Publisher> pList = actions.PublisherList();
		all.add(pList);
		all.add(note);
		return new ModelAndView ("deletePublisherFromMain", "all", all);		
	}
	
	@RequestMapping(value = "/admin/deleteReceiver")
	public ModelAndView deleteReceiver(
			HttpServletRequest request,
			 HttpServletResponse response
			){
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		Receiver r = actions.getReceiverById(id);
		List <Document> dList = r.getDocuments();
		String note = "";
		List <Object> all = new ArrayList<>();
		if(!dList.isEmpty()){
			List <Receiver> rList = actions.ReceiverList();
			note = "Существуют документы, которые привязаны к данному получателю! Удаление невозможно!";
			all.add(rList);
			all.add(note);
					return new ModelAndView ("deleteReceiverFromMain", "all", all);
		}
		
		actions.deleteReceiver(r);
		List <Receiver> rList = actions.ReceiverList();
		all.add(rList);
		all.add(note);
		return new ModelAndView ("deleteReceiverFromMain", "all", all);		
	}
	
	
	
	
	
	@RequestMapping(value = "/superadmin/saveAccessLevels")
	public ModelAndView saveAccessLevels(
			@RequestParam("access") List<String> accesses,
			@RequestParam("authorized") List<String> authorized
			){
		Group gAdmins = actions.getGroupByName("Admins");
		Group gSuperAdmins = actions.getGroupByName("Superadmins");
		List <User> allUsers = actions.getAllUsers();
		for(int i=0; i<allUsers.size(); i++){
			if(allUsers.get(i).getUsername().equals("superadmin")){
				allUsers.remove(i);
			}
		}
		JDBCAccess access = new JDBCAccess();
		for(int i=0; i<allUsers.size(); i++){
			if(accesses.get(i).equals("Admin")){
				allUsers.get(i).setGroup(gAdmins);
			}
			if(accesses.get(i).equals("Superadmin")){
				allUsers.get(i).setGroup(gSuperAdmins);
			}
			if(accesses.get(i).equals("Client")){
				allUsers.get(i).setGroup(null);
			}
			if(authorized.get(i).equals("Yes")){
				allUsers.get(i).setAuthorized(true);
				allUsers.get(i).setLoginAttempts(0);
				access.updateAuthorizedTrue(allUsers.get(i).getUsername());
			}
			if(authorized.get(i).equals("No")){
				allUsers.get(i).setAuthorized(false);
				access.updateAuthorizedFalse(allUsers.get(i).getUsername());
			}	
			actions.add(allUsers.get(i));
		}
		return new ModelAndView ("accessManagement", "Users", allUsers);
	}
	
	@RequestMapping(value = "/superadmin/deleteUser")
	public ModelAndView deleteUser(
			HttpServletRequest request,
			 HttpServletResponse response
			 ){
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		User user = actions.getUserByID(id);
		if(user.getGroup() !=null){
			user.getGroup().getUsers().remove(user);
		}
		actions.deleteUser(user);
		List <User> allUsers = actions.getAllUsers();
		for(int i=0; i<allUsers.size(); i++){
			if(allUsers.get(i).getUsername().equals("superadmin")){
				allUsers.remove(i);
			}
		}
		return new ModelAndView ("accessManagement", "Users", allUsers);
	}
	
	@RequestMapping(value = "/superadmin/importFromExcel")
	public ModelAndView importFromExcel(){
		String note = "";
		return new ModelAndView ("importFromXls", "note", note);
	}
		
		@RequestMapping(value = "/superadmin/saveDocsFromExcel")
		public ModelAndView saveDocsFromExce(
				@RequestParam(value="file") MultipartFile file
				) throws IOException{
			
			if(!file.getOriginalFilename().endsWith(".xls")){
				String note = "File must be in Excel format. It must end with \".xls\". Please choose the correct file";
				return new ModelAndView ("importFromXls", "note", note);
			}
			
			byte [] xlsData = file.getBytes();
			ByteArrayInputStream is = new ByteArrayInputStream(xlsData);
	         HSSFWorkbook my_xls_workbook = new HSSFWorkbook(is);
	         HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
	         Iterator<Row> rowIterator = my_worksheet.iterator();
	         Document d = null;
	         while(rowIterator.hasNext()) {
	        	 
                 Row row = rowIterator.next(); 
                 Iterator<Cell> cellIterator = row.cellIterator();
                 
                         while(cellIterator.hasNext()) {
                        	 	
                                 Cell cell = cellIterator.next();
                                 String value = null;
                                 
                                 switch(cell.getCellType()) { //Identify CELL type
                                         
                                 case Cell.CELL_TYPE_NUMERIC:
                                	 if(HSSFDateUtil.isCellDateFormatted(cell)){
                                		Date date =  cell.getDateCellValue();
                                		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                		value = sdf.format(date); 
                                	 }
                                	 else{
                                	 value = String.valueOf(cell.getNumericCellValue());
                                	 }
                                	 
                                	 break; 
                                 case Cell.CELL_TYPE_STRING:
                                     value = cell.getStringCellValue();
                                      
                                          
                                      break;
                                 case Cell.CELL_TYPE_FORMULA:
                                	 if(Cell.CELL_TYPE_NUMERIC == cell.getCachedFormulaResultType()){
                                	value = String.valueOf(cell.getNumericCellValue());
                                		 
                                		 break;
                                		 
                                	 }
                                	 else{
                                	value = cell.getStringCellValue();
                                		 
                                		 break;
                                	 }       
                                 }
                                 
                                 if(value!=null){
                                 String [] divVal = value.split(" ");
                                 String a = divVal[0];
                                 String [] aaa = a.split("\\.");
                                 //check on big section
                                 if(aaa.length == 1 && divVal.length >= 2 && divVal[0].endsWith(".") && cell.getColumnIndex() == 0){
                                	 
                                	 BigSection bs = null;
                                		 bs = new BigSection();
                                		 int firstPoint =  divVal[0].indexOf(".");
                                		 String invNum = divVal[0].substring(0, firstPoint);
                                		 String bsName = value.substring(firstPoint+2, value.length());
                                		 List <BigSection> list = actions.BigSectionList();
                                			for(BigSection b : list){
                                				if(b.getId().equals(invNum)){
                                					String note = "Обнаружены одинаковые номера больших секций"+invNum+". Это не допустимо. Пожалуйста исправьте в файле";
                                					return new ModelAndView ("importFromXls", "note", note);
                                				}if(b.getName().equals(bsName)){
                                					String note = "Обнаружены одинаковые названия больших секций"+bsName+". Это не допустимо. Пожалуйста исправьте в файле";
                                					return new ModelAndView ("importFromXls", "note", note);
                                    				}
                                			}
                                				
                                		bs.setId(invNum);	
                                		 bs.setName(bsName);
                                		 actions.add(bs);
                                	 
                                	 break;	 
                                 }
                                 //check on small section
                                 if(aaa.length == 2 && divVal.length >= 2 && cell.getColumnIndex() == 0){
                                	 
                                	 Section s = null;
                                	 BigSection bs = null;
                                	 if((aaa[0]+"."+aaa[1]).equals(a)){
                                		 s = new Section();
                                		 String invNum = divVal[0];
                                		 s.setId(invNum);
                                		 String sName = value.substring(divVal[0].length()+1, value.length());
                                		 s.setName(sName);
                                		 bs = actions.getBigSectionByID(divVal[0].substring(0, divVal[0].indexOf(".")));
                                		 
                                		 List <Section> sList = bs.getSections();
                                		 for(Section sec : sList){
                                			 if(sec.getId().equals(invNum)){
                                				 String note = "Обнаружены одинаковые номера подсекций "+invNum+". Это не допустимо. Пожалуйста исправьте в файле";
                             					return new ModelAndView ("importFromXls", "note", note);
                             				}if(sec.getName().equals(sName)){
                             					String note = "Обнаружены одинаковые названия  подсекций "+sName+". Это не допустимо. Пожалуйста исправьте в файле";
                             					return new ModelAndView ("importFromXls", "note", note);
                                 				}
                                			}
                                		 s.setBigSection(bs);
                                	 }
                                	 actions.add(bs);
                                 }
                                if(aaa.length==3 && divVal.length == 1 && cell.getColumnIndex() == 0){
                                		 d = new Document();
                                		 d.setInventaryNumber(value);
                                		 String upperSectionId = aaa[0]+"."+aaa[1];
                                		 Section s = actions.getSectionByID(upperSectionId);
                                		 List <Document> dList = s.getDocuments();
                                		 for(Document docs: dList){
                                			 if(docs.getInventaryNumber().equals(value)){
                                				 String note = "Обнаружены одинаковые инв. номера документов "+value+". Это не допустимо. Пожалуйста исправьте в файле";
                              					return new ModelAndView ("importFromXls", "note", note);
                                			 }
                                		 } 
                                		 d.setSection(s);
                                		 d.setDocType("");
                                		 d.setQuantity(0);
                                		 actions.add(d);
                                 }else{
                                	 if(cell.getColumnIndex() == 1){
                                		 if(d!=null && value!=null){
                                		 d.setName(value);
                                		 actions.add(d);
                                		 }
                                	 }
                                	 if(cell.getColumnIndex() == 2){
                                		 if(d!=null && value!=null){
                                			List <Publisher> pList = actions.PublisherList();
                                			boolean publisherExists = false;
                                			for(int i=0; i<pList.size(); i++){
                                				if(pList.get(i).getName().equals(value)){
                                					d.setPublisher(pList.get(i));
                                					if(!pList.get(i).getDocuments().contains(d)){
                                					pList.get(i).getDocuments().add(d);
                                					}
                                					publisherExists = true;
                                					break;
                                				}
                                			}
                                			 if(publisherExists == false){
                                		 Publisher p = new Publisher();
                                		 p.setName(value);
                                		 actions.add(p);
                                		 d.setPublisher(p);
                                		 if(!p.getDocuments().contains(d)){
                                		 p.getDocuments().add(d);
                                		 }
                                			 }
                                		 actions.add(d);
                                		 }
                                	 }
                                	 if(cell.getColumnIndex() == 3){
                                		 if(d!=null && value!=null){
                                		 d.setPublishDate(value);
                                		 actions.add(d);
                                		 }
                                	 }
                                	 if(cell.getColumnIndex() == 4){
                                		 if(d!=null && value!=null){
                                 			List <Receiver> rList = actions.ReceiverList();
                                 			boolean receiverExists = false;
                                 			for(int i=0; i<rList.size(); i++){
                                 				if(rList.get(i).getName().equals(value)){
                                 					d.setReceiver(rList.get(i));
                                 					if(!rList.get(i).getDocuments().contains(d)){
                                 					rList.get(i).getDocuments().add(d);
                                 					}
                                 					receiverExists = true;
                                 					break;
                                 				}
                                 			}
                                 			 if(receiverExists == false){
                                 		 Receiver r = new Receiver();
                                 		 r.setName(value);
                                 		 actions.add(r);
                                 		 d.setReceiver(r);
                                 		 if(!r.getDocuments().contains(d)){
                                 		 r.getDocuments().add(d);
                                 		 }
                                 			 }
                                 		 actions.add(d);
                                 		 }
                                	 }
                                	 if(cell.getColumnIndex() == 5){
                                		 if(d!=null && value!=null){
                                		 d.setNote(value);
                                		 actions.add(d);
                                		 }
                                	 }
                                	 if(cell.getColumnIndex() == 6){
                                		 if(d!=null && value!=null){
                                		 d.setDocType(value);
                                		 actions.add(d);
                                		 }
                                	 }
                                	 if(cell.getColumnIndex() == 7){
                                		 if(d!=null && value!=null){
                                		 int quant = 0;
                                		 {
                                			 if(!value.contains("-")){
                                		 quant = Integer.parseInt(aaa[0]);
                                			 }
                                		 d.setQuantity(quant);
                                		 actions.add(d);
                                		 }
                                		 }
                                	 } 
                                 } 
                                 }
                         }
         }
			List <BigSection> bsList = actions.BigSectionList(); 
			return new ModelAndView("main", "bs", bsList);
			
		}
		
		@RequestMapping(value = "/superadmin/uploadFonts")
		public ModelAndView uploadFonts(){
			List <Fonts> fontList = actions.fontsList();
			String note = "";
			List <Object> all = new ArrayList<>();
			all.add(fontList);
			all.add(note);
			return new ModelAndView("uploadFont", "all", all);
		}
		
		@RequestMapping(value = "/superadmin/saveFont")
		public ModelAndView saveFont(
				@RequestParam(value="file") MultipartFile file
				){
			
			String note = "";
			Fonts f = new Fonts();
			List <Fonts> fontList1 = actions.fontsList();
			if(!file.getOriginalFilename().endsWith(".ttf")){
				List <Object> all = new ArrayList<>();
				all.add(fontList1);
				note = "Файл не является шрифтом, найдите файл с расширением 'ttf'";
				all.add(note);
			}
			for(int i=0; i<fontList1.size(); i++){
				if(fontList1.get(i).getName().equals(file.getOriginalFilename())){
					List <Object> all = new ArrayList<>();
					all.add(fontList1);
					note = "Такой шрифт уже существует";
					all.add(note);
					return new ModelAndView("uploadFont", "all", all);
				}
			}
			try {
				f.setFontBody(file.getBytes());
				f.setName(file.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actions.add(f);
			List <Object> all = new ArrayList<>();
			note = "";
			List <Fonts> fontList = actions.fontsList();
			all.add(fontList);
			all.add(note);
			return new ModelAndView("uploadFont", "all", all);
		}
		
		@RequestMapping(value = "/superadmin/deleteFont")
		public ModelAndView deleteFont(
				HttpServletRequest request,
				 HttpServletResponse response
				){
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			Fonts f = actions.getFontById(id);
			actions.deleteFont(f);
			List <Object> all = new ArrayList<>();
			String note = "";
			List <Fonts> fontList = actions.fontsList();
			all.add(fontList);
			all.add(note);
			return new ModelAndView("uploadFont", "all", all);
		}
		
		
		
	
	
	
	
	
}