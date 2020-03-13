package com.example.taskmobile.Database.dao;

import androidx.lifecycle.LiveData;
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

    @Query("SELECT * FROM posts ORDER BY id DESC LIMIT :limit ")
    List<PostDb> getLastPosts(int limit);

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

    @Query("SELECT * FROM posts WHERE id < :maxItem ORDER BY id DESC limit :limit")
    List<PostDb> getNextPosts(int maxItem, int limit);

    @Query("SELECT * FROM posts WHERE id > :maxItem ORDER BY id ASC")
    List<PostDb> getNewPosts(int maxItem);

    @Query("SELECT Count(*) FROM posts")
    int getCountOfRows();

    @Query("SELECT Count(*) FROM posts")
    LiveData<Integer> getCountOfNewPosts();
}
