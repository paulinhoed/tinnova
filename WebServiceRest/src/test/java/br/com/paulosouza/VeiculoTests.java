package br.com.paulosouza;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import br.com.paulosouza.controller.ServiceController;
import br.com.paulosouza.http.Veiculo;

public class VeiculoTests extends JerseyTest {

	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(ServiceController.class);
	}

	@Test
	public void testTodosVeiculos() {
		Response response = target("/veiculos").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return veiculo list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testFindByVeiculosPorMarca() {
		Response response = target("/veiculos/findByMarca/Vokswagen").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return veiculo list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testQuantidadeFindByVeiculosPorMarca() {
		Response response = target("/veiculos/findByQuantidadeMarca/Vokswagen").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return quantity of veiculo", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testFindByVeiculosNaoVendidos() {
		Response response = target("/veiculos/findByNaoVendidos").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return veiculo list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testFindByQuantidadeVeiculosNaoVendidos() {
		Response response = target("/veiculos/findByQuantidadeNaoVendidos").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return quantity of veiculo", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testFindByVeiculosRegistradosNaUltimaSemana() {
		Response response = target("/veiculos/findByRegistradosUltimaSemana").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return veiculo list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testFindByVeiculosDistribuidosNaDecada() {
		Response response = target("/veiculos/findByDistribuidosNaDecada/2020").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return veiculo list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	@Test
	public void testFindByQuantidadeVeiculosDistribuidosNaDecada() {
		Response response = target("/veiculos/findByQuantidadeDistribuidosNaDecada/2020").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return quantity of veiculo", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	@Test
	public void testCreate() {
		Veiculo veiculo = new Veiculo(0, "Fusca", "Volkswagen", 1950, "Preto", false, null, null);
		Response output = target("/veiculos").request().post(Entity.entity(veiculo, MediaType.APPLICATION_JSON));
		System.out.println(output.getStatus());
		assertEquals("Should return status 200", 200, output.getStatus());
	}
	
	@Test
	public void testUpdate() {
		Veiculo veiculo = new Veiculo(1, "Fusca", "Volkswagen", 1950, "Branco", true, null, null);
		Response output = target("/veiculos").request().put(Entity.entity(veiculo, MediaType.APPLICATION_JSON));
		System.out.println(output.getStatus());
		assertEquals("Should return status 200", 200, output.getStatus());
	}

	@Test
	public void testDelete() {
		Response output = target("/veiculos/1").request().delete();
		assertEquals("Should return status 200", 200, output.getStatus());
	}
}