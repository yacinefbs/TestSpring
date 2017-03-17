package org.enset.sid.dao;


import java.util.List;

import org.enset.sid.entities.Client;
import org.enset.sid.entities.Compte;
import org.enset.sid.entities.Employe;
import org.enset.sid.entities.Groupe;
import org.enset.sid.entities.Operation;



public interface IBanqueDao {
	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long codeSup);
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroupe(Long codeEmploye, Long codeGr);
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp);
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp);
//	public void versement(String codeCpte, double mt, Long codeEmp);
//	public void retrait(String codeCpte, double mt, Long codeEmp);
//	public void virement(String codeCpte1, String codeCpte2, double mt, Long codeEmp);
	
	public Compte consulterCompte(String codeCpte);
	public List<Operation> consulterOperations(String codeCpte, int position, int nbOperation);
	public Client consulterClient(Long codeCli);
	public List<Client> consulterClients(String mc);
	public List<Compte> getComptesByClient(Long codeCli);
	public List<Compte> getComptesByEmploye(Long codeEmp);
	public List<Employe> getEmployes();
	public List<Groupe> getGroupe();
	public List<Employe> getEmployeByGroupe(Long codeGr);
	public long getNombreOperation(String numCpte);
	
}
