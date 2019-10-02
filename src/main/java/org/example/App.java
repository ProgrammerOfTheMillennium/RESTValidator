package org.example;


//import org.apache.log4j.LoggerFactory;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import org.example.REST.RESTService;


/**
 * Hello REST API!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello REST API!" );
//        final Logger rootLogger = LogManager.getRootLogger();
//        private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

        RESTService rest = new RESTService();

        rest.shouldReturnStatusOkay();
    }
}
