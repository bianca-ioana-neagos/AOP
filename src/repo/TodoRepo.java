package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Todo;
import repo.Repository;

public class TodoRepo implements Repository<Integer,Todo>{
	private String url;
    private String username;
    private String password;

	public TodoRepo(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public Optional<Todo> findOne(String title) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select title,status from Todos where title=?")) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {            
                    boolean status = resultSet.getBoolean("Status");

                    Todo todo = new Todo(title,status);
                 
                    return Optional.of(todo);
                }
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} 
        return Optional.empty();
	}

	@Override
	public Iterable<Todo> findAll() {
		List<Todo> todos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select title,status from Todos")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                  
                    String title = resultSet.getString("Title");
                    boolean status = resultSet.getBoolean("Status");
                   
                    Todo todo = new Todo(title,status);

                    todos.add(todo);
                }
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} 

        return todos;
	}

	@Override
	public Optional<Todo> save(Todo entity) {
		if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
       
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into Todos (title,status) values (?,?)")) {
            statement.setString(1, entity.getTitle());
            statement.setBoolean(2, entity.isStatus());
         
            statement.executeUpdate();
 
            return Optional.empty();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(entity);
        }
	}

	@Override
	public Optional<Todo> update(Todo entity) {
		if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
    
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "update Todos set Status=? WHERE Title=?")) {

            statement.setBoolean(1, entity.isStatus());
            statement.setString(2, entity.getTitle());
            statement.executeUpdate();
            
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(entity);
        }
	}

}
