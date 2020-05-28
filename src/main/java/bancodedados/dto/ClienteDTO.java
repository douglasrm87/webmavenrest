package bancodedados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDTO {
	private long id;
	private long cpfCliente;
	private String nomeCliente;
	private String permiteEmailSMS;
	private String email;
	private EnderecoDTO endereco;
	private String telefone;
	private String cep;
	private List<CartaoFidelidadeDTO> cartaoCliente = new ArrayList<>();
	private List<BonusCartaoFidelidadeDTO> bonusCartaoCliente = new ArrayList<>();
	private PedidoDTO pedido;
	private Date dtRegistro = new Date();
	private List<PedidoDTO> listaPedido = new ArrayList<>();

	public List<PedidoDTO> getListaPedido() {
		return this.listaPedido;
	}

	public void setListaPedido(List<PedidoDTO> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public void adicionarCartaoFidelidade(CartaoFidelidadeDTO cartaoFid) {
		this.cartaoCliente.add(cartaoFid);
		if (this.cartaoCliente.size() == 10) {
			this.bonusCartaoCliente.add(new BonusCartaoFidelidadeDTO(new Date(), this.cpfCliente));
			this.cartaoCliente = new ArrayList<>();
		}
	}

	public int consumirBonusCartaoFidelidade() {
		int bonus = this.bonusCartaoCliente.size();
		this.bonusCartaoCliente = new ArrayList<>();
		return bonus;
	}

	public int obterBonusCartaoFidelidade() {
		return this.bonusCartaoCliente.size();
	}

	public int obterCartaoFidelidade() {
		return this.cartaoCliente.size();
	}
	public ClienteDTO(String nomeCliente, String telefone) {
		super();
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
	}

	public ClienteDTO(String nomeCliente, String telefone, long cpfCliente, String cep, EnderecoDTO endereco) {
		super();
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.endereco = endereco;
		setCep(cep);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCpfCliente() {
		return this.cpfCliente;
	}

	public void setCpfCliente(long cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getPermiteEmailSMS() {
		return this.permiteEmailSMS;
	}

	public void setPermiteEmailSMS(String permiteEmailSMS) {
		this.permiteEmailSMS = permiteEmailSMS;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoDTO getEndereco() {
		return this.endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

 	public Date getDtRegistro() {
		return this.dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
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

	public PedidoDTO getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		String strCEP = cep.substring(0, 5);
		System.out.println("CEP substring: " + strCEP);
		int cepInt = 0;
		try {
			cepInt = Integer.parseInt(strCEP);
			if (cepInt < 80000 || cepInt > 82999) {
				throw new IllegalArgumentException(CentralMensagensBrewField.CEP_NAO_CURITIBA);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(CentralMensagensBrewField.CEP_FORMATO_INVALIDO);
		}
		this.cep = cep;
	}

}
