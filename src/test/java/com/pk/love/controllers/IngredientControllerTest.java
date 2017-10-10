package com.pk.love.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pk.love.command.IngredientCommand;
import com.pk.love.command.RecipeCommand;
import com.pk.love.service.IngredientService;
import com.pk.love.service.RecipeService;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;
	
	IngredientController controller;
	
	MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new IngredientController(recipeService,ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	}

	@Test
	public void testListIngredients() throws Exception {
		//given
		RecipeCommand command = new RecipeCommand();
		
		//when 
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}
	
	@Test
	public void testShowIngredient() throws Exception{
		//given
		IngredientCommand command = new IngredientCommand();
		
		//when
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
		
		//then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
		 	.andExpect(status().isOk())
		 	.andExpect(view().name("recipe/ingredient/show"))
		 	.andExpect(model().attributeExists("ingredient"));
	}

}