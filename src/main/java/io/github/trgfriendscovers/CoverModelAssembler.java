package io.github.trgfriendscovers;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CoverModelAssembler implements RepresentationModelAssembler<Cover, EntityModel<Cover>> {

    @Override
    public EntityModel<Cover> toModel(Cover cover) {
        return EntityModel.of(cover,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CoverController.class).one(cover.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CoverController.class).all()).withRel("covers"));
    }

}
