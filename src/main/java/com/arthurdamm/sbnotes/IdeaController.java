package com.arthurdamm.sbnotes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/")
public class IdeaController {

	@Autowired
	private IdeaRepo ideaRepo;
	
	@PostMapping(path="/idea.json") // CREATE idea via json data
	public int postIdea(@RequestBody Idea idea) {
		ideaRepo.save(idea);
		return 1;
	}
	
	@GetMapping(path="/ideas") // READ all ideas as json list
	public List<Idea> getIdeas() {
		return ideaRepo.findAll();
	}
	
	@PutMapping(path="/idea/{id}") // UPDATE idea with path variable and json data
	public Idea updateIdea(@PathVariable int id, @RequestBody Idea newIdea) {
		Optional<Idea> idea = ideaRepo.findById(id);
		if (!idea.isEmpty()) {
			newIdea.setId(idea.get().getId());
			ideaRepo.save(newIdea);
			return newIdea;
		} else {
			return new Idea();
		}
	}
	
	@DeleteMapping(path="/idea/{id}") // DELETE idea with path variable 
	public String deleteIdea(@PathVariable int id) {
		Optional<Idea> idea = ideaRepo.findById(id);
		if (!idea.isEmpty()) {
			ideaRepo.delete(idea.get());
			return "deleted";
		} else {
			return "";
		}
	}
}
