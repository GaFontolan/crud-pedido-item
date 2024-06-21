package br.com.compagas.desafioCompagas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compagas.desafioCompagas.entity.Item;
import br.com.compagas.desafioCompagas.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

	private static final String MSG_INSERIDO_SUCESS = "Inserido com sucesso";
	
	private static final String MSG_DELETE_SUCESS = "Delatado com sucesso";
	
	private static final String MSG_DELETE_ERRO = "Erro ao deletar";
	
	
	@Autowired
	private ItemRepository repository;
	
	public Optional<Item> buscarItem(int id) {
		Optional<Item> item = repository.findById(id);
		if (item.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return item;
	}
	
	public String inserirItem(Item item) {
		try {
			repository.save(item);
			return MSG_INSERIDO_SUCESS;
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}
	
	public ResponseEntity<?> atualiarItem(Item item, int id) {
		try {
			buscarItem(id);
			return ResponseEntity.ok(repository.save(item));
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}
	
	@Transactional
	public String deleteItem(int id) {
		Optional<Item> item = repository.findById(id);
		if(!item.isEmpty()) {
			repository.deleteById(id);
			return MSG_DELETE_SUCESS;			
		}
		return MSG_DELETE_ERRO;
	}
	
	@Transactional
	public void deletarPeloIdPedido(int id) {
		repository.deleteByPedido_id(id);
	}
}
