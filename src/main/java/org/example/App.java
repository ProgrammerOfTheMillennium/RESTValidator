package org.example;

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
        RESTService rest = new RESTService();

        rest.shouldReturnFullJSONObject();
    }
}
