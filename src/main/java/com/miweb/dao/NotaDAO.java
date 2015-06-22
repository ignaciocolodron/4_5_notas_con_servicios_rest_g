package com.miweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miweb.entity.Nota;
import com.miweb.entity.Nota;

public class NotaDAO {
	//*************** ATRIBUTOS *************//	

		//*************** METODOS *************//	
		
		
		public List<Nota> getAllNotas() {
			
			//Crear la lista de nota
			List<Nota> listaNotas = new ArrayList<Nota>();
			
			DataBaseHelper helper = new DataBaseHelper();
			ResultSet rs = helper.seleccionarRegistros("SELECT * FROM t_nota");
					
			try {
				while(rs.next()){		
					String id = rs.getString("id");
					String descripcion = rs.getString("descripcion");

					Nota nota = new Nota(Integer.valueOf(id), descripcion);

					listaNotas.add(nota);			
				}
			
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listaNotas;
		}
		
		public int addNota(Nota nota) throws ClassNotFoundException, SQLException{
			
			int count = 0;
			
			String insertSentence = "INSERT INTO t_nota (descripcion) VALUES('%s')";
			insertSentence = String.format(insertSentence, 
											nota.getDescripcion());
			
			DataBaseHelper helper = new DataBaseHelper();
			count = helper.modificarRegistro(insertSentence);			
			
			return count;		
		}

		public int updateNota(Nota nota) throws ClassNotFoundException, SQLException{
			
			int count = 0;
			//Preparamos la sentencia a ejecutar
			String updateSentence = "UPDATE t_nota SET descripcion='%s' WHERE id='%s'";
			updateSentence = String.format(updateSentence,  
											nota.getDescripcion(),  
											nota.getId());
			
			//Ejecuta la sentencia con la función de DataBaseHelper
			DataBaseHelper helper = new DataBaseHelper();
			count = helper.modificarRegistro(updateSentence);			

			return count;
		}
			
		public int deleteNota(int id) throws ClassNotFoundException, SQLException{
			
			int count = 0;
			//Preparamos la sentencia a ejecutar
			String updateSentence = "DELETE FROM t_nota WHERE id='%s'";
			updateSentence = String.format(updateSentence,  id);
			
			//Ejecuta la sentencia con la función de DataBaseHelper
			DataBaseHelper helper = new DataBaseHelper();
			count = helper.modificarRegistro(updateSentence);			

			return count;
		}	
}
