package com.javaplenomarcosabreu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Empregado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	@Size(min = 4, max = 25, message = "O nome deve ter entre 4 e 25 caracteres.")
	private String nome;

	@Column(nullable = false)
	@Size(min = 4, max = 30, message = "O endereço deve ter entre 4 e 30 caracteres.")
	private String endereco;

	@Column(nullable = false, unique = true)
	@Pattern(regexp = "^\\d{11}$", message = "Digite apenas os números do CPF.")
	private String cpf;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_departamento", referencedColumnName = "id")	
	private Departamento codDepartamento;
		
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Departamento getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(Departamento codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
