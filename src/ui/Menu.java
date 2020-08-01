package ui;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in);
		Persona p=login();
		System.out.println("----------------- Bienvenido "+p.getNombre()+" "+p.getApellido()+" -------------------");
		System.out.println();
		
		String command;
		do {
			System.out.println("------------------------------------------------------------------------------");
			command=getCommand();
			executeCommand(command);
			System.out.println();
		} while(!command.equalsIgnoreCase("exit"));
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
			ArrayList<Persona> personas = search();
			if (!personas.isEmpty()) {
				for(Persona p : personas)
				System.out.println(p);
			} else {
				System.out.println("No hay personas con ese apellido");
			}
			break;
		case "new":
			Persona p = new Persona();
			inicializePersona(p);
			assignRol(p);
			System.out.println("Person correctly added");
			break;
		case "edit":
			editPerson();
			break;
		case "delete":
			deletePerson();
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); 
		System.out.println("search\t\tlistar por apellido"); 
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.print("\nComando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;
	}
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private ArrayList<Persona> search() {
		System.out.println();
		Persona p = new Persona();
		System.out.print("apellido: ");
		p.setApellido(s.nextLine());
		return ctrlLogin.getByApellido(p);
	}
	
	private void inicializePersona(Persona p) {
		Documento d = new Documento();
		System.out.print("tipo documento: ");
		String tipo = s.nextLine();
		while(!tipo.equalsIgnoreCase("dni") && !tipo.equalsIgnoreCase("cuit")) {
			System.out.print("tipo documento: ");
			tipo = s.nextLine();
		}
		d.setTipo(tipo);
		System.out.print("nro documento: ");
		d.setNro(s.nextLine());
		p.setDocumento(d);
		System.out.print("nombre: ");
		p.setNombre(s.nextLine());
		System.out.print("apellido: ");
		p.setApellido(s.nextLine());
		System.out.print("email: ");
		p.setEmail(s.nextLine());
		System.out.print("contraseña: ");		
		p.setPassword(s.nextLine());
		System.out.print("telefono: ");		
		p.setTel(s.nextLine());
		System.out.print("habilitado: ");				
		if(s.nextLine().equalsIgnoreCase("si")) {
			p.setHabilitado(true);
		} else {
			p.setHabilitado(false);
		}
		ctrlLogin.add(p);
	}

	private void assignRol(Persona p) {
		System.out.println("\n--------------Roles disponibles: ");
		LinkedList<Rol> roles = ctrlLogin.getRoles();
		for (Rol rol : roles) {
			System.out.println(rol);
		}
		Rol selectedRol = new Rol();
		
		System.out.print("rol: ");
		selectedRol.setDescripcion(s.nextLine());
		ctrlLogin.assignRol(p, selectedRol);
	}
	
	private void editPerson(){
		Documento d = new Documento();
		Persona p = null;
		do {
			p = new Persona();
			System.out.print("tipo documento: ");
			String tipo = s.nextLine();
			while(!tipo.equalsIgnoreCase("dni") && !tipo.equalsIgnoreCase("cuit")) {
				System.out.print("tipo documento: ");
				tipo = s.nextLine();
			}
			d.setTipo(tipo);
			System.out.print("nro documento: ");
			d.setNro(s.nextLine());
			p.setDocumento(d);
			
			p = ctrlLogin.getByDocumento(p);
			if(p == null) System.out.println("Datos ingresados incorrectos\n----------------");
		} while (p == null);
		System.out.println("\nPersona seleccionada: ");
		System.out.println(p);
		System.out.println("\n--------Nuevos datos:");
		
		System.out.print("nombre: ");
		p.setNombre(s.nextLine());
		System.out.print("apellido: ");
		p.setApellido(s.nextLine());
		System.out.print("email: ");
		p.setEmail(s.nextLine());
		System.out.print("contraseña: ");		
		p.setPassword(s.nextLine());
		System.out.print("telefono: ");		
		p.setTel(s.nextLine());
		System.out.print("habilitado: ");				
		if(s.nextLine().equalsIgnoreCase("si")) {
			p.setHabilitado(true);
		} else {
			p.setHabilitado(false);
		}
		ctrlLogin.updatePerson(p);
	}	
	private void deletePerson() {
		
		Documento d = new Documento();
		Persona p = null;
		do {
			p = new Persona();
			System.out.print("tipo documento: ");
			String tipo = s.nextLine();
			while(!tipo.equalsIgnoreCase("dni") && !tipo.equalsIgnoreCase("cuit")) {
				System.out.print("tipo documento: ");
				tipo = s.nextLine();
			}
			d.setTipo(tipo);
			System.out.print("nro documento: ");
			d.setNro(s.nextLine());
			p.setDocumento(d);
			
			p = ctrlLogin.getByDocumento(p);
			if (p != null) {
				System.out.print("\nPersona seleccionada: ");
				System.out.println(p);
			} else {
				System.out.println("Datos ingresados incorrectos");
			}
		} while (p == null);
		
		System.out.print("\nEstas seguro? (si/no): ");
		if (s.nextLine().equalsIgnoreCase("si")) {
			ctrlLogin.deletePerson(p);
		} else {
			System.out.println("\n 	OK. No se eliminó nada.");
		}
	}
}
