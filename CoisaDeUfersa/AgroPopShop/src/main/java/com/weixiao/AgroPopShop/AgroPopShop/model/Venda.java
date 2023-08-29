package com.weixiao.AgroPopShop.AgroPopShop.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name="Vendas")
public class Venda implements Serializable {
	private static final long serialVersionUID = 6178098803459551807L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenda;
	

@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@PrimaryKeyJoinColumn(name = "idPedido", referencedColumnName = "idVenda")
	private Pedido pedido;


public long getIdVenda() {
	return idVenda;
}


public void setIdVenda(long idVenda) {
	this.idVenda = idVenda;
}


public Pedido getPedido() {
	return pedido;
}


public void setPedido(Pedido pedido) {
	this.pedido = pedido;
}

}
