package com.weixiao.AgroPopShop.AgroPopShop.model;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 6178098803459551807L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;

	private String nome;
	private String genero;
	private String endereco;
	private String eMail;
	private String CEP; 
	private String salarioBruto;
	
	@OneToMany(targetEntity = Pedido.class,mappedBy = "cliente",
			   fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	
	private Set<Pedido> pedidos;
	
	@OneToMany(targetEntity = Dependente.class,mappedBy = "cliente",
			   fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Dependente> dependetes;
	
	 
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}


	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getSalarioBruto() {
		return salarioBruto;
	}
	public void setSalarioBruto(String salarioBruto) {
		this.salarioBruto = salarioBruto;
	}


}
