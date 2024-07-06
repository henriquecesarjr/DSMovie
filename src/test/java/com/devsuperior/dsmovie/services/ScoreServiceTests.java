package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {
	
	@InjectMocks
	private ScoreService service;

	@Mock
	private UserService userService;

	@Mock
	private ScoreRepository repository;

	@Mock
	private MovieRepository movieRepository;

	private UserEntity user;
	private ScoreDTO scoreDTO;
	private String movieName;
	private Long existingMovieId, nonExistingMovieId;
	private MovieEntity movieEntity;
	private ScoreEntity scoreEntity;

	@BeforeEach
	void setUp() throws Exception {

		user = UserFactory.createUserEntity();
		scoreDTO = ScoreFactory.createScoreDTO();
		existingMovieId = 1L;
		nonExistingMovieId = 2L;
		movieName = "Test Movie";
		movieEntity = MovieFactory.createMovieEntity();
		scoreEntity = ScoreFactory.createScoreEntity();

		when(userService.authenticated()).thenReturn(user);
		when(movieRepository.findById(existingMovieId)).thenReturn(Optional.of(movieEntity));
		when(movieRepository.findById(nonExistingMovieId)).thenReturn(Optional.empty());
		when(repository.saveAndFlush(any())).thenReturn(scoreEntity);
		when(movieRepository.save(any())).thenReturn(movieEntity);

	}
	
	@Test
	public void saveScoreShouldReturnMovieDTO() {

		ScoreEntity existingScore = new ScoreEntity();
		existingScore.setValue(3.5);
		movieEntity.getScores().add(existingScore);

		scoreEntity.setValue(scoreDTO.getScore());
		movieEntity.getScores().add(scoreEntity);

		MovieDTO result = service.saveScore(scoreDTO);

		double sum = (existingScore.getValue() + scoreEntity.getValue());
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingMovieId);
		Assertions.assertEquals(result.getTitle(), movieName);
		Assertions.assertEquals(result.getScore(), sum / movieEntity.getScores().size());
	}
	
	@Test
	public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {

		scoreEntity.getId().getMovie().setId(nonExistingMovieId);
		ScoreDTO dto = new ScoreDTO(scoreEntity);

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.saveScore(dto);
		});
	}
}
