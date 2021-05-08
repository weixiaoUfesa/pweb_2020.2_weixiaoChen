package com.weixiao.AgroPopShop.AgroPopShop.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 6178098803459551807L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduto;
	public Set<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}
	public void setItemPedidos(Set<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
	private String nome;
	private String marca;
	private String altura;
	private String largura;
	private String profundidade;
	private String peso;
	private String preco;
	
	@OneToMany(targetEntity = ItemPedido.class,mappedBy = "produto",
			   fetch = FetchType.LAZY,cascade = CascadeType.ALL)

	private Set<ItemPedido> itemPedidos;
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
	public String getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
