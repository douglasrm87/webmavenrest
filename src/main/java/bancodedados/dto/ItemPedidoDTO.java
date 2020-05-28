package bancodedados.dto;

public class ItemPedidoDTO {
	private int idItem;
	private int idPedido;
	private String estiloCerveja;
	private double valorCerveja;
	
	
	public ItemPedidoDTO(String estiloCerveja, double valorCerveja) {
		super();
		this.estiloCerveja = estiloCerveja;
		this.valorCerveja = valorCerveja;
	}
	public int getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdItem() {
		return this.idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getEstiloCerveja() {
		return this.estiloCerveja;
	}
	public void setEstiloCerveja(String estiloCerveja) {
		this.estiloCerveja = estiloCerveja;
	}
	public double getValorCerveja() {
		return this.valorCerveja;
	}
	public void setValorCerveja(double valorCerveja) {
		this.valorCerveja = valorCerveja;
	}
	
	
}
