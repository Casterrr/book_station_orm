package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.items.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, String> {
    Magazine save(Magazine magazine);
    Magazine findMagazineById(String id);
    List<Magazine> findAll();
    void removeById(String id);
}
