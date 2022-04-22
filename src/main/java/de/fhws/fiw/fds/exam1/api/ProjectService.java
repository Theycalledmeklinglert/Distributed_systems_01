package de.fhws.fiw.fds.exam1.api;

import de.fhws.fiw.fds.exam1.database.ProjectStorage;
import de.fhws.fiw.fds.exam1.models.Project;
import de.fhws.fiw.fds.exam1.models.Student;
import de.fhws.fiw.fds.exam1.models.Supervisor;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Path( "projects" )
public class ProjectService {
	private final ProjectStorage projectStorage = ProjectStorage.getInstance();

	@Context
	protected UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@DefaultValue("") @QueryParam("name") final String name,
								@DefaultValue("") @QueryParam("type") final String type, @DefaultValue("") @QueryParam("semester") final String semester) {
		final Collection<Project> allPersons = this.projectStorage.findBy(name, type, semester);

		return Response.ok(allPersons).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectById(@PathParam("id") final long id) {
		final Optional<Project> person = this.projectStorage.readById(id);

		if (!person.isPresent()) {
			throw new WebApplicationException(Response.status(404).build());
		}

		return Response.ok(person.get()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPerson(@Context final UriInfo uriInfo, final Project project) {
		this.projectStorage.create(project);
		final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(project.getId())).build();
		return Response.created(locationURI).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") final long id, final Project project) {
		final Optional<Project> oldProject = this.projectStorage.readById(id);

		if (!oldProject.isPresent()) {
			throw new WebApplicationException(Response.status(404).build());
		}

		if (id != project.getId()) {
			throw new WebApplicationException(Response.status(400).build());
		}

		this.projectStorage.update(project);

		return Response.ok(project).build();
	}

	@DELETE
	@Path("{id}")
	public Response deletePerson(@PathParam("id") final long id) {
		this.projectStorage.deleteById(id);
		return Response.noContent().build();
	}
	/*
	@POST
	@Path("{id}: \\d+")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStudent(@PathParam("id") final long id, final Student student) {
		Optional<Project> project = this.projectStorage.readById(id);
		if (!project.isPresent()) {
			throw new WebApplicationException(Response.status(404).build());
		}
		project.ifPresent(p -> p.addStudent(student)); // Muss ich hier pruefen ob der uebergebene JSON Body "richtig" ist? Also das keine Attribute fehlen, etc. oder macht
		// der JSON Converter das automatisch?
		return Response.status(201).build();

		// Wie kann ich herausfinden ob ein Student oder ein Supervisor hinzugefuegt werden soll?
		// Merkt der JSON Converter das automatisch anhand der Attribute die uebergeben werden?

	}

	@POST
	@Path("{id}: \\d+")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSupervisor(@PathParam("id") final long id, final Supervisor supervisor) {
		Optional<Project> project = this.projectStorage.readById(id);
		if (!project.isPresent()) {
			throw new WebApplicationException(Response.status(404).build());
		}
		project.ifPresent(p -> p.addSupervisor(supervisor)); // Muss ich hier pruefen ob der uebergebene JSON Body "richtig" ist? Also das keine Attribute fehlen, etc. oder macht
		// der JSON Converter das automatisch?
		return Response.status(201).build();
	}
	*/

}
