package com.herokuapp.TRGFriendsSongs;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


@RestController
public class SongController {

    private final SongModelAssembler assembler;
    private final SongSearchService service;

    SongController(SongModelAssembler assembler, SongSearchService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/songs/api")
    CollectionModel<EntityModel<Song>> all() {
        List<EntityModel<Song>> songs = service.getAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(songs, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SongController.class).all()).withSelfRel());
    }

    @GetMapping("/songs/{id}")
    EntityModel<Song> one(@PathVariable String id) {
        Song song = service.getById(id);
        return assembler.toModel(song);
    }

    @CrossOrigin(origins = {"*", "http://localhost:4200"}, allowedHeaders =  "*")
    @GetMapping("/songs/api/search")
    CollectionModel<?> searchSongs(@RequestParam(value="query", required=false) String searchQuery)
    {
        if (searchQuery == null || searchQuery.isEmpty()) {
            List<EntityModel<Song>> emptySongs = Collections.emptyList();
            return CollectionModel.of(emptySongs, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SongController.class).all()).withRel("songs"));
        }
            List<EntityModel<Song>> songs = service.findSong(searchQuery).stream().map(assembler::toModel).collect(Collectors.toList());
            return CollectionModel.of(songs, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SongController.class).all()).withSelfRel());
    }
}
