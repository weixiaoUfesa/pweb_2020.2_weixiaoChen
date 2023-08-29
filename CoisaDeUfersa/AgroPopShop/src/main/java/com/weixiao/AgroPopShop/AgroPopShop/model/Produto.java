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

import net.bytebuddy.implementation.bytecode.Throw;

@Entity
@Table(name="Produtos")
public class Produto implements Serializable,Comparable{
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
	private float altura;
	private float largura;
	private float profundidade;
	private float peso;
	private float volume;
	
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	private float preco;
	
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
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public float getLargura() {
		return largura;
	}
	public void setLargura(float largura) {
		this.largura = largura;
	}
	public float getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(float profundidade) {
		this.profundidade = profundidade;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Produto) {
			Produto produto=(Produto)o;
			if(this.preco<produto.preco) {
				return 1;
			}else if (this.preco>produto.preco) {
				return -1;
			}else {
				return 0;
			}
		}
		throw new RuntimeException("preco errado");
		
	}
	

	
	

	
	
	
	
}
