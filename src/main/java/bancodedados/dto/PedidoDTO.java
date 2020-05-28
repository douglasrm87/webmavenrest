package bancodedados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDTO {
	private int idPedido;// autoincrement
	private Date dataPedido = new Date();
	private long cpfCliente;//chave estrangeira
	private double valorTotalPedido = 0.0;
	private List<ItemPedidoDTO> listaItens = new ArrayList<>();
	private FormaPagamentoDTO pagamento;
	private String urlRecibo;
	
	
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
	public long getCpfCliente() {
		return this.cpfCliente;
	}
	public void setCpfCliente(long cpfCliente) {
		this.cpfCliente = cpfCliente;
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
	public FormaPagamentoDTO getPagamento() {
		return this.pagamento;
	}
	public void setPagamento(FormaPagamentoDTO pagamento) {
		this.pagamento = pagamento;
	}
	public PedidoDTO(long cpfCliente) {
		super();
		this.cpfCliente = cpfCliente;
	}
}
