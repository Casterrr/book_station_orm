package cacadores.ifal.poo.book_station.util;

import com.github.javafaker.Faker;
import cacadores.ifal.poo.book_station.model.entity.*;
import cacadores.ifal.poo.book_station.model.entity.items.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Random;

public class DataFaker {
    private static final Faker faker = new Faker(new Locale("pt-BR"));
    private static final Random random = new Random();

    public static User createFakeUser() {
        User user = new User();
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        return user;
    }

    public static Employee createFakeEmployee() {
        Employee employee = new Employee();
        employee.setName(faker.name().fullName());
        employee.setEmail(faker.internet().emailAddress());
        employee.setPassword(faker.internet().password());
        employee.setPosition(faker.job().position());
        employee.setSalary(BigDecimal.valueOf(faker.number().randomDouble(2, 1000, 10000)));
        employee.setWorkCardNumber(faker.number().digits(11));
        employee.setHireDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 1000)));
        return employee;
    }

    public static Tenant createFakeTenant() {
        Tenant tenant = new Tenant();
        tenant.setName(faker.name().fullName());
        tenant.setEmail(faker.internet().emailAddress());
        tenant.setPassword(faker.internet().password());
        tenant.setAddress(faker.address().fullAddress());
        tenant.setCpf(faker.number().digits(11));
        tenant.setPhone(faker.phoneNumber().cellPhone());
        return tenant;
    }

    public static Publisher createFakePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName(faker.book().publisher());
        publisher.setEmail(faker.internet().emailAddress());
        publisher.setAddress(faker.address().fullAddress());
        publisher.setPhone(faker.phoneNumber().phoneNumber());
        return publisher;
    }

    public static Author createFakeAuthor() {
        Author author = new Author();
        author.setName(faker.name().fullName());
        author.setBiography(faker.lorem().paragraph());
        author.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return author;
    }

    public static Genre createFakeGenre() {
        Genre genre = new Genre();
        genre.setName(faker.book().genre());
        return genre;
    }

    public static Book createFakeBook() {
        Book book = new Book();
        book.setTitle(faker.book().title());
        book.setIsbn(faker.code().isbn13());
        book.setSynopsis(faker.lorem().paragraph());
        book.setLanguage(faker.options().option("Portuguese", "English", "Spanish"));
        book.setYear(faker.number().numberBetween(1900, 2023));
        book.setAvailableQuantity(faker.number().numberBetween(1, 100));
        book.setPages(faker.number().numberBetween(50, 1000));
        book.setStatus(faker.options().option("AVAILABLE", "BORROWED", "RESERVED"));
        return book;
    }

    public static Magazine createFakeMagazine() {
        Magazine magazine = new Magazine();
        magazine.setTitle(faker.book().title());
        magazine.setPages(faker.number().numberBetween(20, 200));
        magazine.setYear(faker.number().numberBetween(1900, 2024));
        magazine.setAvailableQuantity(faker.number().numberBetween(1, 50));
        magazine.setEdition(faker.number().numberBetween(1, 100));
        magazine.setVolume(faker.number().numberBetween(1, 50));
        magazine.setDescription(faker.lorem().paragraph());
        magazine.setIssn(faker.number().digits(8));
        return magazine;
    }

    public static Loan createFakeLoan() {
        Loan loan = new Loan();
        loan.setLoanDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));
        loan.setExpectedReturnDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));
        if (random.nextBoolean()) {
            loan.setActualReturnDate(LocalDate.now());
            loan.setFineAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 100)));
            loan.setFineDate(LocalDateTime.now());
        }
        return loan;
    }

    public static Reservation createFakeReservation() {
        Reservation reservation = new Reservation();
        reservation.setIsFulfilled(faker.bool().bool());
        reservation.setReservationDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));
        return reservation;
    }
}