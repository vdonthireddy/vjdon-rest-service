package com.vijay.predix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
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
	public String add(
			@QueryParam("i1") int i1,
			@QueryParam("i2") int i2)
	{
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime() + "; i1=" + Integer.toString(i1) + ", i2=" + Integer.toString(i2) + ", SUM=" + Integer.toString(i1+i2);
	}
	
	@GET
	@Path("employee/")
//	@Produces("applicaton/json")
	public Response getName(@QueryParam("id") int id)
	{
		List<Employee> employees = new Employee().GetAll();
		List<Employee> newList = new ArrayList<Employee>();
		if (id != 0){			
			for(Employee emp:employees) {
				if (emp.getId()==id)
				newList.add(emp);
			}
		}
		else {
			newList = employees;
		}
		
//		List<Employee> newList = (id==0) ? employees : employees.stream().filter(emp->emp.getId()==id).collect(Collectors.toList());
		
		GenericEntity entity = new GenericEntity<List<Employee>>(newList) {};
		
		return handleResult(entity);
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
	
	@GET
	@Path("createtable/")
	public Response createTable() {
		String message = "";
		try {
            Connection c=null;
            Statement stmt=null;
            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection("jdbc:postgresql://10.72.6.124:5432/d598b6c81f92a45758435134d0073ec85", "u598b6c81f92a45758435134d0073ec85", "3847ca0b3c7f489280857d1d13acd903");
            message += "\nOpened database successfully";

            stmt = c.createStatement();
            String sql = "DROP TABLE Employee;" + 
            		" CREATE TABLE Employee " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           varchar(100)    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        varchar(100), " +
                    " SALARY         INT)";
            stmt.executeUpdate(sql);    
            stmt.close();
            c.close();
            message += "\nTable Employee Created";

        } catch (Exception ee) {
        	message = ee.getClass().getName()+": "+ ee.getMessage();
        }
		
		return handleResult(message);
	}
	
	@GET
	@Path("addemployee")
	public Response addEmployee(@QueryParam("id") int id,
			@QueryParam("name") String name,
			@QueryParam("age") int age,
			@QueryParam("address") String address,
			@QueryParam("salary") int salary) 
	{
		String message = "";
		try {
            Connection c=null;
            Statement stmt=null;
            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection("jdbc:postgresql://10.72.6.124:5432/d598b6c81f92a45758435134d0073ec85", "u598b6c81f92a45758435134d0073ec85", "3847ca0b3c7f489280857d1d13acd903");
            message += "\nOpened database successfully";

            stmt = c.createStatement();
            String sql = "INSERT INTO Employee(ID, NAME, AGE, ADDRESS, SALARY)" + 
            		" VALUES(" + id + ", '" + name + "', " + age +  ", '" + address + "', " + salary +");";
            
            message += "\n" + sql;
            
            stmt.executeUpdate(sql);    
            stmt.close();
            c.close();
            message += "\nRow Inserted into Employee table";

        } catch (Exception ee) {
        	message = message + "\n" + ee.getClass().getName()+": "+ ee.getMessage();
        }
		return handleResult(message);
	}
	
	@GET
	@Path("getemployees")
	public Response getEmployees() 
	{
		String message = "";
		try {
            Connection c=null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection("jdbc:postgresql://10.72.6.124:5432/d598b6c81f92a45758435134d0073ec85", "u598b6c81f92a45758435134d0073ec85", "3847ca0b3c7f489280857d1d13acd903");
            message += "\nOpened database successfully";

            pst = c.prepareStatement("SELECT * FROM EMPLOYEE;");
            rs = pst.executeQuery();
            message += "\n\nId\t\tName\t\tAge\t\tAddress\t\tSalary";
            while (rs.next()) {
            	message += "\n" + rs.getInt("ID");
            	message += "\t\t" + rs.getString("NAME");
            	message += "\t\t" + rs.getInt("AGE");
            	message += "\t\t" + rs.getString("ADDRESS");
            	message += "\t\t" + rs.getLong("SALARY");
            }
            
			rs.close();
			pst.close();
            c.close();

        } catch (Exception ee) {
        	message = message + "\n\n" + ee.getClass().getName()+": "+ ee.getMessage();
        }
		return handleResult(message);
	}
	
	protected Response handleResult(Object entity)
    {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        responseBuilder.type(MediaType.APPLICATION_JSON);
        responseBuilder.entity(entity);
        return responseBuilder.build();
    }
}
