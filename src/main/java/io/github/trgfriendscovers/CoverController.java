package io.github.trgfriendscovers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


@RestController
public class CoverController {

    private final CoverModelAssembler assembler;
    private final CoverSearchService service;

    CoverController(CoverModelAssembler assembler, CoverSearchService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/covers/api")
    CollectionModel<EntityModel<Cover>> all() {
        List<EntityModel<Cover>> covers = service.getAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(covers, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CoverController.class).all()).withSelfRel());
    }

//    @PostMapping("/covers")
//    ResponseEntity<?> covers(@RequestBody Cover newCovers) {
//        EntityModel<Cover> entityModel = assembler.toModel(repository.save(newCover));
//        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
//    }
    // Single item

    @GetMapping("/covers/{id}")
    EntityModel<Cover> one(@PathVariable String id) {
        Cover cover = service.getById(id);
        return assembler.toModel(cover);
    }

    // Update
//    @PutMapping("/covers/{id}")
//    ResponseEntity<?> replaceCover(@RequestBody Cover newCover, @PathVariable String id)
//    {
//        Cover updatedCover = repository.findById(id).get();
//        updatedCover.setCover(newCover.getCover());
//        updatedCover.setFeaturing(newCover.getFeaturing());
//        updatedCover.setTheme(newCover.getTheme());
//        updatedCover.setOrigin(newCover.getOrigin());
//        updatedCover.setLink(newCover.getLink());
//        repository.save(updatedCover);
//
//        EntityModel<Cover> entityModel = assembler.toModel(updatedCover);
//        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
//    }
//
//    @DeleteMapping("/covers/{id}")
//    ResponseEntity<?> deleteCover(@PathVariable String id) {
//        repository.deleteById(id);
//
//        return ResponseEntity.noContent().build();
//    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://tony-le.github.io/"})
    @GetMapping("/covers/api/search")
    CollectionModel<?> searchCovers(@RequestParam(value="query", required=false) String searchQuery)
    {
        if (searchQuery == null || searchQuery.isEmpty()) {
            List<EntityModel<Cover>> emptyCovers = Collections.emptyList();
            return CollectionModel.of(emptyCovers, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CoverController.class).all()).withRel("covers"));
        }
            List<EntityModel<Cover>> covers = service.findCover(searchQuery).stream().map(assembler::toModel).collect(Collectors.toList());
            return CollectionModel.of(covers, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CoverController.class).all()).withSelfRel());
    }
}
