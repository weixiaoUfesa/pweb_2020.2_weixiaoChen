package com.weixiao.AgroPopShop.AgroPopShop.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 6178098803459551807L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	private boolean situacaoPagamento=false;
	private String cartao; 

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@OneToMany(targetEntity = ItemPedido.class,mappedBy = "pedido",
			   fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<ItemPedido> itemPedidos;

	@Column(nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataPedido;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "idVenda", referencedColumnName = "idPedido")
	private Venda venda;
    private String formaDePagamento;
	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(Set<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public boolean isSituacaoPagamento() {
		return situacaoPagamento;
	}

	public void setSituacaoPagamento(boolean situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	
	
	
   
	
}