package com.sales;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sales.domain.Categoria;
import com.sales.domain.Cidade;
import com.sales.domain.Cliente;
import com.sales.domain.Endereco;
import com.sales.domain.Estado;
import com.sales.domain.Produto;
import com.sales.domain.enuns.TipoCliente;
import com.sales.repository.CategoriaRepository;
import com.sales.repository.CidadeRepository;
import com.sales.repository.ClienteRepository;
import com.sales.repository.EnderecoRepository;
import com.sales.repository.EstadoRepository;
import com.sales.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetoSpringsApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 Categoria cat  = new Categoria(null, "Informatica");
	 Categoria cat1  = new Categoria(null, "Escritorio");
	 
	 Produto prod= new Produto(null, "impressora", 250.00);
	 Produto prod1= new Produto(null, "Computador", 2500.00);
	 Produto prod2= new Produto(null, "notebook", 4500.00);
	 Produto prod3= new Produto(null, "cadeira giratoria", 800.00);
	 
	 
	 cat1.getProdutos().addAll(Arrays.asList(prod,prod1,prod2));
	 cat.getProdutos().addAll(Arrays.asList(prod3));
	 
	 prod.getCategorias().add(cat);
	 prod1.getCategorias().add(cat);
	 prod2.getCategorias().add(cat);
	 prod3.getCategorias().add(cat1);
	 
	 Estado est1= new Estado(null, "Minas Gerais");
	 Estado est2= new Estado(null, "São Paulo");
	 
	 categoriaRepository.saveAll(Arrays.asList(cat,cat1));
	 produtoRepository.saveAll(Arrays.asList(prod,prod1,prod2,prod3));
	 
	 
	 
	 Cidade cid1 = new Cidade(null,"Uberlandia",est1);
	 Cidade cid2 = new Cidade(null,"Sao Paulo",est2);
	 Cidade cid3 = new Cidade(null,"Campinas",est2);
	 
	estadoRepository.saveAll(Arrays.asList(est1,est2));
	cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
	
	Cliente cli1 = new Cliente(null,"Paulo Sergio Sales", "paulounopar@hotmail.com", "02735894908", TipoCliente.PessoaFisica.getCod());
	cli1.getTelefones().addAll(Arrays.asList("33294068","998115250"));
	Endereco end = new Endereco(null, "Av Clarice de Lima Castro ", "355", "bl11 apt23", "Nova Olinda", "86073310", cli1, cid1);
	cli1.getEnderecos().addAll(Arrays.asList(end)); 
	 
	clienteRepository.saveAll(Arrays.asList(cli1));
	
//	enderecoRepository.saveAll(Arrays.asList(end));
	enderecoRepository.save(end);
	

	
	 
	}

}
