package cacadores.ifal.poo.book_station.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cacadores.ifal.poo.book_station.repository.*;
import cacadores.ifal.poo.book_station.model.entity.*;
import cacadores.ifal.poo.book_station.model.entity.items.*;

@Service
public class DataPopulator {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public void run() throws Exception {
        // Populate Users
        for (int i = 0; i < 10; i++) {
            userRepository.save(DataFaker.createFakeUser());
        }

        // Populate Employees
        for (int i = 0; i < 5; i++) {
            employeeRepository.save(DataFaker.createFakeEmployee());
        }

        // Populate Tenants
        for (int i = 0; i < 20; i++) {
            tenantRepository.save(DataFaker.createFakeTenant());
        }

        // Populate Publishers
        for (int i = 0; i < 5; i++) {
            publisherRepository.save(DataFaker.createFakePublisher());
        }

        // Populate Authors
        for (int i = 0; i < 10; i++) {
            authorRepository.save(DataFaker.createFakeAuthor());
        }

        // Populate Genres
        for (int i = 0; i < 5; i++) {
            genreRepository.save(DataFaker.createFakeGenre());
        }

        // Populate Books
        for (int i = 0; i < 50; i++) {
            Book book = DataFaker.createFakeBook();
            book.setPublisher(publisherRepository.findAll().get(0));
            book.setAuthor(authorRepository.findAll().get(0));
            book.setGenre(genreRepository.findAll().get(0));
            bookRepository.save(book);
        }

        // Populate Magazines
        for (int i = 0; i < 20; i++) {
            Magazine magazine = DataFaker.createFakeMagazine();
            magazine.setPublisher(publisherRepository.findAll().get(0));
            magazine.setGenre(genreRepository.findAll().get(0));
            magazineRepository.save(magazine);
        }

        // Populate Loans
        for (int i = 0; i < 30; i++) {
            Loan loan = DataFaker.createFakeLoan();
            loan.setUser(userRepository.findAll().get(0));
            loan.setItem(bookRepository.findAll().get(0));
            loanRepository.save(loan);
        }

        // Populate Reservations
        for (int i = 0; i < 15; i++) {
            Reservation reservation = DataFaker.createFakeReservation();
            reservation.setUser(userRepository.findAll().get(0));
            reservation.setItem(bookRepository.findAll().get(0));
            reservationRepository.save(reservation);
        }
    }
}