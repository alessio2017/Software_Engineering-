/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restws;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class RestfulServer {
    
    public static void main(String[] args) throws Exception{
        // The first step is to instantiate a JAXRSServerFactoryBean object and
        // set the root resource class:
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(CourseRepository.class);
        // A resource provider then needs to be set on the factory bean to manage the life cycle
        // of the root resource class. We use the default singleton resource provider that 
        // returns the same resource instance to every request because I've to be sure that 
        // there is only one instance of CourseRepository otherwise the threads don't share the same data
        factoryBean.setResourceProvider(new SingletonResourceProvider(new CourseRepository()));
        // We also set an address to indicate the URL where the web service is published
        factoryBean.setAddress("http://localhost:8080/");
        // Now the factoryBean can be used to create a new server that will start 
        // listening for incoming connections
        Server server = factoryBean.create();        
        
    }
    
}
