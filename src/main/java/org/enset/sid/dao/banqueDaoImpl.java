package org.enset.sid.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.enset.sid.entities.Client;
import org.enset.sid.entities.Compte;
import org.enset.sid.entities.Employe;
import org.enset.sid.entities.Groupe;
import org.enset.sid.entities.Operation;



public class banqueDaoImpl implements IBanqueDao {

	
	@PersistenceContext
	private EntityManager em; //Pour utiliser le JPA, il faut appeller EntityManager
	
	
	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		// TODO Auto-generated method stub
		if(codeSup != null){
			Employe sup = em.find(Employe.class, codeSup);
			e.setEmployeSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		// TODO Auto-generated method stub
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmploye, Long codeGr) {
		// TODO Auto-generated method stub
		Employe e = em.find(Employe.class, codeEmploye);
		Groupe g = em.find(Groupe.class, codeGr);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
		
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		// TODO Auto-generated method stub
		Client cli = em.find(Client.class, codeCli);
		Employe emp = em.find(Employe.class, codeEmp);
		cp.setClient(cli);
		cp.setEmploye(emp);
		em.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp) {
		// TODO Auto-generated method stub
		Compte cp = consulterCompte(codeCpte);
		Employe emp = em.find(Employe.class, codeEmp);
		op.setCompte(cp);
		op.setEmploye(emp);
		em.persist(op);
		return op;
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		Compte cp = em.find(Compte.class, codeCpte);
		
		if(cp == null){
			throw new RuntimeException("Compte " + codeCpte + " introuvable !!!");
		}
		return cp;
	}

	@Override
	public List<Operation> consulterOperations(String codeCpte, int position, int nbOperation) {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc");
		req.setParameter("x", codeCpte);
		req.setFirstResult(position);
		req.setMaxResults(nbOperation);
		return req.getResultList();
	}

	@Override
	public Client consulterClient(Long codeCli) {
		// TODO Auto-generated method stub
		Client c = em.find(Client.class, codeCli);
		if(c==null){
			throw new RuntimeException("Client introuvable !!!");
		}
		return c;
	}

	@Override
	public List<Client> consulterClients(String mc) {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select c from Client c where c.nomClient like :x");
		req.setParameter("x", "%" + mc + "%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select c from Compte c where c.client.codeClient=:x");
		req.setParameter("x", codeCli);
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select c from Compte c where c.employe.codeEmploye=:x");
		req.setParameter("x", codeEmp);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select e from Employe e");
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupe() {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select g from Groupe g");
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployeByGroupe(Long codeGr) {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select e from Employe e where e.groupe.codeGroupe=:x");
		req.setParameter("x", codeGr);
		return req.getResultList();
	}

	@Override
	public long getNombreOperation(String numCpte) {
		// TODO Auto-generated method stub
		Query req = em.createQuery("select count(o) from Operation o where o.compte.codeCompte=:x");
		req.setParameter("x", numCpte);
		return (Long) req.getResultList().get(0);
	}

}
