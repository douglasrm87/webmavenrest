package bancodedados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDTO {
	private int idPedido;// autoincrement

	private String telefone;// chave estrangeira
	private double valorTotalPedido = 0.0;
	private int estadoPedido; // 0 aberto 1 fechado
	private Date dataPedido = new Date();

	private String urlRecibo; // pode ser nulo no banco
	private List<ItemPedidoDTO> listaItens = new ArrayList<>();
	private FormasPagamento pagamento;
	private ClienteDTO cliente;

	public PedidoDTO(String telefone, double valorTotalPedido, int estadoPedido) {
		super();
		this.telefone = telefone;
		this.valorTotalPedido = valorTotalPedido;
		this.estadoPedido = estadoPedido;
		
	}

	public PedidoDTO(String telefone, int estadoPedido ) {
		super();
		this.telefone = telefone;
		this.estadoPedido = estadoPedido;
 
	}
	public PedidoDTO() {}
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getEstadoPedido() {
		return this.estadoPedido;
	}

	public void setEstadoPedido(int estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public String getUrlRecibo() {
		return this.urlRecibo;
	}

	public void setUrlRecibo(String urlRecibo) {
		this.urlRecibo = urlRecibo;
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return this.dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public double getValorTotalPedido() {
		return this.valorTotalPedido;
	}

	public void setValorTotalPedido(double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public List<ItemPedidoDTO> getListaItens() {
		return this.listaItens;
	}

	public void setListaItens(List<ItemPedidoDTO> listaItens) {
		this.listaItens = listaItens;
	}

	public FormasPagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(FormasPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public PedidoDTO(String telefone) {
		super();
		this.telefone = telefone;
	}
}
