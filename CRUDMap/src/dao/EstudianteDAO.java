package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import service.ConexionMongo;
import model.Estudiante;

/*
 * @autor: Daniel 
 * @web: 
 */

public class EstudianteDAO {

	private List<Estudiante> listaEstudiantes;
	private ConexionMongo conexion = null;

	public List<Estudiante> obtenerEstudiantes() {
		return listaEstudiantes;
	}

	public void saveMongo(Estudiante estudiante) {

		conexion = new ConexionMongo();
		MongoCollection<BasicDBObject> collection = ConexionMongo.ConnectMongo();

		estudiante = new Estudiante("daniel", "the best", "Paré", "11-04-1988", "96589652", "dd@ff.es");
		// Crea los objectos básicos
		// BasicDBObject(Telefono, fecha de inserción)
		BasicDBObject document = new BasicDBObject(estudiante.getTelefono(), new Date());
			document.append("nombre", estudiante.getNombre());
			document.append("alias", estudiante.getAlias());
			document.append("apellidos", estudiante.getApellidos());
			document.append("telefono", estudiante.getTelefono());
			document.append("email", estudiante.getEmail());
			document.append("nacimiento", estudiante.getFechaNacimiento());

			BasicDBObject address = new BasicDBObject();
				address.append("tipVia", "calle");
				address.append("calle", "balmes");
				address.append("num", 25);
				address.append("cdp", "28080");
				address.append("ciudad", "Madrid");

		document.put("address", address);

		// Insertar tablas
		collection.insertOne(document);

	}

	public List<Estudiante> obtenerEstudiantesMongo() {
		listaEstudiantes = new ArrayList<Estudiante>();
		FindIterable<BasicDBObject> listEstud = ConexionMongo.ConnectMongo().find();
		for (BasicDBObject doc : listEstud) {
			System.out.println(doc.getString("_id"));
			Estudiante estudiante = new Estudiante(doc.getString("nombre"),
								 				   doc.getString("alias"),
								 				   doc.getString("apellidos"),
								 				   doc.getString("telefono"),
								 				   doc.getString("email"),
								 				   doc.getString("nacimiento"));
			listaEstudiantes.add(estudiante);
		}

		return listaEstudiantes;
	}

	public List<Estudiante> buscarPorNombre(String nombre) {
		Comparator<Estudiante> groupByComparator = Comparator.comparing(Estudiante::getNombre)
				.thenComparing(Estudiante::getApellidos);
		List<Estudiante> result = listaEstudiantes.stream()
				.filter(e -> e.getNombre().equalsIgnoreCase(nombre) || e.getApellidos().equalsIgnoreCase(nombre))
				.sorted(groupByComparator).collect(Collectors.toList());
		return result;
	}

	public Estudiante obtenerPorId(int id) throws Exception {
		Optional<Estudiante> match = listaEstudiantes.stream().filter(e -> e.getId() == id).findFirst();

		if (match.isPresent()) {
			return match.get();
		} else {
			throw new Exception("El estudiante con ID: " + id + " no fue econtrado");
		}
	}

	public long guardarEstudiante(Estudiante estudiante) {
		listaEstudiantes.add(estudiante);
		return estudiante.getId();
	}

	public boolean actualizarEstudiante(Estudiante estudiante) {
		int idActualizar = 0;
		Optional<Estudiante> estudianteEncontrado = listaEstudiantes.stream()
				.filter(c -> c.getId() == estudiante.getId()).findFirst();
		if (estudianteEncontrado.isPresent()) {
			idActualizar = listaEstudiantes.indexOf(estudianteEncontrado.get());
			listaEstudiantes.set(idActualizar, estudiante);
			return true;
		} else {
			return false;
		}
	}

	public boolean eliminarEstudiante(long id) {
		Predicate<Estudiante> estudiante = e -> e.getId() == id;
		if (listaEstudiantes.removeIf(estudiante)) {
			return true;
		} else {
			return false;
		}
	}

}