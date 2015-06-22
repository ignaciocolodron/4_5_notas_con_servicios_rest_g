package com.miweb.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.miweb.entity.Nota;
import com.miweb.entity.ResultRS;
import com.miweb.services.NotaService;

@Path("/xml/nota")
public class NotasWebServices {
	
		
	@GET
	@Path("/getAllNotas")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Nota> getAllNotas() {
		
		NotaService notaService = new NotaService();
		
		List<Nota> listaNotas = notaService.getAllNotas();

		return listaNotas;

	}
	
	

	@GET
	@Path("/addNota/descripcion/{descripcion}/")
	//@Path("/{pin}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public ResultRS addNota( @PathParam("descripcion") String descripcion) {

		ResultRS resultRS = new ResultRS();
		resultRS.setResult(true);
		
		Nota nota = new Nota(descripcion);
		NotaService notaService = new NotaService();
		try {
			notaService.addNota(nota);
		} catch (Exception e) {
			resultRS.setResult(false);
			resultRS.setMsg(e.getMessage());
		}

		return resultRS;

	}
	
	@GET
	@Path("/updateNota/id/{id}/descripcion/{descripcion}/")
	//@Path("/{pin}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public ResultRS updateNota(@PathParam("id") int id, 
								@PathParam("descripcion") String descripcion) {

		ResultRS resultRS = new ResultRS();
		resultRS.setResult(true);
		
		Nota nota = new Nota(id, descripcion);

		NotaService notaService = new NotaService();
		try {
			notaService.updateNota(nota);
		} catch (Exception e) {
			resultRS.setResult(false);
			resultRS.setMsg(e.getMessage());
		}
		
		return resultRS ;

	}
	
	@GET
	@Path("/deleteNota/id/{id}")
	//@Path("/{pin}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public ResultRS deleteNota(@PathParam("id") int id) {

		ResultRS resultRS = new ResultRS();
		resultRS.setResult(true);
		
		NotaService notaService = new NotaService();
		try{
			notaService.deleteNota(id);
		} catch (Exception e) {
			resultRS.setResult(false);
			resultRS.setMsg(e.getMessage());
		}
		
		
		return resultRS;

	}
}
