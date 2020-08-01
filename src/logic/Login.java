package logic;

import java.util.ArrayList;
import java.util.LinkedList;

import data.*;
import entities.*;

public class Login {
	private DataPersona dp;
	private DataRol dr;
	
	public Login() {
		dp=new DataPersona();
		dr=new DataRol();
	}
	
	public Persona validate(Persona p) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return dp.getByUser(p);
	}

	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}

	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento(per);	
	}
	
	public ArrayList<Persona> getByApellido(Persona per) {
		return dp.getByApellido(per);
	}

	public void add(Persona p) {
		dp.add(p);
	}
	public LinkedList<Rol> getRoles(){
		return dr.getAll();
	}
	public void assignRol(Persona p, Rol selectedRol) {
		Rol rol = dr.getByDesc(selectedRol);
		p.addRol(rol);
		dp.addRol(p, rol);
	}
	public void updatePerson(Persona p) {
		dp.update(p);
	}
	public void deletePerson(Persona p) {
		dr.deleteRoles(p); // primero borro los roles asignados
		dp.deletePerson(p); // borro la persona
	}
}
