package cacadores.ifal.poo.book_station.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cacadores.ifal.poo.book_station.model.entity.items.Magazine;

public interface MagazineRepository extends JpaRepository<Magazine, String> {
    Magazine save(Magazine magazine);
    Magazine findMagazineById(String id);
    List<Magazine> findAll();
    boolean existsByIssn(String issn);
    void removeById(String id);
}
