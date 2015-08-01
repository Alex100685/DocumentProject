package ua.kiev.prog;

import javax.persistence.EntityManager;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class AutoconnectionAspect {
	
	@Autowired
    private EntityManager entityManager;
	
	@Before ("execution (* ua.kiev.prog.ActionsImpl.deleteBigSection(..))")
	public void startTransaction(){
		entityManager.getTransaction().begin();
		System.out.println("Transaction begin");
	}
	
	@After ("execution (* ua.kiev.prog.ActionsImpl.deleteBigSection(..))")
	public void finishTransaction(){
		entityManager.getTransaction().commit();
		System.out.println("Transaction finish");
	}

}
