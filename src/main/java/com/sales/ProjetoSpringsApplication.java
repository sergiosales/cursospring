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
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
	}

}
