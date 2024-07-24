package cacadores.ifal.poo.book_station.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!production")
public class DataPopulationCommand implements CommandLineRunner {
    private final DataPopulator dataPopulator;

    @Autowired
    public DataPopulationCommand(DataPopulator dataPopulator) {
        this.dataPopulator = dataPopulator;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && args[0].equals("populate")) {
            System.out.println("Starting data population...");
            dataPopulator.run();
            System.out.println("Data population completed successfully.");
            System.exit(0);
        }
    }
}