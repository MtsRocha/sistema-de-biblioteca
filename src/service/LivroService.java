package service;

import java.sql.SQLException;
import java.util.List;

import dao.LivroDao;
import domain.Livro;

public class LivroService {


    private LivroDao livroDao;
    //private AutorService autorService;

    public LivroService() {
        livroDao = new LivroDao();
    }

    public void insert (Livro livro) throws SQLException{

        livroDao.insert(livro);

        //autorService.insertBatch(livro.getAutores());
    }

    public Livro findByTitle (String titulo) throws SQLException{

        return livroDao.findByTitle(titulo);

        //autorService.insertBatch(livro.getAutores());
    }

    public List<Livro> findAll() {

        return livroDao.findAll();
    }

    public void delete(Livro livro) {
        
        livroDao.delete(livro);
    }

    public void update(Livro livro) {
        
        livroDao.update(livro);
    }
}
