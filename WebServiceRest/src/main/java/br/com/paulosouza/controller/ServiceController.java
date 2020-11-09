package br.com.paulosouza.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.JsonObject;

import br.com.paulosouza.entity.VeiculoEntity;
import br.com.paulosouza.http.Veiculo;
import br.com.paulosouza.repository.VeiculoRepository;

@Path("/veiculos")
public class ServiceController {

	private final VeiculoRepository repository = new VeiculoRepository();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@GET
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/findById/{id}")
	public Veiculo FindById(@PathParam("id") String id) {

		VeiculoEntity entity = repository.GetVeiculo(Integer.parseInt(id));

		return new Veiculo(entity.getId(), entity.getVeiculo(), entity.getMarca(), entity.getAno(),
				entity.getDescricao(), entity.isVendido(), entity.getCreated(), entity.getUpdated());
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	public List<Veiculo> TodosVeiculos() {

		List<Veiculo> veiculos = new ArrayList<Veiculo>();

		List<VeiculoEntity> listaEntityVeiculos = repository.TodosVeiculos();

		for (VeiculoEntity entity : listaEntityVeiculos) {

			veiculos.add(new Veiculo(entity.getId(), entity.getVeiculo(), entity.getMarca(), entity.getAno(),
					entity.getDescricao(), entity.isVendido(), entity.getCreated(), entity.getUpdated()));
		}

		return veiculos;
	}
	
	@GET
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/findByMarca/{marca}")
	public List<Veiculo> FindByVeiculosPorMarca(@PathParam("marca") String marca) {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();

		List<VeiculoEntity> listaEntityVeiculos = repository.VeiculosPorMarca(marca);

		for (VeiculoEntity entity : listaEntityVeiculos) {
				veiculos.add(new Veiculo(entity.getId(), entity.getVeiculo(), entity.getMarca(), entity.getAno(),
					entity.getDescricao(), entity.isVendido(), entity.getCreated(), entity.getUpdated()));
		}

		return veiculos;
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/findByNaoVendidos")
	public List<Veiculo> FindByVeiculosNaoVendidos() {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();

		List<VeiculoEntity> listaEntityVeiculos = repository.VeiculosNaoVendidos();

		for (VeiculoEntity entity : listaEntityVeiculos) {
				veiculos.add(new Veiculo(entity.getId(), entity.getVeiculo(), entity.getMarca(), entity.getAno(),
					entity.getDescricao(), entity.isVendido(), entity.getCreated(), entity.getUpdated()));
		}

		return veiculos;
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/findByQuantidadeNaoVendidos")
	public String FindByQuantidadeVeiculosNaoVendidos() {
		JsonObject result = new JsonObject();
		result.addProperty("naoVendidos", repository.QuantidadeVeiculosNaoVendidos());

		return result.toString();
	}
	
	@GET
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/findByQuantidadeMarca/{marca}")
	public String FindByQuantidadeVeiculosPorMarca(@PathParam("marca") String marca) {
		
		JsonObject result = new JsonObject();
		result.addProperty("marca", marca);
		result.addProperty("qtdMarca", repository.QuantidadeVeiculosPorMarca(marca));

		return result.toString();
	}
	
	@GET
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/findByRegistradosUltimaSemana")
	public List<Veiculo> FindByVeiculosRegistradosNaUltimaSemana() {

		List<Veiculo> veiculos = new ArrayList<Veiculo>();


		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		//System.out.println("data inicial: " + dataInicial.getTime());
		//System.out.println("data final: " + dataFinal.getTime());
		//System.out.println("----------------");
		int diaDaSemana = dataInicial.get(Calendar.DAY_OF_WEEK);
		int semanaDoMes = dataInicial.get(Calendar.WEEK_OF_MONTH);
		if (diaDaSemana == 1)
			diaDaSemana = 7;
		else
			diaDaSemana--;
		if (semanaDoMes == 1)
			semanaDoMes = 4;
		else
			semanaDoMes--;
		dataInicial.set(Calendar.DAY_OF_WEEK, 1);
		dataInicial.set(Calendar.WEEK_OF_MONTH, semanaDoMes);
		dataFinal.set(Calendar.DAY_OF_WEEK, 7);
		dataFinal.set(Calendar.WEEK_OF_MONTH, semanaDoMes);

		//System.out.println("data inicial: " + dataInicial.getTime());
		//System.out.println("data final: " + dataFinal.getTime());

		//System.out.println("data inicial format: " + dateFormat.format(dataInicial.getTime()));
		//System.out.println("data final format: " + dateFormat.format(dataFinal.getTime()));

		List<VeiculoEntity> listaEntityVeiculos = repository.VeiculosRegistradosUltimaSemana(
				dateFormat.format(dataInicial.getTime()),
				dateFormat.format(dataFinal.getTime()));
		
		for (VeiculoEntity entity : listaEntityVeiculos) {
			veiculos.add(new Veiculo(entity.getId(), entity.getVeiculo(), entity.getMarca(), entity.getAno(),
				entity.getDescricao(), entity.isVendido(), entity.getCreated(), entity.getUpdated()));
		}
		return veiculos;
	}
	
	@GET
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/findByDistribuidosNaDecada/{decada}")
	public List<Veiculo> FindByVeiculosDistribuidosNaDecada(@PathParam("decada") int decada) {

		List<Veiculo> veiculos = new ArrayList<Veiculo>();

		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();

		// Ex: dataInicial: Sun Jan 01 00:00:00 BRT 1911
		dataInicial.set(Calendar.DAY_OF_MONTH, 1);
		dataInicial.set(Calendar.MONTH, 0);
		dataInicial.set(Calendar.YEAR, decada - 9);
		dataInicial.set(Calendar.HOUR_OF_DAY, 0);
		dataInicial.set(Calendar.HOUR, 0);
		dataInicial.set(Calendar.MINUTE, 0);
		dataInicial.set(Calendar.SECOND, 0);
		dataInicial.set(Calendar.AM_PM, 0);

		// Ex: dataFinal: Fri Dec 31 23:59:59 BRT 1920
		dataFinal.set(Calendar.DAY_OF_MONTH, 31);
		dataFinal.set(Calendar.MONTH, 11);
		dataFinal.set(Calendar.YEAR, decada);
		dataFinal.set(Calendar.HOUR_OF_DAY, 23);
		dataFinal.set(Calendar.HOUR, 23);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);
		dataFinal.set(Calendar.AM_PM, 0);

		//System.out.println("data inicial: " + dataInicial.getTime());
		//System.out.println("data final: " + dataFinal.getTime());

		//System.out.println("data inicial format: " + dateFormat.format(dataInicial.getTime()));
		//System.out.println("data final format: " + dateFormat.format(dataFinal.getTime()));

		List<VeiculoEntity> listaEntityVeiculos = repository.VeiculosDistribuidoNaDecada(
				dateFormat.format(dataInicial.getTime()),
				dateFormat.format(dataFinal.getTime()));
		
		for (VeiculoEntity entity : listaEntityVeiculos) {
			veiculos.add(new Veiculo(entity.getId(), entity.getVeiculo(), entity.getMarca(), entity.getAno(),
				entity.getDescricao(), entity.isVendido(), entity.getCreated(), entity.getUpdated()));
		}
		return veiculos;
	}
	
	@GET
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/findByQuantidadeDistribuidosNaDecada/{decada}")
	public String FindByQuantidadeVeiculosDistribuidosNaDecada(@PathParam("decada") int decada) {
		
		JsonObject result = new JsonObject();
		
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();

		// Ex: dataInicial: Sun Jan 01 00:00:00 BRT 1911
		dataInicial.set(Calendar.DAY_OF_MONTH, 1);
		dataInicial.set(Calendar.MONTH, 0);
		dataInicial.set(Calendar.YEAR, decada - 9);
		dataInicial.set(Calendar.HOUR_OF_DAY, 0);
		dataInicial.set(Calendar.HOUR, 0);
		dataInicial.set(Calendar.MINUTE, 0);
		dataInicial.set(Calendar.SECOND, 0);
		dataInicial.set(Calendar.AM_PM, 0);

		// Ex: dataFinal: Fri Dec 31 23:59:59 BRT 1920
		dataFinal.set(Calendar.DAY_OF_MONTH, 31);
		dataFinal.set(Calendar.MONTH, 11);
		dataFinal.set(Calendar.YEAR, decada);
		dataFinal.set(Calendar.HOUR_OF_DAY, 23);
		dataFinal.set(Calendar.HOUR, 23);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);
		dataFinal.set(Calendar.AM_PM, 0);

		//System.out.println("data inicial: " + dataInicial.getTime());
		//System.out.println("data final: " + dataFinal.getTime());

		//System.out.println("data inicial format: " + dateFormat.format(dataInicial.getTime()));
		//System.out.println("data final format: " + dateFormat.format(dataFinal.getTime()));

		String quantidadeListaEntityVeiculos = repository.QuantidadeVeiculosDistribuidoNaDecada(
				dateFormat.format(dataInicial.getTime()),
				dateFormat.format(dataFinal.getTime()));

		result.addProperty("decada", String.valueOf(decada));
		result.addProperty("qtdDecada", quantidadeListaEntityVeiculos);

		return result.toString();
	}
	
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String Cadastrar(Veiculo veiculo) {

		VeiculoEntity entity = new VeiculoEntity();
		Calendar data = Calendar.getInstance();

		try {

			entity.setVeiculo(veiculo.getVeiculo());
			entity.setMarca(veiculo.getMarca());
			entity.setAno(veiculo.getAno());
			entity.setDescricao(veiculo.getDescricao());
			entity.setVendido(veiculo.isVendido());
			entity.setCreated(data.getTime());
			entity.setUpdated(data.getTime());

			repository.Salvar(entity);

			return "Registro cadastrado com sucesso!";

		} catch (Exception e) {

			return "Erro ao cadastrar um registro " + e.getMessage();
		}

	}
	
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String Alterar(Veiculo veiculo) {

		VeiculoEntity entity = new VeiculoEntity();
		Calendar data = Calendar.getInstance();

		try {

			entity.setId(veiculo.getId());
			entity.setVeiculo(veiculo.getVeiculo());
			entity.setMarca(veiculo.getMarca());
			entity.setAno(veiculo.getAno());
			entity.setDescricao(veiculo.getDescricao());
			entity.setVendido(veiculo.isVendido());
			if(veiculo.getCreated()!=null)
				entity.setCreated(veiculo.getCreated());
			else
				entity.setCreated(data.getTime());
			entity.setUpdated(data.getTime());

			repository.Alterar(entity);

			return "Registro alterado com sucesso!";

		} catch (Exception e) {

			return "Erro ao alterar um registro " + e.getMessage();
		}

	}
	
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("{id}")
	public String Excluir(@PathParam("id") Integer id) {

		//System.out.println("id: "+id);
		try {

			repository.Excluir(id);

			return "Registro excluido com sucesso!";

		} catch (Exception e) {

			return "Erro ao excluir o registro! " + e.getMessage();
		}

	}

}
