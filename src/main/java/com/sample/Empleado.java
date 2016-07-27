package com.sample;

import java.math.BigDecimal;

public class Empleado {
 
    private String nombre;
    private Integer promedioConocimientos;
    private String cargo;
    private BigDecimal salario;
 
    public Empleado(String nombre, Integer promedioConocimientos) {
        this.setNombre(nombre);
        this.setPromedioConocimientos(promedioConocimientos);
    }
 
    @Override
    public String toString() {
        return getNombre() + " promedio=" + getPromedioConocimientos() + 
               " cargo=" + getCargo() + " salario=" + salario;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPromedioConocimientos() {
		return promedioConocimientos;
	}

	public void setPromedioConocimientos(Integer promedioConocimientos) {
		this.promedioConocimientos = promedioConocimientos;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
 
//getters y setters...
 
}
 