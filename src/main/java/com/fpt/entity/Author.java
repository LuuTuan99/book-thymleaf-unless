package com.fpt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Tên tác giả không được để trống")
    private String name;
    @Lob
    @NotEmpty(message = "Không được để trống trường này")
    private String avatar;
    @Lob
    @NotEmpty
    private String description;
    private long createdAtMLS;
    private long updatedAtMLS;
    private long deletedAtMLS;
    private int status;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public enum Status {
        ACTIVE(1), DEACTIVE(0),DELETED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public Author() {
        this.createdAtMLS = Calendar.getInstance().getTimeInMillis();
        this.updatedAtMLS = Calendar.getInstance().getTimeInMillis();
        this.status = Status.ACTIVE.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAtMLS() {
        return createdAtMLS;
    }

    public void setCreatedAtMLS(long createdAtMLS) {
        this.createdAtMLS = createdAtMLS;
    }

    public long getUpdatedAtMLS() {
        return updatedAtMLS;
    }

    public void setUpdatedAtMLS(long updatedAtMLS) {
        this.updatedAtMLS = updatedAtMLS;
    }

    public long getDeletedAtMLS() {
        return deletedAtMLS;
    }

    public void setDeletedAtMLS(long deletedAtMLS) {
        this.deletedAtMLS = deletedAtMLS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }


    public static final class Builder {
        private long id;
        private String name;
        private String avatar;
        private String description;
        private long createdAtMLS;
        private long updatedAtMLS;
        private long deletedAtMLS;
        private int status;
        private Set<Book> books = new HashSet<>();

        private Builder() {
        }

        public static Builder anAuthor() {
            return new Builder();
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCreatedAtMLS(long createdAtMLS) {
            this.createdAtMLS = createdAtMLS;
            return this;
        }

        public Builder withUpdatedAtMLS(long updatedAtMLS) {
            this.updatedAtMLS = updatedAtMLS;
            return this;
        }

        public Builder withDeletedAtMLS(long deletedAtMLS) {
            this.deletedAtMLS = deletedAtMLS;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withBooks(Set<Book> books) {
            this.books = books;
            return this;
        }

        public Author build() {
            Author author = new Author();
            author.setId(id);
            author.setName(name);
            author.setAvatar(avatar);
            author.setDescription(description);
            author.setCreatedAtMLS(createdAtMLS);
            author.setUpdatedAtMLS(updatedAtMLS);
            author.setDeletedAtMLS(deletedAtMLS);
            author.setStatus(status);
            author.setBooks(books);
            return author;
        }
    }
}
