/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.exam1;

import de.fhws.fiw.fds.exam1.client.WebApiClient;
import de.fhws.fiw.fds.exam1.client.WebApiResponse;
import de.fhws.fiw.fds.exam1.client.ProjectView;
import de.fhws.fiw.fds.exam1.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import de.fhws.fiw.fds.exam1.client.ProjectView;
import de.fhws.fiw.fds.exam1.client.WebApiClient;
import de.fhws.fiw.fds.exam1.client.WebApiResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestYourApi
{

		@Test
		public void load_existing_project_by_id_status200( ) throws IOException
		{
			final WebApiClient client = new WebApiClient( );
			ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
			final WebApiResponse postResponse = client.postProject(project);
			final WebApiResponse response = client.loadById( 1l );

			final Optional<ProjectView> result = response.getResponseData( ).stream( ).findFirst( );
			assertTrue( result.isPresent( ) );
			final ProjectView gotProject = result.get( );

			assertEquals( 200, response.getLastStatusCode( ) );
			assertEquals( 1, response.getResponseData( ).size( ) );

			assertEquals( "TestProject", gotProject.getName( ) );
			assertEquals( "This is a test Project", gotProject.getDescription( ) );
			assertEquals("2022ws", gotProject.getSemester( ));
			assertEquals("testProject", gotProject.getType( ));

			client.deleteProjectById(postResponse.getIdFromHeaderString());
		}

		@Test
		public void load_project_by_invalid_id_status404( ) throws IOException
		{
			final WebApiClient client = new WebApiClient( );
			ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
			final WebApiResponse postResponse = client.postProject(project);
			final WebApiResponse loadResponse = client.loadById( 2l );
			Optional<ProjectView> gotProjectOpt = loadResponse.getResponseData().stream().findFirst();

			assertFalse(gotProjectOpt.isPresent());
			assertEquals( 404, loadResponse.getLastStatusCode( ) );
			assertEquals( 0, loadResponse.getResponseData( ).size( ) );

			client.deleteProjectById(postResponse.getIdFromHeaderString());
		}

    @Test
    public void load_existing_project_by_name_status200( ) throws IOException
    {
        final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
        final WebApiResponse loadResponse = client.loadAllProjectsByName("TestProject");

        final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
        assertTrue( result.isPresent( ) );
        final ProjectView gotProject = result.get( );

        assertEquals( 200, loadResponse.getLastStatusCode( ) );
        assertEquals(1, loadResponse.getResponseData().size());
        assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_name_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByName("Muster Project");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );

		assertFalse( result.isPresent( ) );
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_existing_project_by_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsBySemester("2022ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final ProjectView gotProject = result.get( );

		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(1, loadResponse.getResponseData().size());
		assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsBySemester("2020ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );

		assertFalse(result.isPresent());
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_existing_project_by_type_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByType("testProject");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final ProjectView gotProject = result.get( );

		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(1, loadResponse.getResponseData().size());
		assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_type_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByType("programmning project");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );

		assertFalse(result.isPresent());
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_existing_project_by_name_and_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("TestProject", "", "2022ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final ProjectView gotProject = result.get( );

		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(1, loadResponse.getResponseData().size());
		assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_name_and_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("TestProject", "", "2023ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertFalse( result.isPresent( ) );
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_existing_project_by_name_and_type_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("TestProject", "testProject", "");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final ProjectView gotProject = result.get( );

		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(1, loadResponse.getResponseData().size());
		assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_name_and_type_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("Project", "testProject", "");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertFalse( result.isPresent( ) );
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_existing_project_by_type_and_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("", "testProject", "2022ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final ProjectView gotProject = result.get( );

		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(1, loadResponse.getResponseData().size());
		assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_type_and_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("", "testProject", "2025ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertFalse( result.isPresent( ) );
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_existing_project_by_name_type_and_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("TestProject", "testProject", "2022ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final ProjectView gotProject = result.get( );

		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(1, loadResponse.getResponseData().size());
		assertEquals( "TestProject", gotProject.getName() );
		assertEquals( "This is a test Project", gotProject.getDescription() );
		assertEquals( "2022ws", gotProject.getSemester() );
		assertEquals( "testProject", gotProject.getType() );
		assertEquals(null, gotProject.getStudents());
		assertEquals(null, gotProject.getSupervisors());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}

	@Test
	public void load_non_existing_project_by_name_type_and_semester_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final WebApiResponse loadResponse = client.loadAllProjectsByNameANDSemesterANDType("TestProject", "programming Project", "2022ws");

		final Optional<ProjectView> result = loadResponse.getResponseData( ).stream( ).findFirst( );
		assertFalse( result.isPresent( ) );
		assertEquals( 200, loadResponse.getLastStatusCode( ) );
		assertEquals(0, loadResponse.getResponseData().size());

		client.deleteProjectById(postResponse.getIdFromHeaderString());
	}


	@Test
	public void test_post_project_status201( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse response = client.postProject(project);

		final WebApiResponse loadResponse = client.loadById(response.getIdFromHeaderString());
		Optional<ProjectView> gotProjectOptional = loadResponse.getResponseData().stream().findFirst();

		assertTrue(gotProjectOptional.isPresent());
		ProjectView gotProject = gotProjectOptional.get();

		assertEquals( 201, response.getLastStatusCode( ) );
		assertEquals( 1, loadResponse.getResponseData( ).size( ) );
		assertEquals("TestProject", gotProject.getName());
		assertEquals("This is a test Project", gotProject.getDescription());
		assertEquals("2022ws", gotProject.getSemester());
		assertEquals("testProject", gotProject.getType());

		client.deleteProjectById(response.getIdFromHeaderString());

	}

	@Test
	public void test_post_invalid_project_status422( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "asdasdsa", "testProject");
		final WebApiResponse postResponse = client.postProject(project);

		assertEquals( 422, postResponse.getLastStatusCode( ) );
		assertEquals(Collections.EMPTY_LIST, postResponse.getResponseData( ) );

		WebApiResponse loadResponse = client.loadAllProjects();
		Optional<ProjectView> gotProjectOptional = loadResponse.getResponseData().stream().findFirst();
		assertFalse(gotProjectOptional.isPresent());

	}

	@Test
	public void test_post_empty_project_status422( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView();
		final WebApiResponse postResponse = client.postProject(project);

		assertEquals( 422, postResponse.getLastStatusCode( ) );
		assertEquals(Collections.EMPTY_LIST, postResponse.getResponseData( ) );

		WebApiResponse loadResponse = client.loadAllProjects();
		Optional<ProjectView> gotProjectOptional = loadResponse.getResponseData().stream().findFirst();
		assertFalse(gotProjectOptional.isPresent());
	}

	@Test
	public void test_put_with_existent_project_status204( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);

		final long id = postResponse.getIdFromHeaderString();
		final WebApiResponse loadResponse = client.loadById(id);

		ProjectView update = new ProjectView("Updated TestProject", "This is an updated test Project", "2023ws", "updatedTestProject");
		WebApiResponse putResponse = client.updateProject(id, update);

		final WebApiResponse updatedLoadResponse = client.loadById(id);
		Optional<ProjectView> updatedProjectOptional = updatedLoadResponse.getResponseData().stream().findFirst();

		assertTrue(updatedProjectOptional.isPresent());
		ProjectView updatedProject = updatedProjectOptional.get();

		assertEquals(204, putResponse.getLastStatusCode());
		assertEquals("Updated TestProject", updatedProject.getName());
		assertEquals("This is an updated test Project", updatedProject.getDescription());
		assertEquals("2023ws", updatedProject.getSemester());
		assertEquals("updatedTestProject", updatedProject.getType());
		assertEquals(null, updatedProject.getStudents());
		assertEquals(null, updatedProject.getSupervisors());

		client.deleteProjectById(id);
	}

	@Test
	public void test_put_with_non_existent_project_status404( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );

		final long id = 1l;
		ProjectView update = new ProjectView("Updated TestProject", "This is an updated test Project", "2023ws", "updatedTestProject");
		WebApiResponse putResponse = client.updateProject(id, update);

		final WebApiResponse updatedLoadResponse = client.loadById(id);
		Optional<ProjectView> updatedProjectOptional = updatedLoadResponse.getResponseData().stream().findFirst();
		assertFalse(updatedProjectOptional.isPresent());
		assertEquals(404, putResponse.getLastStatusCode());

		client.deleteProjectById(id);
	}

	@Test
	public void test_put_with_unchanged_existent_project_status400( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);

		final long id = postResponse.getIdFromHeaderString();

		ProjectView update = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		WebApiResponse putResponse = client.updateProject(id, update);

		final WebApiResponse updatedLoadResponse = client.loadById(id);
		Optional<ProjectView> updatedProjectOptional = updatedLoadResponse.getResponseData().stream().findFirst();

		assertTrue(updatedProjectOptional.isPresent());
		ProjectView updatedProject = updatedProjectOptional.get();

		assertEquals(204, putResponse.getLastStatusCode());
		assertEquals("TestProject", updatedProject.getName());
		assertEquals("This is a test Project", updatedProject.getDescription());
		assertEquals("2022ws", updatedProject.getSemester());
		assertEquals("testProject", updatedProject.getType());
		assertEquals(null, updatedProject.getStudents());
		assertEquals(null, updatedProject.getSupervisors());

		client.deleteProjectById(id);
	}

	@Test
	public void test_delete_with_existing_id_status204() throws IOException
	{
		final WebApiClient client = new WebApiClient();
		ProjectView project = new ProjectView("TestProject", "This is a test Project", "2022ws", "testProject");
		final WebApiResponse postResponse = client.postProject(project);
		final long id = postResponse.getIdFromHeaderString();

		final WebApiResponse deleteResponse = client.deleteProjectById(id);
		final WebApiResponse loadResponse = client.loadById(id);

		Optional<ProjectView> gotProjectOpt = loadResponse.getResponseData().stream().findFirst();

		assertFalse(gotProjectOpt.isPresent());
		assertEquals(204, deleteResponse.getLastStatusCode());
		assertEquals(404, loadResponse.getLastStatusCode());
		assertEquals(0, loadResponse.getResponseData().size());
	}

	@Test
	public void test_delete_with_non_existing_id_status204() throws IOException
	{
		final WebApiClient client = new WebApiClient();
		final long id = 1l;
		final WebApiResponse deleteResponse = client.deleteProjectById(id);
		final WebApiResponse loadResponse = client.loadById(id);

		Optional<ProjectView> gotProjectOpt = loadResponse.getResponseData().stream().findFirst();

		assertFalse(gotProjectOpt.isPresent());
		assertEquals(204, deleteResponse.getLastStatusCode());
		assertEquals(404, loadResponse.getLastStatusCode());
		assertEquals(0, loadResponse.getResponseData().size());
	}

}

