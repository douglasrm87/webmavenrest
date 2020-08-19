package bancodedados.dto;

public class ItemPedidoDTO extends PedidoDTO {
	private int idItem;

	private String estiloCerveja;
	private double valorCerveja;

	public ItemPedidoDTO(String estiloCerveja, double valorCerveja, String telefone  ) {
		super(telefone );
		this.estiloCerveja = estiloCerveja;
		this.valorCerveja = valorCerveja;
		
	}
	public ItemPedidoDTO(String estiloCerveja) {
		this.estiloCerveja = estiloCerveja;
	}

	public ItemPedidoDTO() {
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
