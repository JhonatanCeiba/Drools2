package com.sample;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package; 
 
public class DroolsTest {
 
    public static final void main(String[] args) {
        try {
            //Cargamos la base de reglas
            RuleBase ruleBase = leerReglas();
            WorkingMemory workingMemory = ruleBase.newStatefulSession();
 
            //Obtenemos los empleados
            Collection<Empleado> empleados = buscarEmpleados();
 
            for (Empleado empleado : empleados) {
                workingMemory.insert(empleado);
            }
 
            //Disparamos las reglas de Drools
            workingMemory.fireAllRules();
 
            for (Empleado empleado : empleados) {
                System.out.println("Empleado: " + empleado);
            }
 
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
 
    private static Collection<Empleado> buscarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
 
        //Creamos algunos empleados para el ejemplo
        Empleado empleado1 = new Empleado("Juan", 9);
        Empleado empleado2 = new Empleado("Jose", 6);
        Empleado empleado3 = new Empleado("Pedro", 2);
 
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);
 
        return empleados;
    }
 
    private static RuleBase leerReglas() throws Exception {
        //Leemos el archivo de reglas (DRL)
        Reader source = new InputStreamReader(
        		DroolsTest.class.getResourceAsStream("Sample.drl"));
 
        //Construimos un paquete de reglas
        PackageBuilder builder = new PackageBuilder();
 
        //Parseamos y compilamos las reglas en un único paso
        builder.addPackageFromDrl(source);
 
        // Verificamos el builder para ver si hubo errores
        if (builder.hasErrors()) {
            System.out.println(builder.getErrors().toString());
            throw new RuntimeException(
                "No se pudo compilar el archivo de reglas.");
        }
 
        //Obtenemos el package de reglas compilado
        Package pkg = builder.getPackage();
 
        //Agregamos el paquete a la base de reglas 
        //(desplegamos el paquete de reglas).
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(pkg);
        return ruleBase;
    }
}
 