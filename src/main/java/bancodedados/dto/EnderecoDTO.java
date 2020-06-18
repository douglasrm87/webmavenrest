package bancodedados.dto;

import bancodedados.PostgreSQLJDBCClienteDML;

//http://cep.republicavirtual.com.br/web_cep.php?cep=17514520&formato=xml
//http://cep.republicavirtual.com.br/web_cep.php?cep=80050350&formato=txt
public class EnderecoDTO {
	private int resultado;// transient
	private String mensagem;// transient
	private String resultado_txt;// transient
	private String uf;
	private String cidade;
	private String bairro;
	private String tipo_logradouro;
	private String logradouro;
	private String numero;
	private String cep;
	private String telefone;
	private String complemento;// transiente
	private ClienteDTO cliente;

	public String getCep() {
		return this.cep;
	}

	public EnderecoDTO() {

	}

	public EnderecoDTO(String compl, ClienteDTO cliente) throws Exception {
		this.complemento = compl;
		this.cliente = cliente;
		processarCEP();
		PostgreSQLJDBCClienteDML cadend = new PostgreSQLJDBCClienteDML();
		cadend.inserirEnderecoDTO(cliente, this);
	}

	public EnderecoDTO(String telefone) {
		this.telefone = telefone;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getResultado() {
		return this.resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public String getResultado_txt() {
		return this.resultado_txt;
	}

	public void setResultado_txt(String resultado_txt) {
		this.resultado_txt = resultado_txt;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipo_logradouro() {
		return this.tipo_logradouro;
	}

	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EnderecoDTO(String cep, String uf, String cidade, String bairro, String tipo_logradouro, String logradouro,
			String numero) {
		super();
		this.cep = cep;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.tipo_logradouro = tipo_logradouro;
		this.logradouro = logradouro;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return this.cep + " " + this.tipo_logradouro + " " + this.logradouro + ", " + this.numero + " " + this.bairro
				+ " " + this.cidade + "-" + this.uf;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	private void validarIntervaloCep(String cepLocal) throws Exception {
		String strCEP = cepLocal.substring(0, 5);
		System.out.println("CEP substring: " + strCEP);
		int cepInt = 0;
		try {
			cepInt = Integer.parseInt(strCEP);
			if (cepInt < 80000 || cepInt > 82999) {
				throw new Exception(CentralMensagensBrewField.CEP_NAO_CURITIBA);
			}
		} catch (Exception e) {
			throw new Exception(CentralMensagensBrewField.CEP_FORMATO_INVALIDO);
		}
		this.cep = cepLocal;
	}

	private void processarCEP() throws Exception {
		String strVet[] = this.complemento.split(",");
		if (strVet.length == 2) {
			strVet[0] = strVet[0].replaceAll(" ", "");
			if (strVet[0].contains("-")) {
				strVet[0] = strVet[0].replaceAll("-", "");
			}
			EnderecoXML endXML = new EnderecoXML();
			EnderecoDTO endDTO = endXML.carregarDadosArqXML(strVet[0]);
			if (endDTO.getMensagem() != null && endDTO.getMensagem().length() > 0) {
				throw new Exception(getMensagem());
			} else {
				endDTO.setNumero(strVet[1]);
				endDTO.validarIntervaloCep(strVet[0]);
				this.uf = endDTO.getUf();
				this.cidade = endDTO.getCidade();
				this.bairro = endDTO.getBairro();
				this.tipo_logradouro = endDTO.getTipo_logradouro();
				this.logradouro = endDTO.getLogradouro();
				this.numero = endDTO.getNumero();
				this.cep = strVet[0];
				this.telefone = this.cliente.getTelefone();
			}
		} else {
			throw new Exception(CentralMensagensBrewField.FORMATO_INCORRETO);
		}
	}
}
