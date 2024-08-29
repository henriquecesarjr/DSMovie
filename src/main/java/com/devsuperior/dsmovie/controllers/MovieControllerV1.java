package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.MovieGenreDTO;
import com.devsuperior.dsmovie.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "v1/movies")
@Tag(name = "Movies", description = "Controller for Movie")
public class MovieControllerV1 {

	@Autowired
	private MovieService service;

	@Operation(
			description = "Get all movies",
			summary = "List all movies",
			responses = {
					@ApiResponse(description = "Ok", responseCode = "200")
			}
	)
	@GetMapping(produces = "application/json")
	public Page<MovieGenreDTO> findAll(
			@RequestParam(value="title", defaultValue = "") String title, 
			Pageable pageable) {
		return service.findAllMovieGenre(title, pageable);
	}

	@Operation(
			description = "Get movie by id",
			summary = "Get movie by id",
			responses = {
					@ApiResponse(description = "Ok", responseCode = "200"),
					@ApiResponse(description = "Not Found", responseCode = "404")
			}
	)
	@GetMapping(value = "/{id}", produces = "application/json")
	public MovieGenreDTO findById(@PathVariable Long id) {
		return service.findByIdMovieGenre(id);
	}


}
