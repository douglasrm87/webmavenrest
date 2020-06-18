package bancodedados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bancodedados.PostgreSQLJDBCClienteDML;

public class ClienteDTO {

	private long id;// serial
	private long idEmpresa = CentralMensagensBrewField.ID_BREW_FIELD;
	private long cpfCliente = CentralMensagensBrewField.CPF_PADRAO;
	private String permiteEmailSMS = "N";
	private String email = "teste@teste.com";
	private Date dtRegistro = new Date();

	private String nomeCliente;
	private String telefone;
	private Long idUsuarioTelegram;

	private EnderecoDTO endereco;
	private BonusCartaoFidelidadeDTO bonus;
	private PedidoDTO pedido;
	private List<PedidoDTO> listaPedido = new ArrayList<>();

	public BonusCartaoFidelidadeDTO getBonus() {
		return this.bonus;
	}

	public void setBonus(BonusCartaoFidelidadeDTO bonus) {
		this.bonus = bonus;
	}

	public List<PedidoDTO> getListaPedido() {
		return this.listaPedido;
	}

	public void setListaPedido(List<PedidoDTO> listaPedido) {
		this.listaPedido = listaPedido;
	}

//	public void adicionarCartaoFidelidade(CartaoFidelidadeDTO cartaoFid) {
//		this.cartaoCliente.add(cartaoFid);
//		if (this.cartaoCliente.size() == 10) {
//			this.bonusCartaoCliente.add(new BonusCartaoFidelidadeDTO(new Date(), this.cpfCliente));
//			this.cartaoCliente = new ArrayList<>();
//		}
//	}
//
//	public int consumirBonusCartaoFidelidade() {
//		int bonus = this.bonusCartaoCliente.size();
//		this.bonusCartaoCliente = new ArrayList<>();
//		return bonus;
//	}

//	public int obterBonusCartaoFidelidade() {
//		return this.bonusCartaoCliente.size();
//	}
//
//	public int obterCartaoFidelidade() {
//		return this.cartaoCliente.size();
//	}

	// inserir o telefone e o cliente apenas.
	public ClienteDTO(String nomeCliente, String telefone, long idUsuarioTelegram) {
		super();
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.idUsuarioTelegram = idUsuarioTelegram;
		PostgreSQLJDBCClienteDML cadastrarCli = new PostgreSQLJDBCClienteDML();
		cadastrarCli.cadastrarClienteDTO(this);
	}

	public ClienteDTO(String nomeCliente, String telefone, long cpfCliente, EnderecoDTO endereco) {
		super();
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.endereco = endereco;
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

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public ClienteDTO(String telefone) {
		super();
		this.telefone = telefone;
	}

	public PedidoDTO getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	public Long getIdUsuarioTelegram() {
		return this.idUsuarioTelegram;
	}

	public void setIdUsuarioTelegram(Long idUsuarioTelegram) {
		this.idUsuarioTelegram = idUsuarioTelegram;
	}

}
