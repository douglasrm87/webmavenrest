package bancodedados.dto;

public class ProdutoBebida {
	private String familia;
	private String estilo;
	private String sigla;
	private String descricaoCurta;
	private String descricaoLonga;
	private String descricaoMedia;
	private double valorLitro;
	private double abv;;
	private double ibu;
	private double ebc;

	public String getDescricaoMedia() {
		return this.descricaoMedia;
	}

	public void setDescricaoMedia(String descricaoMedia) {
		this.descricaoMedia = descricaoMedia;
	}

	public String getDescricaoCurta() {
		return this.descricaoCurta;
	}

	public void setDescricaoCurta(String descricaoCurta) {
		this.descricaoCurta = descricaoCurta;
	}

	public String getDescricaoLonga() {
		return this.descricaoLonga;
	}

	public void setDescricaoLonga(String descricaoLonga) {
		this.descricaoLonga = descricaoLonga;
	}

	public double getValorLitro() {
		return this.valorLitro;
	}

	public void setValorLitro(double valorLitro) {
		this.valorLitro = valorLitro;
	}

	public String getFamilia() {
		return this.familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getEstilo() {
		return this.estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
 

	public double getAbv() {
		return this.abv;
	}

	public void setAbv(double abv) {
		this.abv = abv;
	}

	public double getIbu() {
		return this.ibu;
	}

	public void setIbu(double ibu) {
		this.ibu = ibu;
	}

	public double getEbc() {
		return this.ebc;
	}

	public void setEbc(double ebc) {
		this.ebc = ebc;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ProdutoBebida(String familia, String estilo, String sigla, String descricaoCurta, double abv, double ibu,
			double ebc, String descricaoLonga, double valorLitro, String descricaoMedia) {
		super();
		this.familia = familia;
		this.estilo = estilo;
		this.sigla = sigla;
		this.descricaoCurta = descricaoCurta;
		this.abv = abv;
		this.ibu = ibu;
		this.ebc = ebc;
		this.descricaoMedia = descricaoMedia;
		this.descricaoLonga = descricaoLonga;
		this.valorLitro = valorLitro;
	}

	 

}
