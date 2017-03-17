package org.enset.sid.metier;


import java.util.Date;
import java.util.List;

import org.enset.sid.dao.IBanqueDao;
import org.enset.sid.entities.Client;
import org.enset.sid.entities.Compte;
import org.enset.sid.entities.Employe;
import org.enset.sid.entities.Groupe;
import org.enset.sid.entities.Operation;
import org.enset.sid.entities.Retrait;
import org.enset.sid.entities.Versement;
import org.springframework.transaction.annotation.Transactional;



@Transactional //Spring tx qui gère cette annotation : pour ouvrir une transaction (commit et rollback)
public class BanqueMetierImpl implements IBanqueMetier {

	private IBanqueDao dao;
	
	
	//Ajouter seulement le setDao
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		// TODO Auto-generated method stub
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		// TODO Auto-generated method stub
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmploye, Long codeGr) {
		// TODO Auto-generated method stub
		dao.addEmployeToGroupe(codeEmploye, codeGr);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.addCompte(cp, codeCli, codeEmp);
	}

	@Override
	public void verser(double mt, String cpte, Long codeEmp) {
		// TODO Auto-generated method stub
		dao.addOperation(new Versement(new Date(), mt), cpte, codeEmp);
		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde() + mt);
	}

	@Override
	public void retirer(double mt, String cpte, Long codeEmp) {
		// TODO Auto-generated method stub
		dao.addOperation(new Retrait(new Date(), mt), cpte, codeEmp);
		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde() - mt); 
	}

	@Override
	public void virement(double mt, String cpte1, String cpte2, Long codeEmp) {
		// TODO Auto-generated method stub
		retirer(mt, cpte1, codeEmp);
		verser(mt, cpte2, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperations(String codeCpte, int position, int nbOperation) {
		// TODO Auto-generated method stub
		return dao.consulterOperations(codeCpte, position, nbOperation);
	}

	@Override
	public Client consulterClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		// TODO Auto-generated method stub
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.getComptesByClient(codeCli);
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.getComptesByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmployes() {
		// TODO Auto-generated method stub
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupe() {
		// TODO Auto-generated method stub
		return dao.getGroupe();
	}

	@Override
	public List<Employe> getEmployeByGroupe(Long codeGr) {
		// TODO Auto-generated method stub
		return dao.getEmployeByGroupe(codeGr);
	}

	@Override
	public long getNombreOperation(String numCpte) {
		// TODO Auto-generated method stub
		return dao.getNombreOperation(numCpte);
	}
}
