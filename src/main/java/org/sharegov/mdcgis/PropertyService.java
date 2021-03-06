/*******************************************************************************
 * Copyright 2014 Miami-Dade County
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.sharegov.mdcgis;

import static mjson.Json.make;
import static mjson.Json.read;
import groovy.json.JsonBuilder;

import com.newrelic.api.agent.Trace;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

import mjson.Json;

import org.restlet.Request;
import org.restlet.data.Form;
import org.sharegov.mdcgis.utils.AppContext;
import org.springframework.context.ApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PropertyService handles the requests from clients interested in GIS
 * information. This service is not trying to duplicate the existing GIS
 * Services. It will add functionality where the GIS group has not provided it
 * or is very specific to our needs.
 * 
 * @author fiallega
 * 
 */

@Path("mdcgis-0.1")
@Produces("application/json")
public class PropertyService {

	private static Logger _log = LoggerFactory.getLogger(PropertyService.class);




	@Trace(dispatcher = true)
	@GET
	@Path("/candidates")
	@Produces("application/json")
	public Json getCandidates() {

		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();
	
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getCandidates(queryParams.getValuesMap()); 

		return read(answer.toString());

	}

	@Trace(dispatcher = true)
	@GET
	@Path("/commonlocationcandidates")
	@Produces("application/json")
	public Json getCommonLocationCandidates() {

		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();
		
		Map params = queryParams.getValuesMap();
		params.put("layer", queryParams.getValuesArray("layer"));
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getCommonLocationCandidates(params); 

		return read(answer.toString());
		

	}

	@Trace(dispatcher = true)
	@GET
	@Path("/addressFromCoords")
	@Produces("application/json")
	public Json getAddressFromCoords() {

		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();
		
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getAddressFromCoords(queryParams.getValuesMap()); 

		return read(answer.toString());

	}

	@Trace(dispatcher = true)
	@GET
	@Path("/condoaddress")
	@Produces("application/json")
	public Json getCondoAddress() {

		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();

		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getCondoAddress(queryParams.getValuesMap()); 

		return read(answer.toString());
		
	}

	@Trace(dispatcher = true)
	@GET
	@Path("/address")
	@Produces("application/json")
	public Json getAddress() {

		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();
		
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getAddress(queryParams.getValuesMap()); 

		return read(answer.toString());

	}

	@Trace(dispatcher = true)
	@GET
	@Path("/commonlocations/{id}")
	public Json getCommonLocations(@PathParam("id") Long id) {
		
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getCommonLocations(id); 

		return read(answer.toString());
		
		
	}

	@Trace(dispatcher = true)
	@GET
	@Path("/servicelayers")
	@Produces("application/json")
	public Json getDataLayers() {

		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();

		Map params = queryParams.getValuesMap();
		params.put("layer", queryParams.getValuesArray("layer"));
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.getServiceLayers(params); 

		return read(answer.toString());
	}
		
	@Trace(dispatcher = true)
	@GET
	@Path("/publicworksintersectiondata")
	@Produces("application/json")
	public Json getPublicWorksIntersectionData() {
		
		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();

		ApplicationContext ctx = AppContext.getApplicationContext();
		PublicWorksController publicWorksController = (PublicWorksController) ctx.getBean("PUBLICWORKS_CONTROLLER");
		JsonBuilder answer = publicWorksController.getIntersectionData(queryParams.getValuesMap()); 

		return read(answer.toString());

	}
	
	@Trace(dispatcher = true)
	@GET
	@Path("/publicworkscorridordata")
	@Produces("application/json")
	public Json getPublicWorksCorridorData() {
		
		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();

		ApplicationContext ctx = AppContext.getApplicationContext();
		PublicWorksController publicWorksController = (PublicWorksController) ctx.getBean("PUBLICWORKS_CONTROLLER");
		JsonBuilder answer = publicWorksController.getCorridorData(queryParams.getValuesMap()); 

		return read(answer.toString());

	}
	
	@Trace(dispatcher = true)
	@GET
	@Path("/standardizestreet")
	@Produces("application/json")
	public Json standardizeStreet() {
		
		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();
		
		ApplicationContext ctx = AppContext.getApplicationContext();
		AddressController addressController = (AddressController) ctx.getBean("ADDRESS_CONTROLLER");
		JsonBuilder answer = addressController.standardizeStreet(queryParams.getValuesMap()); 

		return read(answer.toString());		
		
	}
	
	
	@Trace(dispatcher = true)
	@GET
	@Path("/publicworksareadata")
	@Produces("application/json")
	public Json getPublicWorksAreaData() {
		
		Form queryParams = Request.getCurrent().getResourceRef()
				.getQueryAsForm();

		ApplicationContext ctx = AppContext.getApplicationContext();
		PublicWorksController publicWorksController = (PublicWorksController) ctx.getBean("PUBLICWORKS_CONTROLLER");
		JsonBuilder answer = publicWorksController.getAreaData(queryParams.getValuesMap()); 

		return read(answer.toString());

	}
	
	
	@Trace(dispatcher = true)
	@GET
	@Path("/testme")
	@Produces("application/json")
	public Json getTest() {
		
		Request request = Request.getCurrent();
		
		JsonBuilder json = new JsonBuilder();
		Map me = new HashMap();
		me.put("hello", "bye");
		json.call(me);
		return read(json.toString());
	}
	
	
}
