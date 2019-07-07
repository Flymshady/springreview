package cz.cellar.springreview.api;

import cz.cellar.springreview.exception.ResourceNotFoundException;
import cz.cellar.springreview.model.Review;
import cz.cellar.springreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Review> getAll(){
        return reviewRepository.findAll();
    }
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Review getById(@PathVariable( value = "id") Long id){
        return reviewRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Review", "id", id));
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public List<Review> getByItemId(@PathVariable( value = "id") Long itemId){
        return reviewRepository.findByItemId(itemId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Review create(@Valid @NonNull @RequestBody Review review){
        return reviewRepository.save(review);
    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public List<Review> remove(@PathVariable(value = "id") Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Review", "id", id));
        reviewRepository.delete(review);
        // return ResponseEntity.ok().build();
        return reviewRepository.findAll();

    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Review update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Review reviewDetails){
        Review review = reviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Review", "id", id));
        review.setTextShort(reviewDetails.getTextShort());
        review.setTextLong(reviewDetails.getTextLong());

        Review updatedReview = reviewRepository.save(review);
        return updatedReview;

    }

}
