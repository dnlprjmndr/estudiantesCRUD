package service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Estudiante;

public class ConexionMongo {
	private static final List<Estudiante> listaEstudiante = new ArrayList<Estudiante>();

	private static String server = null, user = null, pass = null, db = null, coll = null;
	private static int iPort = 0;
	private static MongoClient mongoClient = null;
	private static MongoDatabase database = null;
	private static MongoCollection<BasicDBObject> collection = null;

	public ConexionMongo() {
	}

	public static MongoCollection<BasicDBObject> ConnectMongo() {
		try {

			Properties prop = new Properties();
			prop.load(new FileReader("app.properties"));
	
			server = prop.getProperty("mongo.server");
			iPort = Integer.parseInt(prop.getProperty("mongo.port"));
			user = prop.getProperty("mongo.user");
			pass = prop.getProperty("mongo.pass");
			db = prop.getProperty("mongo.database");
			coll = prop.getProperty("mongo.collection");

			System.out.println(server + iPort + user + pass + db + coll);
		} catch (FileNotFoundException e) {
			e.getCause().getMessage();
		} catch (IOException e) {
			e.getCause().getMessage();
		}
		try {
			// Connect to Database
			mongoClient = new MongoClient(server, iPort);
			database = mongoClient.getDatabase(db);

			if (mongoClient != null) {
				// Create Collection
				System.out.println("Collection created successfully");
				collection = database.getCollection(coll, BasicDBObject.class);
			}
		} catch (Exception e) {
			e.getCause().getMessage();
		}

		return collection;
	}

	public Boolean closeConnect(MongoClient mongoClient) {

		try {
			mongoClient.close();
		} catch (Exception e) {
			e.getCause().getMessage();
			return false;
		}
		return true;

	}

	static {
		listaEstudiante.add(
				new Estudiante("Luis", "Andrade", "12-12-1980", "Ing. en Sistemas", "Cuarto", "landrade@gmail.com"));
		listaEstudiante
				.add(new Estudiante("Laura", "Vasquez", "02-11-1979", "Educación", "Segundo", "lvasquez@gmail.com"));
		listaEstudiante
				.add(new Estudiante("Pedro", "Toro", "22-10-1966", "Contabilidad", "Primero", "ptoro@gmail.com"));
		listaEstudiante.add(
				new Estudiante("Johana", "Salcedo", "11-11-1976", "Administraci�n", "Segundo", "jsalcedo@gmail.com"));
		listaEstudiante.add(new Estudiante("John", "Pilco", "18-08-1988", "Medicina", "Sexto", "jpilco@gmail.com"));
		listaEstudiante.add(new Estudiante("Samuel", "Pardo", "22-03-1985", "Educaci�n", "Primer", "spardo@gmail.com"));
	}

	public static List<Estudiante> getListaEstudiante() {
		return listaEstudiante;
	}

}
