import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.miweb.entity.Nota;
import com.miweb.services.NotaService;


public class NotaServiceTest {

	@Test
	public void test() {
		NotaService notaService  = new NotaService();
		
		List<Nota> notas = new ArrayList<Nota>();

			notas = notaService.getAllNotas();
		
		
		
		for (Nota nota : notas) {
			System.out.println(nota.toString());
		}
		
		assertTrue("La lista obtenida con notaDAO.getAllNotas() debería tener algún elemento", notas.size() > 0);
	}

}
