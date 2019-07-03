package cz.cellar.springreview.dao;

import cz.cellar.springreview.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private ItemRepository itemRepository;
    @Autowired
    public DatabaseSeeder(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Item> items=new ArrayList<>();

        items.add(new Item("Nazev", 2018, "album", "rock", "Album kjewn z heg", "dlouhy text"));
        items.add(new Item("Nazefwwfv", 2019, "single", "hiphop", "Single kjewn z heg", "dlouhy text"));

        itemRepository.saveAll(items);

    }
}
