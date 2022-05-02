package dao;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConfigDB;
import domain.Livro;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDao {
    
    public void insert(Livro livro) throws SQLException{

        String sql = "INSERT INTO Livro(titulo, isbn, edicao, autor,descricao)" +
            "values (?, ?, ?, ?, ?)";


        try(
            //Abrir conexão com o banco
            Connection connection = ConfigDB.getConnection();

            //Criar a partir da da conexão, a sessão

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){

            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getIsbn());
            statement.setInt(3, livro.getEdicao());
            statement.setString(4, livro.getAutor());
            statement.setString(5, livro.getDescricao());

            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            livro.setId(resultSet.getLong("id"));
            
        }catch(SQLException e){
            e.printStackTrace();
        }
       
    }


    public List<Livro> findAll() {

        String sql = "SELECT id, titulo,isbn, edicao, autor, descricao FROM livro ";
        List<Livro> livros = null;

        try (
                Connection connection = ConfigDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            livros = new ArrayList<>();
            Livro livro;

            while (resultSet.next()) {
                livro = obterLivroPorResultSet(resultSet);
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro findByTitle(String titulo){

        String sql = "SELECT id, titulo, isbn, edicao, autor, descricao FROM livro WHERE titulo like ? ";
        Livro livro = null;

        try (
                Connection connection = ConfigDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
    
            statement.setString(1, titulo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                livro = obterLivroPorResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }



    public void delete(Livro livro) {
        String sql = "DELETE FROM livro WHERE id = ? ";

        try (
                Connection connection = ConfigDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setLong(1, livro.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    

    public void update(Livro livro) {
        String sql = "UPDATE livro SET titulo =?, isbn=?, edicao=?, autor=?,descricao=? where id=?";
        try (
                Connection connection = ConfigDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
                
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getIsbn());
            statement.setInt(3, livro.getEdicao());
            statement.setString(4, livro.getAutor());
            statement.setString(5, livro.getDescricao());
            statement.setLong(6, livro.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Livro obterLivroPorResultSet(ResultSet resultSet) throws SQLException {
        
        Livro livro = new Livro();

        livro.setTitulo(resultSet.getString("titulo"));
        livro.setIsbn(resultSet.getString("isbn"));
        livro.setEdicao(resultSet.getInt("edicao"));
        livro.setAutor(resultSet.getString("autor"));
        livro.setDescricao(resultSet.getString("descricao"));
        livro.setId(resultSet.getLong("id"));

        return livro;
    }


}
