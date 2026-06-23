package pe.edu.vallegrande.eventix.dao;

import pe.edu.vallegrande.eventix.model.Evento;
import pe.edu.vallegrande.eventix.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    
    public boolean insertar(Evento evento) {
        String sql = "INSERT INTO eventos (nombreEvento, tipoEvento, modalidad, servicios, fechaEvento, costo, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, evento.getNombreEvento());
            pstmt.setString(2, evento.getTipoEvento());
            pstmt.setString(3, evento.getModalidad());
            pstmt.setString(4, evento.getServicios());
            pstmt.setDate(5, Date.valueOf(evento.getFechaEvento()));
            pstmt.setBigDecimal(6, evento.getCosto());
            pstmt.setString(7, evento.getEstado());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizar(Evento evento) {
        String sql = "UPDATE eventos SET nombreEvento = ?, tipoEvento = ?, modalidad = ?, servicios = ?, fechaEvento = ?, costo = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, evento.getNombreEvento());
            pstmt.setString(2, evento.getTipoEvento());
            pstmt.setString(3, evento.getModalidad());
            pstmt.setString(4, evento.getServicios());
            pstmt.setDate(5, Date.valueOf(evento.getFechaEvento()));
            pstmt.setBigDecimal(6, evento.getCosto());
            pstmt.setString(7, evento.getEstado());
            pstmt.setInt(8, evento.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarLogico(int id) {
        String sql = "UPDATE eventos SET estado = 'INACTIVO' WHERE id = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Evento> listarTodos() {
        String sql = "SELECT * FROM eventos WHERE estado = 'ACTIVO' ORDER BY fechaEvento DESC";
        List<Evento> eventos = new ArrayList<>();
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getInt("id"));
                evento.setNombreEvento(rs.getString("nombreEvento"));
                evento.setTipoEvento(rs.getString("tipoEvento"));
                evento.setModalidad(rs.getString("modalidad"));
                evento.setServicios(rs.getString("servicios"));
                evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
                evento.setCosto(rs.getBigDecimal("costo"));
                evento.setEstado(rs.getString("estado"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
    
    public Evento buscarPorId(int id) {
        String sql = "SELECT * FROM eventos WHERE id = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNombreEvento(rs.getString("nombreEvento"));
                    evento.setTipoEvento(rs.getString("tipoEvento"));
                    evento.setModalidad(rs.getString("modalidad"));
                    evento.setServicios(rs.getString("servicios"));
                    evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
                    evento.setCosto(rs.getBigDecimal("costo"));
                    evento.setEstado(rs.getString("estado"));
                    return evento;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Evento> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM eventos WHERE nombreEvento LIKE ? AND estado = 'ACTIVO' ORDER BY fechaEvento DESC";
        List<Evento> eventos = new ArrayList<>();
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNombreEvento(rs.getString("nombreEvento"));
                    evento.setTipoEvento(rs.getString("tipoEvento"));
                    evento.setModalidad(rs.getString("modalidad"));
                    evento.setServicios(rs.getString("servicios"));
                    evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
                    evento.setCosto(rs.getBigDecimal("costo"));
                    evento.setEstado(rs.getString("estado"));
                    eventos.add(evento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
}
