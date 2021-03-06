package com.herokuapp.TRGFriendsSongs;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SongModelAssembler implements RepresentationModelAssembler<Song, EntityModel<Song>> {

    @Override
    public EntityModel<Song> toModel(Song song) {
        return EntityModel.of(song,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SongController.class).one(song.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SongController.class).all()).withRel("songs"));
    }
}
