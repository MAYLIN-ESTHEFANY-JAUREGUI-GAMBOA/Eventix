package pe.edu.vallegrande.eventix.dao;

import pe.edu.vallegrande.eventix.model.Usuario;
import pe.edu.vallegrande.eventix.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    public boolean autenticar(String usuario, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario);
            pstmt.setString(2, contraseña);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Usuario buscarPorUsuario(String usuario) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setContraseña(rs.getString("contraseña"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
