package br.com.compagas.desafioCompagas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compagas.desafioCompagas.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
