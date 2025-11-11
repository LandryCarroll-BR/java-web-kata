package com.landrycarroll.javawebkata.entities;

import com.landrycarroll.javawebkata.exceptions.TodoExceptions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_complete")
    private boolean isComplete;

    public Todo(String title, String description) {
        this.id = UUID.randomUUID();
        validateTitle(title);
        validateDescription(description);
        this.isComplete = false;
    }

    public Todo() {

    }

    private void validateTitle(String title) throws TodoExceptions.InvalidInputException {
        int MAXIMUM_TITLE_LENGTH = 50;

        if (title.length() > MAXIMUM_TITLE_LENGTH) {
            throw new TodoExceptions.InvalidInputException("Title exceeds maximum length of " + MAXIMUM_TITLE_LENGTH);
        }

        setTitle(title);
    }

    private void validateDescription(String description) throws TodoExceptions.InvalidInputException {
        int MAXIMUM_DESCRIPTION_LENGTH = 200;

        if (description.length() > MAXIMUM_DESCRIPTION_LENGTH) {
            throw new TodoExceptions.InvalidInputException("Description exceeds maximum length of " + MAXIMUM_DESCRIPTION_LENGTH);
        }

        setDescription(description);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isComplete;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isPresent() {
        return id != null;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Todo todo)) return false;
        return isComplete == todo.isComplete && Objects.equals(getId(), todo.getId()) && Objects.equals(getTitle(), todo.getTitle()) && Objects.equals(getDescription(), todo.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), isComplete);
    }
}
