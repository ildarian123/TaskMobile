package com.example.taskmobile.Database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

@Dao
public interface PostDao {
    @Query("SELECT * FROM posts")
    List<PostDb> getAll();

    @Query("SELECT * FROM posts WHERE id IN (:ids)")
    List<PostDb> loadAllByIds(int[] ids);

    @Query("SELECT * FROM posts WHERE author LIKE :author AND date LIKE :date LIMIT 1")
    PostDb findByAuthorAndDate(String author, String date);

    @Query("DELETE FROM posts")
    void deleteAllPosts();

    @Insert
    void insertAll(List<PostDb> postsDb);

    @Delete
    void delete(PostDb postDb);
}
