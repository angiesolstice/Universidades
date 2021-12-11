package com.ibm.academia.apirest.data;

import java.math.BigDecimal;

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

public class DatosDummy 
{
	public static Carrera carrera01() 
	{
		return new Carrera(null, "Ingenieria en Sistemas", 50, 5); 
	}

	public static Carrera carrera02() 
	{
		return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
	}

	public static Carrera carrera03() 
	{
		return new Carrera(null, "Ingenieria Industrial", 60, 5);
	}
	
	public static Persona empleado01() 
	{
		return new Empleado(null, "Lautaro", "Lopez", "25174036", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.ADMINISTRATIVO);
	}

	public static Persona empleado02() 
	{
		return new Empleado(null, "Lenadro", "Lopez", "25174630", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
	}
	
	public static Empleado empleado03()
	{
		return new Empleado(null,"Jorge", "Martell", "123456788",new Direccion("Avenida Tlahuac", "5", "55010", "4", "1", "Tlahuac"),new BigDecimal(25000), TipoEmpleado.ADMINISTRATIVO);
	}
	public static Persona profesor01() 
	{
		return new Profesor(null, "Martin", "Lugone", "33908461", new Direccion(), new BigDecimal("60000.00"));
	}
	
	public static Persona profesor02()
	{
		return new  Profesor(null, "Susana", "Guzman", "123344556", new Direccion(), new BigDecimal(27000.00));
	}
	
	public static Persona profesor03()
	{
		return new  Profesor(null, "Victor", "Gonzalez", "125244556", new Direccion(), new BigDecimal(27000.00));
	}
	public static Persona alumno01() 
	{
		return new Alumno(null, "Jhon", "Benitez", "45233715", new Direccion());
	}

	public static Persona alumno02() 
	{
		return new Alumno(null, "Andres", "Benitez", "45233891", new Direccion());
	}

	public static Persona alumno03() 
	{
		return new Alumno(null, "Joaquin", "Leon", "45233012", new Direccion());
	}
	
	public static Pabellon pabellon01()
	{
		return new Pabellon(null, 30.25, "Sistemas I",  new Direccion("Avenida Universidad", "12", "55104", "1", "7", "Ajusco"));
	}
	public static Pabellon pabellon02()
	{
		return new Pabellon(null, 121.20, "Humanidades I", new Direccion("Avenida Insurgentes", "35", "55300", "2", "2", "Zacatenco"));
	}
	public static Pabellon pabellon03()
	{
		return new Pabellon(null, 72.5, "Economia I", new Direccion("Avenida Central", "35", "55300", "2", "1", "Zacatenco"));
	}
	public static Aula aula01()
	{
		return new Aula(null, 1, "7m x 6m", 40, Pizarron.PIZARRA_TIZA);
	}
	public static Aula aula02()
	{
		return new Aula(null, 2, "7m x 6.5m", 45, Pizarron.PIZARRA_TIZA);
	}
	public static Aula aula03()
	{
		return new Aula(null, 3, "7m x 6m", 40, Pizarron.PIZARRA_BLANCA);
	}
	
}