package cacadores.ifal.poo.book_station.repository;

import cacadores.ifal.poo.book_station.model.entity.items.Book;
import cacadores.ifal.poo.book_station.model.entity.items.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine, String> {
    Magazine save(Magazine magazine);
}
