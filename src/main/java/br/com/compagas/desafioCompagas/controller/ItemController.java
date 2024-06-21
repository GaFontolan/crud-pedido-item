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

import br.com.compagas.desafioCompagas.entity.Item;
import br.com.compagas.desafioCompagas.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	private static final String MSG_ERRO_INCLUIR = "Não foi possível inserir ao BD, verifique se todos os campos estão corretos";

	private static final String MSG_ERRO_BUSCAR = "item não encontrado, id %s não existe na base";
	
	private static final String MSG_DELETE = "Não existe item com id %s para ser deletado";

	
	@Autowired
	private ItemService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPedidoById(@PathVariable("id") final int id) {
		try {
			return ResponseEntity.ok(service.buscarItem(id));
		}catch (RuntimeException e) {
			String msg = String.format(MSG_ERRO_BUSCAR, id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
	}
	
	@PostMapping
	public ResponseEntity<?> inserirPedido(@RequestBody Item item) {
		try {
			return ResponseEntity.ok(service.inserirItem(item));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERRO_INCLUIR);	
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Item item, @PathVariable int id) {
		try {
			item.setIdProduto(id);
			return service.atualiarItem(item, id);			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERRO_INCLUIR);		
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> detete(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteItem(id));
		}catch(RuntimeException e) {
			String msg = String.format(MSG_DELETE, id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}
}
