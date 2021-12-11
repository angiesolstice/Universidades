package com.ibm.academia.apirest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Direccion;
import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PabellonDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;


@Component
public class Comandos implements CommandLineRunner 
{
	@Autowired
	private CarreraDAO carreraDao;

	
	
	@Autowired
	private AlumnoDAO alumnoDao;
	
	@Autowired
	private ProfesorDAO profesorDao;
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Autowired
	private AulaDAO aulaDao;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	public void run(String... args) throws Exception 
	{
		
		
		/*INSERTAR PABELLON A UNA AULA
		 * 
		Optional<Pabellon> auxiliarPabellon = pabellonDao.buscarPorId(1);
		Iterable<Aula> aulas = aulaDao.buscarTodos();
		aulas.forEach(aula->aula.setPabellon(auxiliarPabellon.get()));
		aulas.forEach(aula->aulaDao.guardar(aula));*/
		
	
		/*  Buscar un empleado por tipo de empleado*/
	    /*TipoEmpleado tipoEmpleado = TipoEmpleado.ADMINISTRATIVO;
		Iterable<Persona> empleados = empleadoDao.findEmpleadosByTipoEmpleado(tipoEmpleado);
		System.out.println(empleados);*/
		
	
		/*BUSQUEDAS DE AULA*/
	    /*Pizarron tipoAula = Pizarron.PIZARRA_TIZA;
		List<Aula> aulasPizarron = (List<Aula>)aulaDao.buscarAulaPorTipoPizarron(tipoAula);
		aulasPizarron.forEach(System.out::println);*/
		
		/*List<Aula> aulasPizarron = (List<Aula>)aulaDao.buscarAulaPorNombrePabellon("Laboratorio de Computacion");
		aulasPizarron.forEach(System.out::println);*/
		
	    /*Optional<Aula> aulasPizarron = aulaDao.buscarAulaPorNumero(1);
		System.out.println(aulasPizarron.get().toString());*/
		
		
		/*Buscar pabellon por localidad
		 * List<Pabellon> pabellon1 = (List<Pabellon>)pabellonDao.buscarPabellonPorLocalidad("vallejo");
		pabellon1.forEach(System.out::println);*/
		
		/*Buscar pabellon por nombre
		 * List<Pabellon> pabellon1 = (List<Pabellon>)pabellonDao.buscarPabellonPorNombre("Laboratorio de Computacion");
		pabellon1.forEach(System.out::println);*/
		
	
		/*INSERTAR UN NUEVO ALUMNO
		 * Direccion direccion= new Direccion("Zaragoza", "35","56520","1305", "10", "Miguel Hidalgo");
		Persona alumno= new Alumno(null,"Juan","Cardenas", "987654329", direccion);
		Persona auxiliarPersona = alumnoDao.guardar(alumno);
		System.out.println(auxiliarPersona.toString());*/
		
		
		/*BUSCAR CARRERAS POR NOMBRE Y APELLIDO DEL PROFESOR
		 * 
		List<Carrera> carreras = (List<Carrera>)carreraDao.buscarCarrerasPorProfesorNombreYApellido("Carlos", "Valle");
		carreras.forEach(System.out::println);*/
		
	
		/*
		Set<Carrera> carreras = new HashSet<Carrera>();
		Carrera licRecursos = carreraDao.buscarPorId(8).get();
        Carrera licTurismo = carreraDao.buscarPorId(6).get();
        carreras.add(licRecursos);
        carreras.add(licTurismo);
		Optional<Persona> profesorPersona = profesorDao.buscarPorId(6);
	    Profesor profesor=(Profesor)profesorPersona.get();
		profesor.setCarreras(carreras);
		Persona auxiliarProfesor=profesorDao.guardar((Persona)profesor);
		System.out.println(auxiliarProfesor);*/
		
		/*INSERTAR UN NUEVO PROFESOR
		 * Direccion direccion= new Direccion("Avenida Central", "54","55430","2", "1", "Cuauhtemoc");
		BigDecimal salario = new BigDecimal(25000);
		Persona profesor=new Profesor(null,"Eugenio","Cardenas", "334521214", direccion, salario);
		Persona auxiliarProfesor = profesorDao.guardar(profesor);
		System.out.println(auxiliarProfesor.toString());
		
		Direccion direccion= new Direccion("Avenida Tlahuac", "123","55411","4", "1", "Tlahuac");
		BigDecimal salario = new BigDecimal(27000);
		Persona profesor=new Profesor(null,"Carlos","Valle", "984561219", direccion, salario);
		Persona auxiliarProfesor = profesorDao.guardar(profesor);
		System.out.println(auxiliarProfesor.toString());
		*
		*/
		
		
		
		
		/*INSERTAR UN NUEVO EMPLEADO
		 * 
		Direccion direccion= new Direccion("Periferico del Oreinte", "45","55441","6", "1", "Tlahuac");
		BigDecimal salario = new BigDecimal(28000);
		TipoEmpleado tipoEmpleado = TipoEmpleado.ADMINISTRATIVO;
		Persona empleado=new Empleado(null, "Silvia", "Gomez", "123456981", direccion, salario,tipoEmpleado);
		Persona auxiliarEmpleado = empleadoDao.guardar(empleado);
		System.out.println(auxiliarEmpleado.toString());*/
		
		
	    /*Direccion direccion= new Direccion("Calzada Ermita", "120","55431","12", "1", "Iztapalapa");
		BigDecimal salario = new BigDecimal(15000);
		TipoEmpleado tipoEmpleado = TipoEmpleado.MANTENIMIENTO;
		Persona empleado=new Empleado(null, "Brian", "Ascencio", "123446782", direccion, salario,tipoEmpleado);
		Persona auxiliarEmpleado = empleadoDao.guardar(empleado);
		System.out.println(auxiliarEmpleado.toString());*/
		
		
		/*
		 * INSERTAR UN PABELLON
		Direccion direccion= new Direccion("Avenida Adolfo Mateos", "10","55430","4", "1", "Vallejo");
		Pabellon pabellon = new  Pabellon(null, 20.00,"Laboratorio de Computacion", direccion);
		Pabellon auxiliarPabellon= pabellonDao.guardar(pabellon);
		System.out.println(auxiliarPabellon.toString());*/
		
		/*INSERTAR UNA AULA*/
		/*Pizarron tipoPizarron= Pizarron.PIZARRA_BLANCA;
		Aula aula= new Aula(null, 1, "5m x 7m", 35,tipoPizarron);
		Aula auxiliarAula = aulaDao.guardar(aula);
		System.out.println(auxiliarAula.toString());*/
		
		/*Pizarron tipoPizarron= Pizarron.PIZARRA_TIZA;
		Aula aula= new Aula(null, 3, "6m x 8m", 36,tipoPizarron);
		Aula auxiliarAula = aulaDao.guardar(aula);
		System.out.println(auxiliarAula.toString());
		*/
		
		/*Carrera ingElectronica = new Carrera(null, "Ingenieria Electronica", 45, 5);
		Carrera licSistemas = new Carrera(null, "Licenciatura en Sistemas", 40, 4);
		BigDecimal sueldo= new BigDecimal(18000.00);
		Direccion direccion= new Direccion("Zaragoza", "35","56520","City royal", "2", "Miguel Hidalgo");
		Persona profesor1=new Profesor(1, "Eugenio", "Cardenas", "EUCM1711950HDF", direccion, sueldo);*/
		/*Set<Carrera> carrerasProfesor= new HashSet<>();
		carrerasProfesor.add(licSistemas);
		carrerasProfesor.add(ingElectronica);
		profesor1.setCarreras(carrerasProfesor);*/
		/*Persona auxiliarPersona =profesorDao.guardar(profesor1);
		System.out.println(auxiliarPersona.toString());*/
		/*Carrera finanzas = new Carrera(null, "Ingenieria en finanzas", 20, 3);
		Carrera carreraGuardada = carreraDao.guardar(finanzas);
		System.out.println(carreraGuardada.toString());*/
		
		/*Carrera carrera = null;
		
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(2);
		if(oCarrera.isPresent())
		{
			carrera = oCarrera.get();
			System.out.println(carrera.toString());
		}
		else
		{
			System.out.println("Carrera no encontrada");
		}
		
		carrera.setCantidadAnios(7);
		carrera.setCantidadMaterias(66);
		carreraDao.guardar(carrera);
		
		System.out.println(carreraDao.buscarPorId(2).orElse(new Carrera()).toString());
		
		carreraDao.eliminarPorId(2);
		System.out.println(carreraDao.buscarPorId(2).orElse(new Carrera()).toString());*/
		
		/*Carrera ingSistemas = new Carrera(null, "Ingenieria en Sistemas", 60, 5);
        Carrera ingIndustrial = new Carrera(null, "Ingenieria Industrial", 55, 5);
        Carrera ingAlimentos = new Carrera(null, "Ingenieria en Alimentos", 53, 5);
        Carrera ingElectronica = new Carrera(null, "Ingenieria Electronica", 45, 5);
        Carrera licSistemas = new Carrera(null, "Licenciatura en Sistemas", 40, 4);
        Carrera licTurismo = new Carrera(null, "Licenciatura en Turismo", 42, 4);
        Carrera licYoga = new Carrera(null, "Licenciatura en Yoga", 25, 3);
        Carrera licRecursos = new Carrera(null, "Licenciatura en Recursos Humanos - RRHH", 33, 3);

        carreraDao.guardar(ingSistemas);
        carreraDao.guardar(ingIndustrial);
        carreraDao.guardar(ingAlimentos);
        carreraDao.guardar(ingElectronica);
        carreraDao.guardar(licSistemas);
        carreraDao.guardar(licTurismo);
        carreraDao.guardar(licYoga);
        carreraDao.guardar(licRecursos);*/
		
		/*List<Carrera> carreras = (List<Carrera>)carreraDao.findCarrerasByNombreContains("Sistemas");
        carreras.forEach(System.out::println);*/
        
        /*List<Carrera> carrerasIgnoreCase1 = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");
        List<Carrera> carrerasIgnoreCase2 = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase("sistemas");
        carrerasIgnoreCase1.forEach(System.out::println);
        carrerasIgnoreCase2.forEach(System.out::println);*/
        
        /*List<Carrera> carrerasPorAnio = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(3);
        carrerasPorAnio.forEach(System.out::println);*/
		
		/*Optional<Persona> persona = personaDao.buscarPorId(1);
		System.out.println(persona.toString());
		*/
	}
}