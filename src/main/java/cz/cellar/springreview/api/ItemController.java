package cz.cellar.springreview.api;

import cz.cellar.springreview.dao.ItemRepository;
import cz.cellar.springreview.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/items")
@RestController
public class ItemController {

    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository){
       this.itemRepository=itemRepository;

    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Item> getAll(){
        return itemRepository.findAll();
    }


    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public List<Item> getById(@PathVariable long id){
        return itemRepository.findById(id);
    }

    @RequestMapping(value = "/genre/{genre}", method = RequestMethod.GET)
    public List<Item> getByGenre(@PathVariable String genre){
        return itemRepository.findByGenreEquals(genre);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<Item> create(@RequestBody Item item){
        itemRepository.save(item);

        return itemRepository.findAll();
    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public List<Item> remove(@PathVariable long id){
        itemRepository.deleteById(id);
        return itemRepository.findAll();

    }
}
