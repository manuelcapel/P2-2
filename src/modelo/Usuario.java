package modelo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Clase correspondiente a la entidad de Usuario, que tiene los campos de
 * id, nombre, apellido y email.
 * @author M.I.Capel
 *
 */
@Entity
public class Usuario implements Serializable {
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE)
		private long id;
		private String nombre;
		private String apellido;
		
		// Email será también una clave primaria aunque no está reflejado en el código
		// No estará permitido modificar el email de un usuario y este será único.
		private String email;
		public Usuario(){	
		}
		public Usuario(String nombre, String apellido, String email){
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
		};
		public Usuario(Usuario us){
			this.nombre = us.nombre;
			this.apellido = us.apellido;
			this.email = us.email;
		}
		public void setId(long id){
			this.id = id;
		}
		public long getId(){
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		@Override
		public String toString(){
			return nombre + " " + apellido + " " + email;
		}
}
