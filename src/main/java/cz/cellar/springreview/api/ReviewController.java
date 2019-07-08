package cz.cellar.springreview.api;

import cz.cellar.springreview.exception.ResourceNotFoundException;
import cz.cellar.springreview.model.Review;
import cz.cellar.springreview.repository.ItemRepository;
import cz.cellar.springreview.repository.PersonRepository;
import cz.cellar.springreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reviews/")
public class ReviewController {

    private ReviewRepository reviewRepository;
    private ItemRepository itemRepository;
    private PersonRepository personRepository;

    @Autowired
    public ReviewController(PersonRepository personRepository, ReviewRepository reviewRepository, ItemRepository itemRepository) {
        this.reviewRepository = reviewRepository;
        this.itemRepository = itemRepository;
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Review getById(@PathVariable( value = "id") Long id){
        return reviewRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Review", "id", id));
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public List<Review> getByItemId(@PathVariable( value = "itemId") Long itemId){
        return reviewRepository.findByItemId(itemId);
    }

    @RequestMapping(value = "/create/{itemId}/{personId}", method = RequestMethod.POST)
    public @ResponseBody Review create(@PathVariable(value = "itemId") Long itemId,
                                       @PathVariable(value = "personId") Long personId,
                                       @Valid @NonNull @RequestBody Review review){
        return itemRepository.findById(itemId).map(item -> {
            review.setItem(item);
            return personRepository.findById(personId).map(person-> {
                review.setPerson(person);
                return reviewRepository.save(review);
            }).orElseThrow(()-> new ResourceNotFoundException("Person", "id", personId));
        }).orElseThrow(()-> new ResourceNotFoundException("Item", "id", itemId));
    }

    @RequestMapping(value = "/remove/{reviewId}/items/{itemId}", method = RequestMethod.POST)
    public ResponseEntity<?> remove(@PathVariable(value = "reviewId") Long reviewId,
                                 @PathVariable(value = "itemId") Long itemId){

        return reviewRepository.findByIdAndItemId(reviewId, itemId).map(review -> {
            reviewRepository.delete(review);
            return ResponseEntity.ok().build() ;
        }).orElseThrow(()-> new ResourceNotFoundException("Review", "id", reviewId));


    }

    @RequestMapping(value = "/update/{reviewId}/items/{itemId}", method = RequestMethod.PUT)
    public Review update(@PathVariable(value = "reviewId") Long reviewId,
                         @PathVariable(value = "itemId") Long itemId,
                       @Valid @RequestBody Review reviewRequest){

        if(!itemRepository.existsById(itemId)){
            throw new ResourceNotFoundException("Item", "id", itemId);
        }

        return reviewRepository.findById(reviewId).map(review -> {
            review.setTextShort(reviewRequest.getTextShort());
            review.setTextLong(reviewRequest.getTextLong());
            return reviewRepository.save(review);
        }).orElseThrow(()-> new ResourceNotFoundException("Review", "id", reviewId));

    }

}
