package com.miweb.services;

import java.sql.SQLException;
import java.util.List;

import com.miweb.dao.NotaDAO;
import com.miweb.entity.Nota;

public class NotaService {
	NotaDAO notaDAO = new NotaDAO();
	
	public List<Nota> getAllNotas() {
		
		List<Nota> notas = notaDAO.getAllNotas();
		
		return notas;
	}

	public int addNota(Nota nota) throws ClassNotFoundException, SQLException {
		
		int count = notaDAO.addNota(nota);
		
		return count;
	}

	public int updateNota(Nota nota) throws ClassNotFoundException, SQLException {
		int count = notaDAO.updateNota(nota);
		
		return count;
	}

	public int deleteNota(int id) throws ClassNotFoundException, SQLException {
		
		int count = notaDAO.deleteNota(id);
		
		return count;
	}
}
