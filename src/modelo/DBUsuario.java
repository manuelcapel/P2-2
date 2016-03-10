package modelo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Usuario;

/**
 * Clase correspondiente a la base de datos de usuarios con las funciones de
 * inserción, actualización, borrado, listado y comprobación de usuarios.
 * @author M.I.Capel
 *
 */
public class DBUsuario {
	private static final String PERSISTENCE_UNIT_NAME = "usuario";
	private static EntityManagerFactory factoria;
	
	/**
	 * Función que inserta nuevos usuarios en la base de datos
	 * 
	 * @param usuario 
	 */
	public static void insertar(Usuario usuario){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();		
	}
	/**
	 * Función que actualiza el nombre y apellido de un usuario en la base de datos
	 * a partir de la nueva información aportada.
	 * Se mantiene tanto el ID como el email del usuario.
	 * 
	 * @param usuario
	 */
	public static void actualizar(Usuario usuario){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Usuario aux = em.find(Usuario.class, usuario.getId());
		
		em.getTransaction().begin();
		aux.setNombre(usuario.getNombre());
		aux.setApellido(usuario.getApellido());
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * Función que elimina a un usuario de la base de datos.
	 * 
	 * @param usuario
	 */
	public static void eliminar(Usuario usuario){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Usuario aux = em.find(Usuario.class, usuario.getId());
		
		em.getTransaction().begin();
		em.remove(aux);
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * Función que busca un usuario en la base de datos a partir del email del mismo.
	 * 
	 * @param email
	 * @return Usuario buscado
	 */
	public static Usuario seleccionarUsuario(String email){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email='"+email+"'");
		if(!q.getResultList().isEmpty())
			return (Usuario) q.getSingleResult();
		else
			return null;
		
	}
	/**
	 * Función que comprueba si está ya registrado un usuario con un determinado email.
	 * 
	 * @param email
	 * @return 
	 */
	public static boolean existeEmail(String email){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email='"+email+"'");
				
		return !q.getResultList().isEmpty();
		
	}
	/**
	 * Función que devuelve la lista de usuarios contenida en la base de datos.
	 * 
	 * @return Lista de usuarios
	 */
	public static List<Usuario> listarUsuarios(){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u");
		return (List<Usuario>) q.getResultList();
	}
}
