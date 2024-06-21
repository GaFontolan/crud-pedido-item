package br.com.compagas.desafioCompagas.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compagas.desafioCompagas.entity.Item;
import br.com.compagas.desafioCompagas.entity.Pedido;
import br.com.compagas.desafioCompagas.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

	private static final String PESSOA_FISICA = "pessoa fisica";

	private static final String PESSOA_JURIDICA = "pessoa juridica";

	private static final String MSG_DELETE_SUCESS = "Delatado com sucesso";
	
	private static final String MSG_INSERIDO_SUCESS = "Inserido com sucesso";


	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ItemService itemService;

	public List<Pedido> buscarTodosPedidos() {
		List<Pedido> compra = repository.findAll();
		if (compra.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return compra;
	}
	
	public Optional<Pedido> buscarPedido(int id) {
		Optional<Pedido> compra = repository.findById(id);
		if (compra.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return compra;
	}

	@Transactional
	public String inserirPedido(Pedido pedido) {
		try {
			pedido.getItens().forEach(i -> {
				i.setPedido(pedido);
			});
			repository.save(validaCampos(pedido));
			return MSG_INSERIDO_SUCESS;
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}

	public ResponseEntity<?> atualizarPedido(Pedido pedido, int id) {
		try {
			buscarPedido(id);
			return ResponseEntity.ok(repository.save(pedido));
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}

	public String deletePedido(int id) {
		Pedido pedido = findById(id);
		if (pedido.getItens() != null && pedido.getItens().size() > 0) {
			itemService.deletarPeloIdPedido(id);
		}
		repository.deleteById(id);
		return MSG_DELETE_SUCESS;
	}

	private Pedido findById(int id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id informado não existe."));
	}

	private Pedido validaCampos(Pedido pedido) {
		Pedido pedidoValidado = validaValor(pedido);
		return pedidoValidado;
	}

	private Pedido validaValor(Pedido pedido) {
		String tipo = removerAcentos(pedido.getTipo());
		double valorTotal = 0.0;
		for (Item itens : pedido.getItens()) {
			valorTotal += itens.getValor();
		}

		if (PESSOA_FISICA.equalsIgnoreCase(tipo)) {
			pedido.setValorTotal(valorTotal * (5.2 / 100) + valorTotal);
		} else if (PESSOA_JURIDICA.equalsIgnoreCase(tipo)) {
			pedido.setValorTotal(1 + (valorTotal * (3.2 / 100)) + valorTotal);
		} else {
			throw new EntityNotFoundException();
		}
		return pedido;
	}

	private static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
