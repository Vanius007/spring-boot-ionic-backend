package com.vanius.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vanius.cursomc.domain.Categoria;
import com.vanius.cursomc.domain.Cidade;
import com.vanius.cursomc.domain.Cliente;
import com.vanius.cursomc.domain.Endereco;
import com.vanius.cursomc.domain.Estado;
import com.vanius.cursomc.domain.ItemPedido;
import com.vanius.cursomc.domain.Pagamento;
import com.vanius.cursomc.domain.PagamentoComBoleto;
import com.vanius.cursomc.domain.PagamentoComCartao;
import com.vanius.cursomc.domain.Pedido;
import com.vanius.cursomc.domain.Produto;
import com.vanius.cursomc.domain.enums.EstadoPagamento;
import com.vanius.cursomc.domain.enums.TipoCliente;
import com.vanius.cursomc.repositories.CategoriaRepository;
import com.vanius.cursomc.repositories.CidadeRepository;
import com.vanius.cursomc.repositories.ClienteRepository;
import com.vanius.cursomc.repositories.EnderecoRepository;
import com.vanius.cursomc.repositories.EstadoRepository;
import com.vanius.cursomc.repositories.ItemPedidoRepository;
import com.vanius.cursomc.repositories.PagamentoRepository;
import com.vanius.cursomc.repositories.PedidoRepository;
import com.vanius.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;
	
	@Autowired
	private ProdutoRepository prod;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		repo.save(Arrays.asList(cat1,cat2));	
		prod.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));	
		
		estadoRepo.save(Arrays.asList(est1,est2));
		cidadeRepo.save(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null,"Maria", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("32120603", "997990125"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 03", "jardim", "38220834",cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Matos", "105", "sala 800", "centro", "38770834",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(Arrays.asList(cli1));
		
		enderecoRepo.save(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 11:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.save(Arrays.asList(ped1,ped2));
		
		pagamentoRepo.save(Arrays.asList(pagto1,pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.save(Arrays.asList(ip1,ip2,ip3));
		
		
		
		
	}
}
