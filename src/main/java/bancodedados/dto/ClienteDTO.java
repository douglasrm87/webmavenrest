package bancodedados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDTO {
	private int id;
	private int cpfCliente;
	private String nomeCliente;
	private List<CartaoFidelidadeDTO> cartaoCliente = new ArrayList<>();
	private Date dtRegistro = new Date();
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpfCliente() {
		return this.cpfCliente;
	}

	public void setCpfCliente(int cpfCliente) {
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

	public ClienteDTO() {
		super();

	}

	public ClienteDTO(int cpfCliente, String nomeCliente) {
		super();
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
	}

	public ClienteDTO(int cpfCliente) {
		super();
		this.cpfCliente = cpfCliente;
	}

	public Date getDtRegistro() {
		return this.dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

 

}
