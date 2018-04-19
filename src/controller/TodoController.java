package controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import model.Todo;
import repo.Repository;

public class TodoController {
	private Repository<Integer, Todo> repo;

	public TodoController(Repository<Integer, Todo> repo) {
		this.repo = repo;
	}
	
	public void save(Todo todo) {
	        repo.save(todo);   
	}
	
	public void  update(Todo todo){
        repo.update(todo);
    }
	
	public Repository getRepo() {
        return repo;
    }
	
	public Set<Todo> findAll(){
        Iterable<Todo> todos=repo.findAll();
        return StreamSupport.stream(todos.spliterator(), false).collect(Collectors.toSet());
    }
	
	public Optional<Todo> findOne(String title) {
		return repo.findOne(title);
	}
}
