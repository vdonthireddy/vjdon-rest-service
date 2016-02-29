package com.vijay.predix;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.vijay.predix.entity.Employee;

@Path("/")
public class DefaultService {

	@GET
	@Path("add/")
//	@Produces("application/json")
	public String add()
	{
		return "Hello Vijay Donthireddy! Predix is awesome!!!";
	}
	
	@GET
	@Path("employee/")
//	@Produces("applicaton/json")
	public Response getName()
	{
		Employee emp = new Employee();
		emp.setFirstName("Vijay");
		emp.setLastName("Donthireddy");
		return handleResult(emp);//.getFullName();
	}
	@GET
    @Path("customers/")
    public Response getCustomer(@QueryParam("id") String id)
    {
    	return handleResult("Customer Id is: " + id);
    }
        
    //Working URL: http://vjdon-rest-service.run.aws-usw02-pr.ice.predix.io/services/greeting?name=Predix
    @GET
    @Path("greeting/")
    public Response greeting(@QueryParam("name") String name) {
    	if (name == null)
    		name = "Vijay";
        return handleResult("Hello " + name);
    }
    
//	@GET
//	@Path("employee/")
//	@Produces("applicaton/json")
//	public Employee getName()
//	{
//		Employee emp = new Employee();
//		emp.setFirstName("Vijay");
//		emp.setLastName("Donthireddy");
//		return emp;
//	}
	
	protected Response handleResult(Object entity)
    {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        responseBuilder.type(MediaType.APPLICATION_JSON);
        responseBuilder.entity(entity);
        return responseBuilder.build();
    }
}
