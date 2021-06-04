package com.justinpjose.miskaaassignment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.justinpjose.miskaaassignment.CountryModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(CountryModel country);

    //Delete all query
    @Query("DELETE FROM countries")
    void reset();

    //Get all data query
    @Query("SELECT * FROM countries")
    List<CountryModel> getAll();

    //Get the table count
    @Query("SELECT COUNT(*) FROM countries")
    int getCount();
}