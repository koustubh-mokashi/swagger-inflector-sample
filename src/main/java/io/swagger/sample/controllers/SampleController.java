package io.swagger.sample.controllers;

import javax.ws.rs.core.Response.Status;

import io.swagger.inflector.models.RequestContext;
import io.swagger.inflector.models.ResponseContext;
import io.swagger.sample.models.Person;

/**
 * Implementation of controller to support /v1/people API paths.
 * @author koustubh.m
 *
 */
public class SampleController {
	
	/**
	 * Method to support GET /v1/people?id={id}
	 * @param request
	 * @param id
	 * @return
	 */
	public ResponseContext getPersonById(RequestContext request, Integer id) {
		return new ResponseContext().status(Status.OK).entity("Person Id " + id);
	}

	/**
	 * Method to support POST /v1/people 
	 * e.g POST /v1/people -d '{"name":"abc"}'
	 * @param request
	 * @param person
	 * @return
	 */
	public ResponseContext addPerson(RequestContext request, Person person) {
		return new ResponseContext().status(Status.OK).entity(person);
	}
	
}
