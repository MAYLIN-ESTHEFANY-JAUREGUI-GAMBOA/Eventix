package pe.edu.vallegrande.eventix.model;

public class Usuario {
    private int id;
    private String usuario;
    private String contraseña;
    
    public Usuario() {
    }
    
    public Usuario(int id, String usuario, String contraseña) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
