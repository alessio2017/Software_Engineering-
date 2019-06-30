/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclient.soapesame;

import java.util.*;
import javax.jws.WebService;

/**
 *
 * @author biar
 */
@WebService(endpointInterface = "com.myclient.soapesame.ProfessorInterface")
public class ProfessorImpl implements ProfessorInterface {
    private Map<String, Professor> professor = new TreeMap<String, Professor>();
    public ProfessorImpl(){
        professor.put("1", new Professor("Massimo", "Mecella"));
        professor.put("2", new Professor("Riccardo", "Rosati"));
        professor.put("3", new Professor("Maurizio", "Lenzerini"));
        professor.put("4", new Professor("Tiziana", "Catarci"));
        professor.put("5", new Professor("Bruno", "Ciciani"));
    }
    @Override
    public Professor getDetails(String id) {
        return professor.get(id);
    }
    
}
