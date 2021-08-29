package com.sales;

import java.text.SimpleDateFormat;
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
import com.sales.domain.ItemPedido;
import com.sales.domain.Pagamento;
import com.sales.domain.PagamentoComBoleto;
import com.sales.domain.PagamentoComCartao;
import com.sales.domain.Pedido;
import com.sales.domain.Produto;
import com.sales.domain.enuns.EstadoPagamento;
import com.sales.domain.enuns.TipoCliente;
import com.sales.repository.CategoriaRepository;
import com.sales.repository.CidadeRepository;
import com.sales.repository.ClienteRepository;
import com.sales.repository.EnderecoRepository;
import com.sales.repository.EstadoRepository;
import com.sales.repository.ItemPedidoRepository;
import com.sales.repository.PagamentoRepository;
import com.sales.repository.PedidoRepository;
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

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 Categoria cat  = new Categoria(null, "Informatica");
	 Categoria cat1  = new Categoria(null, "Escritorio");
	 Categoria cat2  = new Categoria(null, "Roupa");
	 Categoria cat3  = new Categoria(null, "Livros");
	 Categoria cat4 = new Categoria(null, "Eletronicos");
	 Categoria cat5  = new Categoria(null, "Mesas");
	 Categoria cat6 = new Categoria(null, "Camas");
	 Categoria cat7  = new Categoria(null, "Bolsas");
	 
	 Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
	 
	 Estado est1= new Estado(null, "Minas Gerais");
	 Estado est2= new Estado(null, "São Paulo");
	 
	 Cidade cid1 = new Cidade(null,"Uberlandia",est1);
	 Cidade cid2 = new Cidade(null,"Sao Paulo",est2);
	 Cidade cid3 = new Cidade(null,"Campinas",est2);
	 
	estadoRepository.saveAll(Arrays.asList(est1,est2));
	cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
	
	
	
	Cliente cli1 = new Cliente(null,"Paulo Sergio Sales", "paulounopar@hotmail.com", "02735894908", TipoCliente.PessoaFisica);
	cli1.getTelefones().addAll(Arrays.asList("33294068","998115250"));
	
	Endereco end = new Endereco(null, "Av Clarice de Lima Castro ", "355", "bl11 apt23", "Nova Olinda", "86073310", cli1, cid1);
	Endereco end2 = new Endereco(null, "Av Luzia de Lima Castro ", "500", "casa dos fundos", "Nova Esperança", "86070010", cli1, cid1);

	cli1.getEnderecos().addAll(Arrays.asList(end,end2)); 
	 
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(end,end2));
//	enderecoRepository.saveAll(Arrays.asList(end));
	
	
	SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido ped1 = new Pedido(null, stf.parse("29/07/21 20:18"), cli1, end);
	Pedido ped2 = new Pedido(null, stf.parse("28/07/21 20:18"), cli1, end2);
	
	Pagamento pagt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1,6);
	ped1.setPagamento(pagt1);

	
	Pagamento pagt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, stf.parse("29/07/21 20:18"), null);
	ped2.setPagamento(pagt2); 
	
	 
	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
	pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	pagamentoRepository.saveAll( Arrays.asList(pagt1,pagt2));
	
	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 2000.00, 1);
	ItemPedido ip2 = new ItemPedido(ped1, p10, 0.00, 800.00, 1);
	
	ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	ped2.getItens().add(ip2);
	
	p1.getItens().addAll(Arrays.asList(ip1));
	p10.getItens().add(ip2);
	
	itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2));
	 
	}

}
