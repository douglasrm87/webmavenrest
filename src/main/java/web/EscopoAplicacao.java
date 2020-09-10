package web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bancodedados.dto.ClienteDTO;

@ApplicationScoped
@ManagedBean(name = "geral", eager = true)
public class EscopoAplicacao {
	
	public static List<ClienteDTO> listaClienteCartaoFidelidade = new ArrayList<>();
	
}
