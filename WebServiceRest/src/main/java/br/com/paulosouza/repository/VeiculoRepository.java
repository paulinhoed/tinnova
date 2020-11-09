package br.com.paulosouza.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.paulosouza.entity.VeiculoEntity;

public class VeiculoRepository {
	private final EntityManagerFactory entityManagerFactory;

	private final EntityManager entityManager;

	public VeiculoRepository() {

		/*
		 * CRIANDO O NOSSO EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO
		 * persistence.xml
		 */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_concessionaria");

		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * CONSULTA UM VEICULO CADASTRADO PELO CÓDIGO
	 * */
	public VeiculoEntity GetVeiculo(Integer id){
 
		return this.entityManager.find(VeiculoEntity.class, id);
	}

	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 */
	public void Salvar(VeiculoEntity veiculoEntity) {

		this.entityManager.getTransaction().begin();
		this.entityManager.persist(veiculoEntity);
		this.entityManager.getTransaction().commit();
	}

	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 */
	public void Alterar(VeiculoEntity veiculoEntity) {

		this.entityManager.getTransaction().begin();
		this.entityManager.merge(veiculoEntity);
		this.entityManager.getTransaction().commit();
	}

	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	 **/
	public void Excluir(Integer id) {

		VeiculoEntity veiculo = this.GetVeiculo(id);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(veiculo);
		this.entityManager.getTransaction().commit();

	}

	/**
	 * RETORNA TODOS OS VEICULOS CADASTRADAS NO BANCO DE DADOS
	 */
	@SuppressWarnings("unchecked")
	public List<VeiculoEntity> TodosVeiculos() {

		return this.entityManager.createQuery("SELECT p FROM VeiculoEntity p ORDER BY p.id").getResultList();
	}

	/**
	 * CONSULTA VEICULOS NAO VENDIDOS
	 */
	@SuppressWarnings("unchecked")
	public List<VeiculoEntity> VeiculosNaoVendidos() {

		return this.entityManager.createQuery("SELECT p FROM VeiculoEntity p WHERE p.vendido is false ORDER BY p.id").getResultList();
	}
	
	/**
	 * CONSULTA TODOS OS VEICULOS POR MARCA
	 */
	@SuppressWarnings("unchecked")
	public List<VeiculoEntity> VeiculosPorMarca(String marca) {

		return this.entityManager.createQuery("SELECT p FROM VeiculoEntity p "
				+ "WHERE p.marca = '"+marca+"' ORDER BY p.id").getResultList();
	}
	
	/**
	 * CONSULTA QUANTIDADE OS VEICULOS POR MARCA
	 */
	public String QuantidadeVeiculosPorMarca(String marca) {

		Query query = this.entityManager.createQuery("SELECT COUNT(*) FROM VeiculoEntity p "
				+ "WHERE p.marca = '"+marca+"'");

		return query.getSingleResult().toString();
	}
	
	/**
	 * CONSULTA VEICULOS REGISTRADOS NA ULTIMA SEMANA
	 */
	@SuppressWarnings("unchecked")
	public List<VeiculoEntity> VeiculosRegistradosUltimaSemana(String dataInicial, String dataFinal) {

		return this.entityManager.createQuery("SELECT p FROM VeiculoEntity p "
				+ "WHERE p.created BETWEEN '"+dataInicial+"' AND '"+dataFinal+"' ORDER BY p.id").getResultList();
	}
	
	/**
	 * CONSULTA VEICULOS DISTRIBUIDOS NA DECADA
	 */
	@SuppressWarnings("unchecked")
	public List<VeiculoEntity> VeiculosDistribuidoNaDecada(String decadaInicial, String decadaFinal) {

		return this.entityManager.createQuery("SELECT p FROM VeiculoEntity p "
				+ "WHERE p.ano BETWEEN '"+decadaInicial+"' AND '"+decadaFinal+"' ORDER BY p.id").getResultList();
	}

	
	/**
	 * CONSULTA QUANTIDADE VEICULOS DISTRIBUIDOS NA DECADA
	 */
	public String QuantidadeVeiculosDistribuidoNaDecada(String decadaInicial, String decadaFinal) {

		Query query = this.entityManager.createQuery("SELECT COUNT(*) FROM VeiculoEntity p "
				+ "WHERE p.ano BETWEEN '"+decadaInicial+"' AND '"+decadaFinal+"' ORDER BY p.id");

		return query.getSingleResult().toString();
	}
	
	/**
	 * CONSULTA VEICULOS NAO VENDIDOS
	 */
	public String QuantidadeVeiculosNaoVendidos() {

		Query query = this.entityManager.createQuery("SELECT COUNT(*) FROM VeiculoEntity p WHERE p.vendido is false ORDER BY p.id");

		return query.getSingleResult().toString();
	}
}
