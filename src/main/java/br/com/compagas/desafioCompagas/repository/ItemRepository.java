package br.com.compagas.desafioCompagas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compagas.desafioCompagas.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	void deleteByPedido_id(int id);

	void deleteByIdProduto(int id);
}
