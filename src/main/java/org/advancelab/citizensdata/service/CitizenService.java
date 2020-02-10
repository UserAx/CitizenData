package org.advancelab.citizensdata.service;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.advancelab.citizensdata.database.Hibernatedb;
import org.advancelab.citizensdata.exception.DuplicationEntryException;
import org.advancelab.citizensdata.exception.EmptyRecordException;
import org.advancelab.citizensdata.exception.InvalidQueryArgumentException;
import org.advancelab.citizensdata.exception.RecordNotFoundException;
import org.advancelab.citizensdata.model.Citizen;
import org.advancelab.citizensdata.model.License;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CitizenService {
	
	private SessionFactory sessionFactory = new Hibernatedb().getSessionFactory();
	
	public List<Citizen> getCitizens() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Citizen");
		List<Citizen> citizen = query.list();
		if(citizen.size() == 0) {
			throw new EmptyRecordException("Empty record. Please fill in some data before use.");
		}
		session.getTransaction().commit();
		session.close(); 
		return citizen;
	}
	
	public List<Citizen> getCitizens(int start, int end) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Citizen");
		List<Citizen> citizen = query.list();
		if(citizen.size() == 0) {
			throw new EmptyRecordException("Empty record. Please fill in some data before use.");
		} else if(citizen.size() < start ) {
			throw new InvalidQueryArgumentException("Note: The value of 'start' is greater than size of our results.");
		} else if(citizen.size() < end ){
			throw new InvalidQueryArgumentException("Note: The value of 'end' is greater than size of our results.");
		}
		session.getTransaction().commit();
		session.close();
		return citizen.subList((start-1), end);
	}
	
	public List<Citizen> getCitizens(int birthYear) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Citizen where year =" + birthYear);
		List<Citizen> citizen = query.list();
		if(citizen.size() == 0) {
			throw new RecordNotFoundException("No matching record with this date of bith.");
		}
		session.getTransaction().commit();
		session.close();
		return citizen;
	}
	
	public Citizen getCitizen(String cardId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select cardId from Citizen");
		List<String> Ids = query.getResultList();
		if(!(Ids.contains(cardId))){
			throw new RecordNotFoundException("No record in the database with the cardId: "+ cardId);
		}
		Citizen citizen = (Citizen) session.get(Citizen.class, cardId);
		session.getTransaction().commit();
		session.close();
		return citizen;
	}
	
	public void addCitizen(Citizen citizen) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String inputKey = citizen.getCardId();
		Query query = session.createQuery("select cardId from Citizen");
		List<String> Ids = query.getResultList();
		if((Ids.contains(inputKey))){
			throw new DuplicationEntryException("Use a different id. This cardId already exists in the record: "+ inputKey);
		}
		session.persist(citizen);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateCitizen(String cardId, Citizen citizen) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String inputkey = citizen.getCardId();
		Query query = session.createQuery("select cardId from Citizen");
		List<String> Ids = query.getResultList();
		if(!(cardId.equalsIgnoreCase(inputkey))){
			throw new RecordNotFoundException("The id of this entry doesn't match with the targeted update entry. Please make sure the ids are identical. ");
		}
		session.update(cardId, citizen);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteCitizen(String cardId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select cardId from Citizen"); 
		List<String> listofIds = query.getResultList();
		if(!(listofIds.contains(cardId))) {
			throw new RecordNotFoundException("No card with this id: "  + cardId + " in the records.");
		}
		Citizen citizen = (Citizen) session.get(Citizen.class, cardId);
		session.delete(citizen);
		session.getTransaction().commit();
		session.close();
	}
	
}
