package eu.ase.ro.seminars.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eu.ase.ro.seminars.utils.Cinema;

@Dao
public interface CinemaDao {
    @Insert
    void insert(Cinema cinema);

    @Query("select * from cinemas")
    List<Cinema> getAll();

    @Query("delete from cinemas")
    void deleteAll();
}
