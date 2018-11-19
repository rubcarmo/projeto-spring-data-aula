package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("vieira@gmail.com");
		usuarioSpringData.setIdade(36);
		usuarioSpringData.setLogin("ruviera");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Rubens Vieira dos Santos");
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuarios cadastrados" + interfaceSpringDataUser.count());
		
		//System.out.println("Iniciou o Spring com sucesso");

	}
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		
		for(Telefone telefone: usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getTipo() + " " + telefone.getNumero());
		}
		
	}

	@Test
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for(UsuarioSpringData usuarioSpringData: lista) {
		
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println("------------------------------------------------------------------------------");
		}
	}

	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		UsuarioSpringData data = usuarioSpringData.get(); 
		
		data.setNome("Wagner Carmo de Jesus");
		interfaceSpringDataUser.save(data);
		
	}
	
	@Test
	public void testeDelete() {
		
		interfaceSpringDataUser.deleteById(3L);
		
	}
	
	@Test
	public void testeDeletePorNome() {
		
		interfaceSpringDataUser.deletePorNome("Rubens Vieira dos Santos");
		
	}


	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Rubens");
		
		for(UsuarioSpringData usuarioSpringData: list) {
		
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
		}
	}

	
	@Test
	public void testeConsultaNomeParam() {
		
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Rubens Carmo de Jesus");
		
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println("----------------------------------------------------------------------");
		
	}

	@Test
	public void testeUpdateEmailPorNome() {
		
		interfaceSpringDataUser.updateEmailPorNome("xxxxxxxx@xxx.com.br", "Viviane Carmo de Jesus");
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		UsuarioSpringData data = usuarioSpringData.get(); 
		
		Telefone telefone = new Telefone();
		telefone.setNumero("36524271");
		telefone.setTipo("residencial");
		telefone.setUsuarioSpringData(data);
		
		interfaceTelefone.save(telefone);		
		
		System.out.println("Telefones cadastrados" + interfaceTelefone.count());
				
	}

}
