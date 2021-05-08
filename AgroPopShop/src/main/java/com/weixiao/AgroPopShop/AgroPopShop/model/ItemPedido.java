package com.weixiao.AgroPopShop.AgroPopShop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ItemsPedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 6178098803459551807L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idItemPedido;
	
	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;
	
	private int quantidade;
	private String valorUnidade;
	public long getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getValorUnidade() {
		return valorUnidade;
	}
	public void setValorUnidade(String valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
	
	
	}
	