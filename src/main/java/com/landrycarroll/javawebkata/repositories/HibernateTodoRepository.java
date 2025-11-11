package com.landrycarroll.javawebkata.repositories;

import com.landrycarroll.javawebkata.config.Hibernate;
import com.landrycarroll.javawebkata.entities.Todo;
import com.landrycarroll.javawebkata.exceptions.TodoExceptions;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.UUID;

public class HibernateTodoRepository implements TodoRepository {
    private final SessionFactory sessionFactory;

    public HibernateTodoRepository() {
        this.sessionFactory = Hibernate.getSessionFactory();
    }

    @Override
    public Todo addTodo(Todo todo) {
        try (Session session = this.sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(todo);
            session.getTransaction().commit();
            return todo;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add Todo: " + e.getMessage(), e);
        }
    }


    @Override
    public Todo updateTodo(Todo todo) {
        try (Session session = this.sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.merge(todo);
            session.getTransaction().commit();
            return todo;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update Todo: " + e.getMessage(), e);
        }
    }


    @Override
    public UUID deleteTodo(UUID todoId) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            Todo todo = session.find(Todo.class, todoId);
            if (todo != null) {
                session.remove(todo);
            } else {
                throw new TodoExceptions.TodoDoesNotExistException("Todo with id + " + todoId + "does not exist");
            }
            session.getTransaction().commit();
            return todo.getId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Todo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Todo> viewTodos() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("from Todo", Todo.class).setMaxResults(10).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get Todos: " + e.getMessage(), e);
        }
    }

    @Override
    public Todo getTodo(UUID todoId) {
        try (Session session = this.sessionFactory.openSession()) {
            return session.find(Todo.class, todoId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get Todo: " + e.getMessage(), e);
        }
    }
}
