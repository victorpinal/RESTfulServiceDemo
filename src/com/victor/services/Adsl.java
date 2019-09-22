package com.victor.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.victor.datos.Ip;
import com.victor.utils.mySQL;

@Path("/")
public class Adsl {

	@GET	
	@Path("ips")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ip> getIps() {
		
		List<Ip> datos = new ArrayList<>();
		
		try (ResultSet res = mySQL.get().getConnection().createStatement().executeQuery("SELECT * FROM ip")) {
            while (res.next()) {
                datos.add(new Ip(res.getInt("id"), res.getString("ip"), res.getString("name")));
            }
        } catch (Exception e) {
        }
		
		return datos;
	}
	
	@GET
	@Path("adsl/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdsl(@PathParam("id") int id) {
		List<JSONObject> resultado = new ArrayList<>();
		try (PreparedStatement stmt = mySQL.get().getConnection().prepareStatement("SELECT time,download,upload,attdownrate,attuprate FROM datos WHERE ip_id=? ORDER BY time DESC")) {

            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            
            while (res.next()) {
            	JSONObject datos = new JSONObject();
                datos.put("time",new SimpleDateFormat("dd/MM/yy HH:mm").format(res.getTimestamp("time")));
                datos.put("download",res.getInt("download"));
                datos.put("upload",res.getInt("upload"));
                datos.put("attdownrate",res.getInt("attdownrate"));
                datos.put("attuprate",res.getInt("attuprate"));
                resultado.add(datos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return Response.ok(resultado).build();
	}
	
	@GET
	@Path("film/rutas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRutas() {
		List<Ruta> resultado = new ArrayList<>();
		try (ResultSet res = mySQL.get().getConnection(mySQL.peliculas).createStatement().executeQuery("SELECT DISTINCT ruta FROM `film` WHERE ruta IS NOT NULL")) {
            
            while (res.next()) {
            	Ruta datos = new Ruta();
            	datos.ruta = res.getString("ruta");
                resultado.add(datos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return Response.ok(resultado).build();
	}
	
	private class Ruta {
		public String ruta;		
	}
	
}
