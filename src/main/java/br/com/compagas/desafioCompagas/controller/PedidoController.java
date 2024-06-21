package br.com.compagas.desafioCompagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compagas.desafioCompagas.entity.Pedido;
import br.com.compagas.desafioCompagas.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	private static final String MSG_ERRO_INCLUIR = "Não foi possível inserir ao BD, verifique se todos os campos estão corretos";
	
	private static final String MSG_ERRO_BUSCAR = "Pedido não encontrado, id %s não existe na base";
	
	private static final String MSG_ERRO_BUSCAR_TODOS = "Não existe nenhum pedido na base";
	
	private static final String MSG_DELETE = "Não existe pedido com id %s para ser deletado";

	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosPedidos() {
		try {
			return ResponseEntity.ok(service.buscarTodosPedidos());
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERRO_BUSCAR_TODOS);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPedidoById(@PathVariable("id") final int id) {
		try {
			return ResponseEntity.ok(service.buscarPedido(id));
		}catch (RuntimeException e) {
			String msg = String.format(MSG_ERRO_BUSCAR, id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
	}
	
	@PostMapping
	public ResponseEntity<?> inserirPedido(@RequestBody Pedido pedido) {
		try {
			return ResponseEntity.ok(service.inserirPedido(pedido));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERRO_INCLUIR);	
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Pedido pedido, @PathVariable int id) {
		try {
			pedido.setId(id);
			return service.atualizarPedido(pedido, id);			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERRO_INCLUIR);		
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> detete(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deletePedido(id));
		}catch(RuntimeException e) {
			String msg = String.format(MSG_DELETE, id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}
}
