package org.advancelab.citizensdata.service;

import java.util.List;

import org.advancelab.citizensdata.database.Hibernatedb;
import org.advancelab.citizensdata.exception.DuplicationEntryException;
import org.advancelab.citizensdata.exception.EmptyRecordException;
import org.advancelab.citizensdata.exception.InvalidQueryArgumentException;
import org.advancelab.citizensdata.exception.RecordNotFoundException;
import org.advancelab.citizensdata.model.License;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LicenseService {

private SessionFactory sessionFactory = new Hibernatedb().getSessionFactory();
	
	public List<License> getLicenses() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from License");
		List<License> license = query.list();
		if(license.size() == 0) {
			throw new EmptyRecordException("Empty record. Please fill in some data before use.");
		}
		session.getTransaction().commit();
		session.close(); 
		return license;
	}
	
	public List<License> getLicenses(int start, int end) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from License");
		List<License> license = query.list();
		if(license.size() == 0) {
			throw new EmptyRecordException("Empty record. Please fill in some data before use.");
		} else if(license.size() < start ) {
			throw new InvalidQueryArgumentException("Note: The value of 'start' is greater than size of our results.");
		} else if(license.size() < end ){
			throw new InvalidQueryArgumentException("Note: The value of 'end' is greater than size of our results.");
		}
		session.getTransaction().commit();
		session.close();
		return license.subList((start-1), end);
	}
	
	public List<License> getLicenses(String catagory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from License where catagory =" + catagory);
		List<License> license = query.list();
		if(license.size() == 0) {
			throw new RecordNotFoundException("No matching record with this catagory.");
		}
		session.getTransaction().commit();
		session.close();
		return license;
	}
	
	public License getLicense(String licenseId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select licenseId from License");
		List<String> Ids = query.getResultList();
		if(!(Ids.contains(licenseId))){
			throw new RecordNotFoundException("No record in the database with the licenseId: "+ licenseId);
		}
		License license = (License) session.get(License.class, licenseId);
		session.getTransaction().commit();
		session.close();
		return license;
	}
	
	public void addLicense(License license) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String inputKey = license.getLicenseId();
		Query query = session.createQuery("select licenseId from License");
		List<String> Ids = query.getResultList();
		if((Ids.contains(inputKey))){
			throw new DuplicationEntryException("Use a different id. This licenseId already exists in the record: "+ inputKey);
		}
		session.persist(license);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateLicense(String licenseId, License license) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String inputkey = license.getLicenseId();
		Query query = session.createQuery("select licenseId from License");
		List<String> Ids = query.getResultList();
		if(!(licenseId.equalsIgnoreCase(inputkey))){
			throw new RecordNotFoundException("The id of this entry doesn't match with the targeted update entry. Please make sure the ids are identical. ");
		}
		session.update(licenseId, license);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteLicense(String licenseId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select licenseId from License"); 
		List<String> listofIds = query.getResultList();
		if(!(listofIds.contains(licenseId))) {
			throw new RecordNotFoundException("No card with this id: "  + licenseId + " in the records.");
		}
		License license = (License) session.get(License.class, licenseId);
		session.delete(license);
		session.getTransaction().commit();
		session.close();
	}

}
