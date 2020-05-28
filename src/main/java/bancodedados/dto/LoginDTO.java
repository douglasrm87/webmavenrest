package bancodedados.dto;

public class LoginDTO {
	private String mensagem;
	private boolean sucesso = true;
	public String getMensagem() {
		return this.mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isSucesso() {
		return this.sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	

}
