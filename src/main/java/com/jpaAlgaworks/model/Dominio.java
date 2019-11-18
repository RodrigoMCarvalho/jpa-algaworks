package com.jpaAlgaworks.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Dominio {

	@Id
	private Integer id;

	private String nome;

	@OneToMany(mappedBy = "dominio")
	private List<Usuario> usuarios;
	
	

	public Dominio() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Dominio dominio = (Dominio) o;

		return id.equals(dominio.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}