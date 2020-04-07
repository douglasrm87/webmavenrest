package postgree.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDTO {
	private String cpfCliente;
	private String nomeCliente;
	private List<CartaoFidelidadeDTO> cartaoCliente = new ArrayList<>();
	private Date dtCadastro = new Date();

	public String getCpfCliente() {
		return this.cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public List<CartaoFidelidadeDTO> getCartaoCliente() {
		return this.cartaoCliente;
	}

	public void setCartaoCliente(List<CartaoFidelidadeDTO> cartaoCliente) {
		this.cartaoCliente = cartaoCliente;
	}

	public ClienteDTO(String cpfCliente, String nomeCliente) {
		super();
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
	}

	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

}
