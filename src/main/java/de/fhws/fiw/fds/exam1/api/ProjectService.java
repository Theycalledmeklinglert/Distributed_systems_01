package de.fhws.fiw.fds.exam1.api;

import de.fhws.fiw.fds.exam1.database.ProjectStorage;
import de.fhws.fiw.fds.exam1.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.*;

import static de.fhws.fiw.fds.exam1.models.ValidityCheck.checkProject;

@Path( "projects" )
public class ProjectService {

	@Context protected UriInfo uriInfo;
	private final ProjectStorage projectStorage = ProjectStorage.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@DefaultValue("") @QueryParam("name") final String name,
								@DefaultValue("") @QueryParam("type") final String type, @DefaultValue("") @QueryParam("semester") final String semester) {
		final Collection<Project> allPersons = this.projectStorage.findBy(name, type, semester);

		return Response.ok(allPersons).build();
	}

	@GET
	@Path("{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectById(@PathParam("id") final long id) {
		final Optional<Project> project = this.projectStorage.readById(id);

		if (!project.isPresent()) {
			throw new WebApplicationException(Response.status(404).build());
		}

		return Response.ok(project.get()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProject(final Project project) {
		if(!checkProject(project)) {
			throw new WebApplicationException(Response.status(422).build());
		}
		this.projectStorage.create(project);
		final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(project.getId())).build();
		return Response.created(locationURI).build();
	}

	@PUT
	@Path("{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") final long id, final Project newProject) {
		Optional<Project> readProject = this.projectStorage.readById(id);

		if (!readProject.isPresent())
		{
			throw new WebApplicationException(Response.status(404).build());
		}
		Project oldProject = readProject.get();

		if(!checkProject(newProject))
		{
			throw new WebApplicationException(Response.status(422).build());
		}

		this.projectStorage.update(newProject, id);

		return Response.noContent().build();
	}




	@DELETE
	@Path("{id: \\d+}")
	public Response deletePerson(@PathParam("id") final long id) {
		this.projectStorage.deleteById(id);
		return Response.noContent().build();
	}


}
